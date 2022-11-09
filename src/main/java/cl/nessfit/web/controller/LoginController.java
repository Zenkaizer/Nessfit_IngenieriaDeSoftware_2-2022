package cl.nessfit.web.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping ("/index")
    public String index() {
        return "index";
    }

    /**
     * Method in charge of mapping the login url.
     * @return Login html functions.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Method in charge of logging out the user in the system.
     * @param request Logout request.
     * @return Login form html.
     */
    @GetMapping ("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/index";
    }
}