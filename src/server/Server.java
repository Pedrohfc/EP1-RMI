package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.*;

public class Server {

	public static void main(String[] args) {
		try {
			String serverName = args[0];
			int port = Integer.parseInt(args[1]);
			String host = "127.0.0.1";
			PartRepository pr = new SimplePartRepository(serverName, host, port);
			Registry rg = LocateRegistry.createRegistry(port);
			rg.bind(serverName, pr);
			
			System.out.println("Server "+serverName+" started at port "+port);
			
			Registry server = LocateRegistry.getRegistry(host, 1500);
			ServerMaster sm = (ServerMaster) server.lookup("ServerMaster");
			sm.addRepository(pr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
