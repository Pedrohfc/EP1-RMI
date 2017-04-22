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
    	try {
		    repTable = new JTable();
		    //tableData = new RepositoryTableModel(repositories);
		    //repTable.setModel(tableData);
		    Object[][] rowData = new Object[repositories.size()][2];
		    for (int i = 0; i < repositories.size(); i++) {
		        rowData[i][0] = repositories.get(i).getName();
		        rowData[i][1] = repositories.get(i).getNumParts();
		    }
		    String[] columnNames = {"Nome", "Peças"};
		    repTable = new JTable(rowData, columnNames);
		    repTable.setDefaultEditor(Object.class, null);
		    JScrollPane scroll = new JScrollPane();
		    scroll.getViewport().add(repTable);
		    panel.add(scroll, BorderLayout.CENTER);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }

    private void buildButtons() {
        JButton connect = new JButton("Conectar");
        connect.addActionListener(e -> {
            int row = repTable.getSelectedRow();
            controller.connectToRepository(repositories.get(row));
        });

        JButton exit = new JButton("Sair");
        exit.addActionListener(e -> System.exit(0));

        JPanel buttons = new JPanel();
        buttons.add(connect);
        buttons.add(exit);

        panel.add(buttons, BorderLayout.SOUTH);
    }

}
