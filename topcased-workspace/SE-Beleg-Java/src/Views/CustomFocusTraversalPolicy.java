package Views;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;

public class CustomFocusTraversalPolicy extends FocusTraversalPolicy {   
	private List<Component> elementList = new ArrayList<Component>();
	
	public void addComponent(Component component) {
		elementList.add(component);
	}
	
	
    public Component getComponentAfter(Container focusCycleRoot,Component aComponent)    {   
        int currentPosition = elementList.indexOf(aComponent);
        currentPosition = (currentPosition + 1) % elementList.size();   
        System.out.println("next choosen");
        return (Component)elementList.get(currentPosition);   
    }   
    public Component getComponentBefore(Container focusCycleRoot,Component aComponent)    { 
        int currentPosition = elementList.indexOf(aComponent);
        currentPosition = (elementList.size() + currentPosition - 1) % elementList.size();   
        return (Component)elementList.get(currentPosition);   
    }
    public Component getFirstComponent(Container cntnr) {
        return (Component)elementList.get(0);
    }
    public Component getLastComponent(Container cntnr) {
        return (Component)elementList.get(elementList.size() - 1);
    }
    public Component getDefaultComponent(Container cntnr) {
        return (Component)elementList.get(0);
    }
}