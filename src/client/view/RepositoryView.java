package client.view;

import client.controller.RepositoryController;

import remote.PartRepository;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComponent;

import java.awt.BorderLayout;

public class RepositoryView implements View {
	
	private RepositoryController controller;
	private PartRepository repository;
	private JPanel panel;
	
	public RepositoryView(RepositoryController rc, PartRepository pr) {
		this.controller = rc;
		this.repository = pr;
	}
	
	@Override
	public JComponent render() {
		panel = new JPanel(new BorderLayout());
		
		buildMessage();
		
		JPanel buttons = new JPanel();
		JButton back = new JButton("Escolher outro repositório");
		back.addActionListener(e -> controller.index());
		buttons.add(back);
		
		panel.add(back, BorderLayout.SOUTH);
		return panel;
	}
	
	private void buildMessage() {
		try {
			String repMessage = "Conectado ao repositorio "+repository.getName();
			repMessage += "\nNo host "+repository.getHost();
			repMessage += "\nNa porta "+repository.getPort();
			JTextArea ok = new JTextArea(repMessage);
			panel.add(ok, BorderLayout.CENTER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
