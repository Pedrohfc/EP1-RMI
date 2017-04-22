package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import remote.Part;

public class SimplePart extends UnicastRemoteObject implements Part {

	private long code;
	private String name;
	private String description;
	private List<Part> subparts;
	private String repository;
	
	public SimplePart(long c, String n, String d, List<Part> sp, String r) throws RemoteException {
		this.code = c;
		this.name = n;
		this.description = d;
		this.subparts = sp;
		this.repository = r;
	}
	
	@Override
	public long getCode() throws RemoteException {
		return this.code;
	}
	
	@Override
	public String getDescrition() throws RemoteException {
		return this.description;
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public int getNumDirectSubparts() throws RemoteException {
		return this.subparts.size();
	}

	@Override
	public int getNumPrimitiveSubparts() throws RemoteException {
		return this.subparts.size();
	}

	@Override
	public String getRepository() throws RemoteException {
		return this.repository;
	}

	@Override
	public List<Part> getSubparts() throws RemoteException {
		return this.subparts;
	}

	@Override
	public String type() throws RemoteException {
		if (this.subparts.size() == 0)
			return "Primitive";
		return "Set of subparts";
	}

	@Override
	public String getInfo() throws RemoteException {
		StringBuilder info = new StringBuilder();
		info.append("Code: ");
		info.append(this.code+"\n");
		info.append("Name: ");
		info.append(this.name+"\n");
		info.append("Description: ");
		info.append(this.description+"\n");
		info.append("Subparts: ");
		for (Part p : subparts) {
			info.append("Name: ");
			info.append(p.getName()+" ");
			info.append("Repository: ");
			info.append(p.getRepository()+"\n");
		}
		return info.toString();
	}

}
