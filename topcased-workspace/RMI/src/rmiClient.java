import java.rmi.*;

//nutzt externen service
public class rmiClient {
	public rmiClient(String host, String text){
		try{
			rmiInterface service = (rmiInterface) Naming.lookup("//"+host+"/rmiHost");
			String myText = service.toUpper(text);
			System.out.println(myText);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	};
	
	public static void main(String args[]){
		rmiClient client = new rmiClient(args[0],args[1]);
	}

}
