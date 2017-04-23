package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

import remote.*;

public class Server {

    private String serverName;
    private int port;
    private String host;

    public Server(String name, String host) {
        this.serverName = name;
        this.port = 1099;
        //this.host = "127.0.0.1";
        this.host = host;
    }

    public void startServer() {
        try {
            PartRepository pr = new SimplePartRepository(serverName, host, port);
            Registry rg = LocateRegistry.getRegistry(host);
            rg.bind(serverName, pr);

            System.out.println("Server " + serverName + " started at port " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> this.closeServer()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeServer() {
        try {
            Registry rg = LocateRegistry.getRegistry();
            rg.unbind(serverName);
            System.out.println("Closing the repository");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(args[0], args[1]).startServer();
    }

}
