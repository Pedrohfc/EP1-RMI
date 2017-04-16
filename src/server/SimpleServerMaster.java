package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.PartRepository;
import remote.ServerMaster;

public class SimpleServerMaster extends UnicastRemoteObject implements ServerMaster {
	List<PartRepository> repositories;

	public SimpleServerMaster() throws RemoteException {
		super();
		this.repositories = new ArrayList<>();
	}
	
	@Override
	public void addRepository(PartRepository repository) {
		repositories.add(repository);
	}
	
	@Override
	public List<PartRepository> getRepositories() {
		return repositories;
	}
	
	public static void main(String[] args) {
		try {
			String serverName = "ServerMaster";
			int port = 1500;
			ServerMaster sm = new SimpleServerMaster();
			Registry rg = LocateRegistry.createRegistry(port);
			rg.bind(serverName, sm);
			System.out.println("Server "+serverName+" started at port "+port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
