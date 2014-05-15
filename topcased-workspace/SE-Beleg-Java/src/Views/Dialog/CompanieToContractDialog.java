package Views.Dialog;

import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

import Controller.CompanieList;
import Controller.ContractSingle;

public class CompanieToContractDialog extends JDialog{
	
	private ContractSingle controller;
	private CompanieList companieController;
	
	
	public CompanieToContractDialog(ContractSingle controller){
		super();
		this.controller = controller;
		JDesktopPane dpane = new JDesktopPane();
		companieController = new CompanieList();
		companieController.display();
		JInternalFrame iFrame = companieController.getDisplayedFrame();
		dpane.add(iFrame);
		dpane.setVisible(true);
		add(dpane);
		setLocation(100, 100);
		setAlwaysOnTop(true);
		pack();
		setVisible(true);
	}
	
	
	
//	public String getSelectedCompanieId(){}
}
