package cl.nessfit.web.controller.administrative;

import cl.nessfit.web.model.User;
import cl.nessfit.web.service.UserServiceInterface;
import cl.nessfit.web.util.ProfileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping ("/administrative")
public class AdministrativeEditProfileController {
    @Autowired
    UserServiceInterface userService;

    @GetMapping ("/edit-profile/{rut}")
    public String editProfile(Model model, @PathVariable String rut) {
        User user = userService.searchByRut(rut);
        model.addAttribute("user", user);
        return "administrative/edit-profile";
    }

    /**
     * Receive data from edit-profile.html.
     * @param modelUser User from the html form.
     * @param bindingResult Result from User class validations.
     * @param model Is the application's dynamic data structure.
     * @return Return user to home page.
     */
    @PostMapping("/edit-profile")
    public String editProfile(@Valid @ModelAttribute("user") User modelUser,
                              BindingResult bindingResult,
                              Model model) {
        // Logged user obtained by rut
        User editedUser = userService.searchByRut(modelUser.getRut());
        // "True" if email exist on the system, "False" if not
        boolean existEmail = ProfileValidation.notExistEmail(userService, editedUser, modelUser.getEmail());
        // If there is a problem, it is verified
        if (bindingResult.hasErrors() || !existEmail) {
            model.addAttribute("emailExist", existEmail);
            return "administrative/edit-profile";
        }

        // Update user values
        editedUser.setFirstName(modelUser.getFirstName());
        editedUser.setLastName(modelUser.getLastName());
        editedUser.setEmail(modelUser.getEmail());
        editedUser.setPhone(modelUser.getPhone());
        // Save data from actualUser
        userService.save(editedUser);

        return "redirect:/administrative/manage-user";
    }
}