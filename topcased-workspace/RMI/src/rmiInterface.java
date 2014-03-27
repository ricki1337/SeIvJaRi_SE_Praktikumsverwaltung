
//interface muss auf client und host seite als .class vorliegen
public interface rmiInterface extends java.rmi.Remote{
	public String toUpper(String text) throws java.rmi.RemoteException;
}
