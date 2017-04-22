package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {

    String getName() throws RemoteException;

    String getHost() throws RemoteException;

    int getPort() throws RemoteException;

    int getNumParts() throws RemoteException;

    List<Part> getParts() throws RemoteException;

    Part getPart(long cod) throws RemoteException;

    /**
     * Cria uma nova peca no repositorio
     *
     * @param nome
     * @param descricao
     * @param subparts - Lista de pecas que compoem a nova peca
     * @throws RemoteException
     */
    void addPart(String nome, String descricao, List<Part> subparts) throws RemoteException;
}
