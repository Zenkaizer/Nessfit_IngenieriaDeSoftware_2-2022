package cl.nessfit.web.controller.administrative;

import cl.nessfit.web.model.User;
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
@RequestMapping("/administrative")
public class AdministrativeManageUserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/manage-user")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAdministrativeAndClients());
        model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "administrative/manage-user";
    }

    @PostMapping("/manage-user")
    public String manageUser(@RequestParam("rut") String rut, Model model) {
        User user  = userService.searchByRut(rut);
        if (user != null) {
            if (user.getRole().getId() == 2 || user.getRole().getId() == 3) {
                model.addAttribute("users", user);
            } else {
                model.addAttribute("users", null);
            }
        } else {
            model.addAttribute("users", null);
        }
        model.addAttribute("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "administrative/manage-user";
    }
}