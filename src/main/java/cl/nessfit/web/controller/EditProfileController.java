package cl.nessfit.web.controller;

import cl.nessfit.web.model.User;
import cl.nessfit.web.service.UserServiceInterface;
import cl.nessfit.web.util.ProfileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class EditProfileController {
    @Autowired
    UserServiceInterface userService;

    /**
     * Check if user is authenticated.
     * @param model Is the application's dynamic data structure.
     * @return access to the webpage.
     */
    @GetMapping ("/edit-profile")
    public String editProfile(Model model) {
        User actualUser = userService.searchByRut(SecurityContextHolder.getContext().getAuthentication().getName());
        // The current user of the system is sent
        model.addAttribute("user", actualUser);
        return "edit-profile";
    }

    /**
     * Receive data from edit-profile.html.
     * @param modelUser User from the html form.
     * @param bindingResult Result from User class validations.
     * @param model Is the application's dynamic data structure.
     * @return Return user to home page.
     */
    @PostMapping("/edit-profile")
    public String editProfile(@Valid @ModelAttribute("user") User modelUser, BindingResult bindingResult, Model model){

        // Logged user obtained by rut
        User actualUser = userService.searchByRut(SecurityContextHolder.getContext().getAuthentication().getName());
        // "True" if email exist on the system, "False" if not
        boolean existEmail = ProfileValidation.notExistEmail(userService, actualUser, modelUser.getEmail());
        // If there is a problem, it is verified
        if (bindingResult.hasErrors() || !existEmail) {
            model.addAttribute("emailExist", existEmail);
            return "edit-profile";
        }

        // Update user values
        actualUser.setFirstName(modelUser.getFirstName());
        actualUser.setLastName(modelUser.getLastName());
        actualUser.setEmail(modelUser.getEmail());
        actualUser.setPhone(modelUser.getPhone());
        // Save data from actualUser
        userService.save(actualUser);

        // Redirect to home.
        return "redirect:/";
    }
}