package cl.nessfit.web.util;

import cl.nessfit.web.model.Installation;
import cl.nessfit.web.service.InstallationServiceInterface;
import java.util.List;

public class InstallationValidation {

     /**
     * Method that is responsible for validating if the name exists in the system.
     * @param name New name for the installation.
     * @return "True" if the name not exist and "False" if exists.
     */
    public static boolean notExistName(InstallationServiceInterface installationService, String name) {
        // We check if the name currently exists in the system.
        List<Installation> installationList = installationService.getInstallations();
        for (Installation installation : installationList) {
            // If the name exists in the system, then we return false.
            if (installation.getName().equals(name)) { return false; }
        }
        return true;
    }
}
