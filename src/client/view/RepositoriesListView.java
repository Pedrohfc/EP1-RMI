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
	private String[] repositories;
	private String errorMessage;
	
	public RepositoriesListView(RepositoryController rc, String[] reps) {
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
		//tableData = new RepositoryTableModel(repositories);
		//repTable.setModel(tableData);
		String[][] rowData = new String[repositories.length][1];
		for (int i = 0; i < repositories.length; i++) {
			rowData[i][0] = repositories[i];
		}
		String[] columnNames = {"Nome"};
		repTable = new JTable(rowData, columnNames);
		repTable.setDefaultEditor(Object.class, null);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(repTable);
		panel.add(scroll, BorderLayout.CENTER);
	}
	
	private void buildButtons() {
		JButton connect = new JButton("Conectar");
		connect.addActionListener(e -> {
			int row = repTable.getSelectedRow();
			String name = repositories[row];
			String host = "";
			int port = 0;
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
