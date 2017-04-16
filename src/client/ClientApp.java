package client;

import javax.swing.JFrame;
import javax.swing.JComponent;

import client.controller.*;

public class ClientApp {
	private JFrame frame;
	
	public ClientApp() {
		frame = new JFrame();
	}
	
	public void startApp() {
		//frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new RepositoryController(this).index();
		frame.setVisible(true);
	}
	
	public void setCurrentScreen(JComponent screen) {
		frame.setContentPane(screen);
		frame.pack();
	}
	
	public static void main(String[] args) {
		new ClientApp().startApp();
	}
}
