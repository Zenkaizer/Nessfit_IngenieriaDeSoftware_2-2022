package cl.nessfit.web.controller.administrative;

import cl.nessfit.web.model.Role;
import cl.nessfit.web.model.User;
import cl.nessfit.web.service.UserServiceInterface;
import cl.nessfit.web.util.ProfileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/administrative")
public class AdministrativeRegisterUserController {

    @Autowired
    UserServiceInterface userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/register-client")
    public String registerClient(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "administrative/register-user";
    }

    @PostMapping("/register-client")
    public String registerNewClient(@Valid @ModelAttribute("user") User modelUser, BindingResult bindingResult, Model model) {

        // Extra verifications
        boolean existEmail = ProfileValidation.notExistEmail(userService, null, modelUser.getEmail());
        boolean existRut = ProfileValidation.notExistRut(userService, modelUser.getRut());
        boolean validRut = ProfileValidation.validRut(modelUser.getRut());

        // If there is a problem, it is verified
        if (bindingResult.hasErrors() || !existEmail || !existRut || !validRut) {
            model.addAttribute("existEmail", existEmail);
            model.addAttribute("existRut", existRut);
            model.addAttribute("validRut", validRut);
            model.addAttribute("rut", modelUser.getRut());
            return "administrative/register-user";
        }
        // New User
        User newUser = new User();
        // Set attributes
        newUser.setRut(modelUser.getRut());
        newUser.setFirstName(modelUser.getFirstName());
        newUser.setLastName(modelUser.getLastName());
        newUser.setPhone(modelUser.getPhone());
        newUser.setEmail(modelUser.getEmail());
        newUser.setStatus(1);
        newUser.setPassword(passwordEncoder.encode(modelUser.getRut()));
        // Create role
        Role role = new Role();
        // Client
        role.setId(3);
        newUser.setRole(role);
        // Save user
        userService.save(newUser);

        return "redirect:/administrative/manage-user";
    }
}
