package cl.nessfit.web.util;

public class PasswordValidation {

    /**
     * Method that performs validations corresponding to the password.
     * @param password1 New password.
     * @param password2 Repeat new password.
     * @return "True" if the password validation is ok, and "False" if not.
     */
    public static boolean validatePassword(String password1, String password2) {
        if (areEquals(password1, password2)) {
            return lengthValidation(password1);
        }
        return false;
    }
    /**
     * Check if both passwords are equal.
     * @param password1 new password
     * @param password2	repeated new password
     * @return true if are equal, false otherwise
     */
    public static boolean areEquals(String password1, String password2) {
        return password1.equals(password2);
    }
    /**
     * Check if the password has a valid length
     * @param password new password
     * @return true if length is valid, false otherwise
     */
    public static boolean lengthValidation(String password) {
        if (password.length() >= 10) {
            return password.length() <= 15;
        }
        return false;
    }

}