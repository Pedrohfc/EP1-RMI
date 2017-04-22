package client.view;

import client.controller.RepositoryController;

import remote.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.util.List;

public class RepositoryView implements View {

    private RepositoryController controller;
    private PartRepository repository;
    private JPanel panel;
    private List<Part> currentPart;
    private JLabel nameLabel, descriptionLabel;
    private JTextField nameTxt, descriptionTxt;
    private JTable subp, partList;

    public RepositoryView(RepositoryController rc, PartRepository pr, List<Part> part) {
        this.controller = rc;
        this.repository = pr;
        this.currentPart = part;
    }

    @Override
    public JComponent render() {
        panel = new JPanel(new BorderLayout());

        buildMessage();
        buildListPanel();
        buildButtons();

        return panel;
    }

    private void buildMessage() {
        try {
            String repMessage = "Conectado ao repositorio " + repository.getName();
            repMessage += "\nNo host " + repository.getHost();
            repMessage += "\nNa porta " + repository.getPort();
            JTextArea ok = new JTextArea(repMessage);
            ok.setEditable(false);
            panel.add(ok, BorderLayout.NORTH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void buildListPanel() {
        JPanel list = new JPanel(new GridLayout(1, 2));
        list.add(buildListParts());
        list.add(buildCurrentPart());
        panel.add(list, BorderLayout.CENTER);
    }

    private JComponent buildListParts() {
        try {
            JPanel repList = new JPanel(new BorderLayout());
            List<Part> parts = repository.getParts();
            String[][] rowData = new String[parts.size()][3];
            String[] columnNames = {"código", "nome", "Subcomponentes"};
            for (int i = 0; i < parts.size(); i++) {
                rowData[i][0] = "" + parts.get(i).getCode();
                rowData[i][1] = parts.get(i).getName();
                rowData[i][2] = "" + parts.get(i).getNumPrimitiveSubparts();
            }
            partList = new JTable(rowData, columnNames);
            partList.setDefaultEditor(Object.class, null);
            JScrollPane scroll = new JScrollPane();
            scroll.getViewport().add(partList);
            repList.add(scroll, BorderLayout.CENTER);

            repList.add(buildAddButton(), BorderLayout.SOUTH);
            return repList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JComponent buildAddButton() {
        JPanel btns = new JPanel(new BorderLayout());

        JButton add = new JButton("Adicionar à peça corrente");
        add.addActionListener(event -> {
            try {
                List<Part> p = repository.getParts();
                int row = this.partList.getSelectedRow();
                Part selected = p.get(row);
                DefaultTableModel model = (DefaultTableModel) subp.getModel();
                currentPart.add(selected);
                String[] rowData = {selected.getName(), selected.getRepository()};
                model.addRow(rowData);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btns.add(add, BorderLayout.WEST);
        
        add = new JButton("Mostrar peça selecionada");
        add.addActionListener((ActionEvent event) -> {
            try {
                List<Part> p = repository.getParts();
                int row = this.partList.getSelectedRow();
                Part selected = p.get(row);
                
                JOptionPane.showMessageDialog(null, selected.getInfo());
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btns.add(add, BorderLayout.EAST);
        
        return btns;
    }

    private JComponent buildCurrentPart() {
        try {
            JPanel partPanel = new JPanel(new BorderLayout());
            JLabel title = new JLabel("Criar nova peça");
            partPanel.add(title, BorderLayout.NORTH);
            JPanel partInfo = new JPanel(new BorderLayout());
            partInfo.add(buildForm(), BorderLayout.NORTH);
            String[] columnNames = {"nome", "repositório"};
            String[][] rowData = new String[currentPart.size()][2];
            for (int i = 0; i < currentPart.size(); i++) {
                rowData[i][0] = currentPart.get(i).getName();
                rowData[i][1] = currentPart.get(i).getRepository();
            }
            subp = new JTable(new DefaultTableModel(rowData, columnNames));
            JScrollPane scroll = new JScrollPane();
            scroll.getViewport().add(subp);
            partInfo.add(scroll, BorderLayout.CENTER);
            partInfo.add(buildPartButtons(), BorderLayout.SOUTH);
            partPanel.add(partInfo, BorderLayout.CENTER);
            return partPanel;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JComponent buildForm() {
        JPanel form = new JPanel(new GridLayout(2, 2));
        nameLabel = new JLabel("Nome:");
        nameTxt = new JTextField();
        descriptionLabel = new JLabel("Descrição:");
        //descriptionTxt = new JTextArea();
        descriptionTxt = new JTextField();
        form.add(nameLabel);
        form.add(nameTxt);
        form.add(descriptionLabel);
        form.add(descriptionTxt);
        return form;
    }

    private JComponent buildPartButtons() {
        JPanel buttons = new JPanel();
        JButton create = new JButton("Criar");
        create.addActionListener(e -> controller.create(nameTxt.getText(), descriptionTxt.getText()));
        JButton erase = new JButton("Limpar");
        erase.addActionListener(e -> {
        	DefaultTableModel model = (DefaultTableModel) subp.getModel();
        	for (int i = currentPart.size()-1; i >= 0; i--) {
        		model.removeRow(i);
        	}
        	currentPart.clear();
        });
        buttons.add(create);
        buttons.add(erase);
        return buttons;
    }

    private void buildButtons() {
        JPanel buttons = new JPanel();
        JButton back = new JButton("Escolher outro repositório");
        back.addActionListener(e -> controller.index());
        buttons.add(back);
        JButton exit = new JButton("Sair");
        exit.addActionListener(e -> System.exit(0));
        buttons.add(exit);
        panel.add(buttons, BorderLayout.SOUTH);
    }
}
