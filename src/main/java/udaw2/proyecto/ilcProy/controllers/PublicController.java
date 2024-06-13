package udaw2.proyecto.ilcProy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import udaw2.proyecto.ilcProy.domain.FormInfo;

@Controller
@RequestMapping("/public")
public class PublicController {

    @GetMapping({ "/quienessomos" })
    public String showQuienesSomos() {
        return "/public/quienessomos";
    }

    @GetMapping({ "/contacto" })
    public String showContacta(Model model) {
        model.addAttribute("formInfo", new FormInfo());
        return "/public/contacto";
    }

    @PostMapping("/contacto/submit")
    public String showMyformSubmit(@Valid @ModelAttribute FormInfo formInfo,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors())
            return "redirect:/public/contacto";
        String x = null;
        switch (formInfo.getMotivo()) {
            case 1 -> x = "Queja";
            case 2 -> x = "Consulta";
            case 3 -> x = "Otros";
        }
        model.addAttribute("motivoElegido", x);
        return "/public/formSubmitView";
    }

    @GetMapping("/signin")
    public String showLogin() {
        return "signinView";
    }

    @GetMapping("/signout")
    public String showLogout() {
        return "signoutView";
    }
}
