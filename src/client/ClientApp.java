package client;

import javax.swing.JFrame;
import javax.swing.JComponent;

import javax.swing.UIManager.*;
import javax.swing.UIManager;

import client.controller.*;

public class ClientApp {

    private JFrame frame;

    public ClientApp() {
        frame = new JFrame();
    }

    public void startApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new RepositoryController(this).server();
        frame.setVisible(true);
    }

    public void setCurrentScreen(JComponent screen) {
        frame.setContentPane(screen);
        frame.pack();
    }

    public static void main(String[] args) {
    	try {
    		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    			if (info.getName().equals("Nimbus")) {
    				UIManager.setLookAndFeel(info.getClassName());
    				break;
    			}
    		}
    	} catch (Exception e) {
    		// Nimbus nao disponivel :(
    	}
        new ClientApp().startApp();
    }
}
