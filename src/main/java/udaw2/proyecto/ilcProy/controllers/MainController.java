package udaw2.proyecto.ilcProy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping({"","/","/index","/home"})
	public String showHome(Model model){ 
		return "public/homeView";
	}
	
}
