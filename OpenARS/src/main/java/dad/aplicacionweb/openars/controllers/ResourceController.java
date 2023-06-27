package dad.aplicacionweb.openars.controllers;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.models.Resource;
import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.services.CommentService;
import dad.aplicacionweb.openars.services.ResourceService;
import dad.aplicacionweb.openars.services.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;


@Controller
@RequestMapping("/all-resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceServ;

    @Autowired
    private CommentService commentServ;

    @Autowired
    private UserService userServ;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();   //java.security

        if(principal != null){

            model.addAttribute("logged", true);
            model.addAttribute("username", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));     //return boolean

        } else{
            model.addAttribute("logged", false);
        }

    }

    @GetMapping("/")
    public String showResources(Model model){
        model.addAttribute("resources", resourceServ.findAll());
        return "temps_Resource/all-resources";
    }

    @GetMapping("/{id}")
    public String showResource(Model model, @PathVariable Long id, HttpServletRequest request){

        Optional<Resource> resource = resourceServ.findById(id);

        boolean isowner = false;

        if(resource.isPresent()){
            Resource rsc = resource.get();
            if(request.isUserInRole("USER")){
                Principal principal = request.getUserPrincipal();
                User logged = userServ.findByUsername(principal.getName());
                if(rsc.getOwner().getUsername().equals(logged.getUsername()))   isowner = true;
            }
            

            model.addAttribute("resource", resource.get());
            model.addAttribute("opinions", resource.get().getOpinions());
            model.addAttribute("isowner", isowner);
            return "temps_Resource/resource";
        }
        else{
            return "temps_Resource/all-resources";
        }

    }


    @GetMapping("/{id}/preview")
    public ResponseEntity<Object> downloadPreview(@PathVariable Long id) throws SQLException{

        Optional<Resource> resource = resourceServ.findById(id);
        if(resource.isPresent() && resource.get().getPreview() != null){
            org.springframework.core.io.Resource file = new InputStreamResource(resource.get().getPreview().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/png")
                    .contentLength(resource.get().getPreview().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Object> downloadFile(@PathVariable Long id) throws SQLException{

        Optional<Resource> resource = resourceServ.findById(id);

        if(resource.isPresent() && resource.get().getFile() != null){

            org.springframework.core.io.Resource file = new InputStreamResource(resource.get().getFile().getBinaryStream());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + (resource.get().getName()) + ".zip");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");

            return ResponseEntity.ok().headers(headers).contentLength(resource.get().getFile().length()).body(file);

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/edit-resource")
    public String editResource(Model model, @PathVariable Long id){
        Optional<Resource> resource = resourceServ.findById(id);
        if(resource.isPresent()){
            Resource actual = resource.get();
            User owner = actual.getOwner();
            model.addAttribute("actresource", actual);
            model.addAttribute("rscowner", owner);
        }
        return "temps_Resource/edit-resource";
    }

    @PostMapping("/{id}/edit-resource")
    public String editResourcePost(Model model, @PathVariable Long id, Resource resource){
        Optional<Resource> preresource = resourceServ.findById(id);

        resource.setOwner(preresource.orElseThrow().getOwner());
        resource.setFile(preresource.orElseThrow().getFile());
        resource.setPreview(preresource.orElseThrow().getPreview());

        resource.setBpreview(true);
        resource.setBfile(true);

        resourceServ.save(resource);
        return "redirect:/all-resources/" + resource.getId();
    }

    @GetMapping("/{id}/delete-resource")
    public String deleteResource(Model model, @PathVariable Long id){
        model.addAttribute("rscname", resourceServ.findById(id).get().getName());
        resourceServ.deleteById(id);
        return "temps_Resource/removed-resource";
    }

    @GetMapping("/addresource/{userid}")
    public String addResource(Model model, @PathVariable Long userid){

        model.addAttribute("userid", userid);

        return "temps_Resource/add-resource";

    }

    @PostMapping("/addresource/{userid}")
    public String addResourcePost(Model model, Resource resource, HttpServletRequest request, @PathVariable Long userid, MultipartFile resourceFile, MultipartFile previewFile) throws IOException{

        Optional<User> actual = userServ.findById(userid);


        if (!resourceFile.isEmpty()){
            resource.setFile(BlobProxy.generateProxy(resourceFile.getInputStream(), resourceFile.getSize()));
            resource.setBfile(true);
        }

        if (!previewFile.isEmpty()){
            resource.setPreview(BlobProxy.generateProxy(previewFile.getInputStream(), previewFile.getSize()));
            resource.setBpreview(true);
        }

        resource.setOwner(actual.get());

        resourceServ.save(resource);


        return "redirect:/all-resources/"+resource.getId();

    }



    private void updateImage(Resource resource, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {

        if (!imageField.isEmpty()) {
            resource.setPreview(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            resource.setBpreview(true);
        } else {
            if (removeImage) {
                resource.setPreview(null);
                resource.setBpreview(false);
            } else {
                // Maintain the same image loading it before updating the book
                Resource auxResource = resourceServ.findById(resource.getId()).orElseThrow();
                if (auxResource.getBpreview()) {
                    resource.setPreview(BlobProxy.generateProxy(resource.getPreview().getBinaryStream(),
                            resource.getPreview().length()));
                    resource.setBpreview(true);
                }
            }
        }
    }

}
