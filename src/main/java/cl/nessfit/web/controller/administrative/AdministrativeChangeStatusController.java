package cl.nessfit.web.controller.administrative;

import cl.nessfit.web.model.User;
import cl.nessfit.web.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrative")
public class AdministrativeChangeStatusController {
    @Autowired
    UserServiceInterface userService;

    /**
     * Method that return a form.
     * @return the "change-status-user" form.
     */
    @GetMapping("/change-status-user")
    public String changeStatusUser() {
        return "administrative/manage-user";
    }

    /**
     * Changes the user status, depending on the form election by the administrator or administrative.
     * @param rut Rut from user that we change the status.
     * @return return to "change-status-user" page, or home page.
     */
    @PostMapping("/change-status-user/{rut}")
    public String changeStatusUser(@PathVariable String rut) {
        User user = userService.searchByRut(rut);

        user.setStatus(user.getStatus() == 1 ? 0 : 1);
        userService.save(user);
        return "redirect:/administrative/manage-user";
    }
}