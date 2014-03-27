import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

//muss laufen um den service anzusprechen
public class rmiHost extends UnicastRemoteObject implements rmiInterface{
	public rmiHost() throws RemoteException{//constructor, ruft UnicastRemoteObject constr.
		super();		
	}
	
	public String toUpper(String text){//implementierter service des rmiInterface
		return text.toUpperCase();		
	}
	
	public static void main(String args[]){
		System.setSecurityManager(new RMISecurityManager(){
			public void checkConnect(String host, int port){};
			public void checkAccept(String host, int port){};
			
		});
		try{
			
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);//start
			
			rmiHost host = new rmiHost();
			Naming.rebind("//"+args[0]+"/rmiHost",host);//anmeldung des service
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
