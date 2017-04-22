package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Part extends Remote {

    long getCode() throws RemoteException;

    String getName() throws RemoteException;

    String getDescrition() throws RemoteException;

    /**
     * @return Nome do ropositorio original da peca
     * @throws RemoteException
     */
    String getRepository() throws RemoteException;

    /**
     * @return Se eh uma peca primitiva ou agregacao de pecas
     * @throws RemoteException
     */
    String type() throws RemoteException;

    int getNumDirectSubparts() throws RemoteException;

    int getNumPrimitiveSubparts() throws RemoteException;

    List<Part> getSubparts() throws RemoteException;

    String getInfo() throws RemoteException;

}
