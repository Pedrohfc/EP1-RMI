package client.view;

import client.controller.*;
import remote.Part;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.util.List;

public class PartView implements View {
	
	private RepositoryController controller;
	private Part part;
	private JTable subparts;
	private JPanel panel;
	
	
	public PartView(RepositoryController c, Part p) {
		this.controller = c;
		this.part = p;
	}
	
	@Override
	public JComponent render() {
		panel = new JPanel(new BorderLayout());
		buildInfo();
		buildSubparts();
		return panel;
	}
	
	private void buildInfo() {
		try {
			JPanel info = new JPanel(new GridLayout(5,1));
			
			JLabel name = new JLabel("Nome: "+part.getName());
			JLabel code = new JLabel("Código: "+part.getCode());
			JLabel repository = new JLabel("Repositório: "+part.getRepository());
			JLabel type = new JLabel("Tipo: "+part.type());
			JLabel description = new JLabel("Descrição: "+part.getDescrition());
			
			info.add(name);
			info.add(code);
			info.add(repository);
			info.add(type);
			info.add(description);
			
			panel.add(info, BorderLayout.NORTH);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void buildSubparts() {
		try {
			String[] columnNames = {"Nome", "Repositório", "Código", "Subcomponentes"};
			List<Part> subp = part.getSubparts();
			Object[][] rowData = new Object[subp.size()][4];
			for (int i = 0; i < subp.size(); i++) {
				Part p = subp.get(i);
				rowData[i][0] = p.getName();
				rowData[i][1] = p.getRepository();
				rowData[i][2] = p.getCode();
				rowData[i][3] = p.getNumDirectSubparts();
			}
		
			subparts = new JTable(rowData, columnNames);
			subparts.setDefaultEditor(Object.class, null);
			JScrollPane scroll = new JScrollPane();
			scroll.getViewport().add(subparts);
			JPanel title = new JPanel(new BorderLayout());
			title.add(new JLabel("Subcomponentes:"), BorderLayout.NORTH);
			title.add(scroll, BorderLayout.SOUTH);
			panel.add(title, BorderLayout.CENTER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
