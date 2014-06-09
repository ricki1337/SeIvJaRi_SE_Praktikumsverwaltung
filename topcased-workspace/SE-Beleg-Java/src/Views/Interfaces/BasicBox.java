package Views.Interfaces;

import java.util.ArrayList;

import javax.swing.JComponent;

public interface BasicBox {
	public void setComponentNames();
	public void setComponentValues();
	public void setComponentEventHandler();
	
	public void refreshContent();
	public JComponent getJComponent();
	public void initComponents();
}
