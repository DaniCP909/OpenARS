package dad.aplicacionweb.CommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentServiceLog {

    private Logger log = LoggerFactory.getLogger(CommentServiceLog.class);

    public CommentServiceLog() {}

    @GetMapping("/cs-page-_log")
    public String page(Model model) {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("A INFO Mesaage");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        return "page";
    }
}
