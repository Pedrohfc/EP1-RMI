package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import remote.Part;
import remote.PartRepository;

public class SimplePartRepository extends UnicastRemoteObject implements PartRepository {
	
	private static final long serialVersionUID = 8480705212859205173L;
	
	private List<Part> parts;
	private long nextCode;
	private String repositoryName;

	public SimplePartRepository(String name) throws RemoteException {
		super();
		parts = new ArrayList<>();
		nextCode = 1;
		repositoryName = name;
	}

	@Override
	public void addPart(String name, String description, List<Part> p) throws RemoteException {
		Part part = new SimplePart(nextCode, name, description, p, repositoryName);
		parts.add(part);
		nextCode++;
	}

	@Override
	public String getName() throws RemoteException {
		return this.repositoryName;
	}

	@Override
	public int getNumParts() throws RemoteException {
		return parts.size();
	}

	@Override
	public Part getPart(long code) throws RemoteException {
		for (Part p : parts) {
			if (p.getCode() == code)
				return p;
		}
		return null;
	}

	@Override
	public List<Part> getParts() throws RemoteException {
		return this.parts;
	}

}
