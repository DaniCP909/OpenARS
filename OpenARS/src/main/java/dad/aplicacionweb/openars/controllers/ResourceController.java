package dad.aplicacionweb.openars.controllers;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.models.Resource;
import dad.aplicacionweb.openars.services.CommentService;
import dad.aplicacionweb.openars.services.ResourceService;
import dad.aplicacionweb.openars.services.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showResource(Model model, @PathVariable Long id){

        Optional<Resource> resource = resourceServ.findById(id);
        if(resource.isPresent()){
            model.addAttribute("resource", resource.get());
            model.addAttribute("opinions", resource.get().getOpinions());
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

    @GetMapping("/addresource")
    public String addResource(){

        return "temps_resource/add-resource";

    }

    @PostMapping("/addresource")
    public String addResourcePost(Model model, Resource resource, HttpServletRequest request, MultipartFile resourceFile) throws IOException{

        Principal principal = request.getUserPrincipal();

        if (!resourceFile.isEmpty()){
            resource.setFile(BlobProxy.generateProxy(resourceFile.getInputStream(), resourceFile.getSize()));
            resource.setBfile(true);
        }

        resource.setOwner(userServ.findByUsername(principal.getName()));

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
