package client.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import client.controller.RepositoryController;

public class ServerView implements View {

	private RepositoryController controller;
	private JLabel hostLabel, portLabel;
	private JTextField hostTxt, portTxt;
	private JButton connect;
	
	public ServerView(RepositoryController c) {
		this.controller = c;
	}
	
	@Override
	public JComponent render() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		int columns = 15;
		c.insets = new Insets(5,5,5,5);
		
		hostLabel = new JLabel("Host:");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(hostLabel, c);
		
		hostTxt = new JTextField(columns);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(hostTxt, c);
		
		portLabel = new JLabel("Porta:");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(portLabel,c);
		
		portTxt = new JTextField(columns);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(portTxt,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		panel.add(buildButton(), c);
		return panel;
	}
	
	
	private JComponent buildButton() {
		connect = new JButton("Conectar");
		connect.addActionListener(e -> {
			controller.connectServer(hostTxt.getText(), portTxt.getText());
		});
		
		return connect;
	}
}
