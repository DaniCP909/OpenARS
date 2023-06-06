package dad.aplicacionweb.openars.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenARSLog {

	private Logger log = LoggerFactory.getLogger(OpenARSLog.class);

	public OpenARSLog() {}

	@GetMapping("/openars-page_log")
	public String page(Model model) {
		log.trace("A TRACE Message");
		log.debug("A DEBUG Message");
		log.info("A INFO Mesaage");
		log.warn("A WARN Message");
		log.error("An ERROR Message");
		return "page";
	}
}
