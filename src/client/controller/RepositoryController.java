package client.controller;

import client.ClientApp;
import client.view.*;
import remote.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RepositoryController {

    private ClientApp app;
    private Registry registry;
    private List<Part> currentPart;
    private PartRepository currentRepository;

    public RepositoryController(ClientApp app) {
        this.app = app;
        currentPart = new ArrayList<>();
        try {
            this.registry = LocateRegistry.getRegistry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void index() {

        List<PartRepository> repositories;
        try {
            repositories = new ArrayList<>();
            String[] nameReps = registry.list();
            for (String name : nameReps) {
            	PartRepository repository = (PartRepository) registry.lookup(name);
            	repositories.add(repository);
            }

        } catch (Exception e) {
            repositories = null;
        }
        app.setCurrentScreen(new RepositoriesListView(this, repositories).render());
    }

    public void connectToRepository(PartRepository repository) {
        currentRepository = repository;
        app.setCurrentScreen(new RepositoryView(this, currentRepository, currentPart).render());
    }

    public void create(String name, String description) {
        try {
            currentRepository.addPart(name, description, currentPart);
            currentPart = new ArrayList<>();
            app.setCurrentScreen(new RepositoryView(this, currentRepository, currentPart).render());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void remove(Part p) {
        try {
            if(currentRepository.getParts().contains(p))
                System.out.println(p);
            System.out.println(currentRepository.getParts().remove(currentRepository.getPart(p.getCode())));
//                    .addPart(name, description, currentPart);
//            currentPart = new ArrayList<>();
//            app.setCurrentScreen(new RepositoryView(this, currentRepository, currentPart).render());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
