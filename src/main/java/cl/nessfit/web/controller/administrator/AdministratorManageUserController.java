package cl.nessfit.web.controller.administrator;

import cl.nessfit.web.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/administrator")
public class AdministratorManageUserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping ("/manage-user")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "administrator/manage-user";
    }

    @PostMapping("/manage-user")
    public String manageUser(@RequestParam("rut") String rut, Model model) {
        model.addAttribute("users", userService.searchByRut(rut));
        model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "administrator/manage-user";
    }
}