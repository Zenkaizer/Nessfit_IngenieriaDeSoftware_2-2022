package cl.nessfit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import cl.nessfit.web.service.UserServiceInterface;

@Controller
public class HomeController {
    @Autowired
    UserServiceInterface userServiceInterface;
    /**
     * Method who control the redirection to the home page.  
     * @return the form from the home page. 
     */
    @GetMapping ({"/home", "/", ""})
    public String home() {
        return "home";
    }
}