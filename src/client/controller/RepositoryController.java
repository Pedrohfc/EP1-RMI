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

public class RepositoryController {
	ClientApp app;
	
	public RepositoryController(ClientApp app) {
		this.app = app;
	}
	
	public void index() {
		
		List<PartRepository> repositories;
		try {
			repositories = new ArrayList<>();
			Registry rg = LocateRegistry.getRegistry();
			String[] repositoryNames = rg.list();
			for (String name : repositoryNames) {
				PartRepository repository = (PartRepository) rg.lookup(name);
				repositories.add(repository);
			}
		} catch (Exception e) {
			repositories = Collections.emptyList();
		}
		app.setCurrentScreen(new RepositoriesListView(this, repositories).render());
	}
	
	public void connectToRepository(String name, String host, int port) {
		try {
			Registry rg = LocateRegistry.getRegistry();
			PartRepository pr = (PartRepository) rg.lookup(name);
			app.setCurrentScreen(new RepositoryView(this, pr).render());
		} catch (Exception e) {
			index();
		}
	}
	
}
