package cl.nessfit.web.service;

import cl.nessfit.web.model.Installation;

import java.util.List;

public interface InstallationServiceInterface {
    
    void save(Installation installation);

    Installation searchByName(String name);

    List<Installation> getInstallations();
}
