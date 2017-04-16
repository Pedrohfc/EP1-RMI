package client.view;

import java.util.List;
import javax.swing.table.*;

import remote.PartRepository;

public class RepositoryTableModel extends AbstractTableModel {
	
	List<PartRepository> repositories;

	public RepositoryTableModel(List<PartRepository> reps) {
		this.repositories = reps;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public int getRowCount() {
		return repositories.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PartRepository repository = repositories.get(rowIndex);
		try {
			switch (columnIndex) {
				case 0:
					return repository.getName();
				case 1:
					return repository.getHost();
				case 2:
					return repository.getPort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return "Repositório";
			case 1:
				return "Host";
			case 2:
				return "Porta";
		}
		return "";
	}

}
