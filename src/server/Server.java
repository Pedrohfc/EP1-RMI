package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.*;

public class Server {

    private String serverName;
    private int port;
    private String host;

    public Server(String name, int port) {
        this.serverName = name;
        this.port = port;
        this.host = "127.0.0.1";
    }

    public void startServer() {
        try {
            PartRepository pr = new SimplePartRepository(serverName, host, port);
            Registry rg = LocateRegistry.getRegistry();
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
        new Server(args[0], Integer.parseInt(args[1])).startServer();
    }

}
