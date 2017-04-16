package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerMaster extends Remote {

	void addRepository(PartRepository repository) throws RemoteException;
	List<PartRepository> getRepositories() throws RemoteException;
}
