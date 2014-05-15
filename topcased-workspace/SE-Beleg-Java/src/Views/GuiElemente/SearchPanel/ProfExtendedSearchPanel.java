package Views.GuiElemente.SearchPanel;

import java.awt.GridLayout;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Views.ListView;

public class ProfExtendedSearchPanel extends ExtendedSearchPanel{
	
	public ProfExtendedSearchPanel(EventListener controller,ListView view){
		super(controller, view);
	}

	@Override
	protected void addSearchFields() {
		addTextfield("Name:","Name");
		addTextfield("E-Mail:","NameID");

		addSearchButton();
	}
	
	
}
