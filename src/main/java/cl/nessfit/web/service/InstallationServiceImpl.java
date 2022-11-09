package cl.nessfit.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nessfit.web.model.Installation;
import cl.nessfit.web.repository.InstallationRepositoryInterface;

import java.util.List;

@Service
public class InstallationServiceImpl implements InstallationServiceInterface{
    @Autowired
    private InstallationRepositoryInterface installationRepository;

    /**
     * Saves the installations in the database.
     */
    @Override
    public void save(Installation installation) {
        installationRepository.save(installation);
    }
    /**
     * Given a name, returns a installation with that name data.
     */
    @Override
    public Installation searchByName(String name) {
        return installationRepository.findByName(name);
    }
    /**
     * Return a list gith all installations from the database.
     */
    @Override
    public List<Installation> getInstallations() { return installationRepository.findAll(); }

}
