package cl.nessfit.web.util;

import cl.nessfit.web.model.User;
import cl.nessfit.web.service.UserServiceInterface;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileValidation {

    /**
     * Method that is responsible for validating if the email exists in the system.
     * @param email New email for the user.
     * @return "True" if the email not exist and "False" if exists.
     */
    public static boolean notExistEmail(UserServiceInterface userService, User editedUser, String email) {

        // If the new email is the same that user email.
        if (editedUser != null) { if (editedUser.getEmail().equals(email)) { return true; } }

        // We check if the email currently exists in the system.
        List<User> userList = userService.getUsers();
        for (User user : userList) {
            // If the email exists in the system, then we return false.
            if (user.getEmail().equals(email)) { return false; }
        }
        return true;
    }
    /**
     * Method that is responsible for validating if the rut not exists in the system.
     * @param rut New rut for the user.
     * @return "True" if the rut not exist and "False" if exists.
     */
    public static boolean notExistRut(UserServiceInterface userService, String rut) {
        return userService.searchByRut(rut) == null;
    }
    /**
     * Method that checks if the rut is valid.
     * @param rut user's rut.
     * @return true if rut is valid, otherwise false.
     */
    public static boolean validRut(String rut) {
        Pattern pattern = Pattern.compile("^([1-9][0-9]{7,13}[0-9K])$");
        Matcher matcher = pattern.matcher(rut);
        if (matcher.matches()) {
            String dv = calculateDV(rut);
            return rut.charAt(rut.length() - 1) == dv.charAt(0);
        }
        return false;
    }
    /**
     * Calculates the verification digit given a rut.
     * @param rut user's rut.
     * @return string of verification digit.
     */
    public static String calculateDV(String rut) {
        String rutNumeric = rut.substring(0, rut.length() - 1);

        int M = 0, S = 1, T = Integer.parseInt(rutNumeric);
        for (; T != 0; T = (int) Math.floor(T /= 10))
            S = (S + T % 10 * (9 - M ++ % 6)) % 11;
        return ( S > 0 ) ? String.valueOf(S - 1) : "K";
    }

}