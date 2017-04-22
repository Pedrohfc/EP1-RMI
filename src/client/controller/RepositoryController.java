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
		
		String[] repositories;
		try {
			repositories = registry.list();
			
		} catch (Exception e) {
			repositories = null;
		}
		app.setCurrentScreen(new RepositoriesListView(this, repositories).render());
	}
	
	public void connectToRepository(String name, String host, int port) {
		try {
			currentRepository = (PartRepository) registry.lookup(name);
			app.setCurrentScreen(new RepositoryView(this, currentRepository,currentPart).render());
		} catch (Exception e) {
			e.printStackTrace();
			index();
			JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao servidor");
		}
	}
	
	public void create(String name, String description) {
		try {
			currentRepository.addPart(name, description, currentPart);
			currentPart = new ArrayList<>();
			app.setCurrentScreen(new RepositoryView(this, currentRepository,currentPart).render());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
