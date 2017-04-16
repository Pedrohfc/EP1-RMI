package client.view;

import javax.swing.*;
import java.util.List;
import java.awt.BorderLayout;

import remote.*;
import client.controller.RepositoryController;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RepositoriesListView implements View {

	private RepositoryController controller;
	private JPanel panel;
	private JTable repTable;
	private RepositoryTableModel tableData;
	private List<PartRepository> repositories;
	private String errorMessage;
	
	public RepositoriesListView(RepositoryController rc, List<PartRepository> reps) {
		this.controller = rc;
		this.repositories = reps;
	}

	@Override
	public JComponent render() {
		panel = new JPanel(new BorderLayout());
		
		JLabel message = new JLabel("Escolha um Repositório: ", SwingConstants.CENTER);
		
		panel.add(message, BorderLayout.NORTH);
		
		buildTable();
		buildButtons();
		
		return panel;
	}
	
	private void buildTable() {
		repTable = new JTable();
		tableData = new RepositoryTableModel(repositories);
		repTable.setModel(tableData);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(repTable);
		panel.add(scroll, BorderLayout.CENTER);
	}
	
	private void buildButtons() {
		JButton connect = new JButton("Conectar");
		connect.addActionListener(e -> {
			int row = repTable.getSelectedRow();
			String name = (String) tableData.getValueAt(row,0);
			String host = (String) tableData.getValueAt(row,1);
			int port = (int) tableData.getValueAt(row,2);
			controller.connectToRepository(name, host, port);
		});
		
		JButton exit = new JButton("Sair");
		exit.addActionListener(e -> System.exit(0));
		
		JPanel buttons = new JPanel();
		buttons.add(connect);
		buttons.add(exit);
		
		panel.add(buttons, BorderLayout.SOUTH);
	}
	
}
