package client.view;

import javax.swing.*;
import java.util.List;
import java.awt.BorderLayout;

import remote.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RepositoriesListView {


	public JComponent render(List<PartRepository> repositories) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel message = new JLabel("Escolha um Repositório: ", SwingConstants.CENTER);
		JTable repTable = new JTable();
		repTable.setModel(new RepositoryTableModel(repositories));
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(repTable);
		JButton connect = new JButton("Conectar");
		JButton exit = new JButton("Sair");
		exit.addActionListener(e -> System.exit(0));
		JPanel buttons = new JPanel();
		buttons.add(connect);
		buttons.add(exit);
		
		panel.add(message, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(buttons, BorderLayout.SOUTH);
		return panel;
	}
	
	
	public static void main(String[] args) {
		try {
			Registry rg = LocateRegistry.getRegistry(1500);
			ServerMaster sm = (ServerMaster) rg.lookup("ServerMaster");
			JFrame teste = new JFrame();
			RepositoriesListView view = new RepositoriesListView();
			teste.add(view.render(sm.getRepositories()));
			teste.pack();
			teste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			teste.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
