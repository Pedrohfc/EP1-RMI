package client.controller;

import client.ClientApp;
import client.view.*;
import remote.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Collections;

public class RepositoryController {
	ClientApp app;
	
	public RepositoryController(ClientApp app) {
		this.app = app;
	}
	
	public void index() {
		
		ServerMaster server = getServerMaster();
		List<PartRepository> repositories;
		try {
			repositories = server.getRepositories();
		} catch (RemoteException e) {
			repositories = Collections.emptyList();
		}
		app.setCurrentScreen(new RepositoriesListView(this, repositories).render());
	}
	
	public void connectToRepository(String name, String host, int port) {
		try {
			Registry rg = LocateRegistry.getRegistry(host,port);
			PartRepository pr = (PartRepository) rg.lookup(name);
			app.setCurrentScreen(new RepositoryView(this, pr).render());
		} catch (Exception e) {
			index();
		}
	}
	
	public ServerMaster getServerMaster() {
		String name = "ServerMaster";
		String host = "127.0.0.1";
		int port = 1500;
		try {
			Registry rg = LocateRegistry.getRegistry(host,port);
			ServerMaster server = (ServerMaster) rg.lookup(name);
			return server;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
