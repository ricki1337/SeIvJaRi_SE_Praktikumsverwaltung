package Views.GuiElemente;

import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;

public class AddButton extends JButton{
	
	public AddButton(String buttonText){
		super(buttonText);
	}
	
	public AddButton(String buttonText, String buttonName){
		super(buttonText);
		setButtonName(buttonName);
	}
	
	public AddButton(String buttonText, String buttonName, MouseListener mouseListener){
		super(buttonText);
		setButtonName(buttonName);
		this.addMouseListener(mouseListener);
	}
	
	
	public void setButtonText(String buttonText){
		this.setText(buttonText);
	}
	
	public void setButtonName(String buttonName){
		this.setName(buttonName);
	}

}
