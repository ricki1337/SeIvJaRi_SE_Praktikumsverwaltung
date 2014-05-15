package Views.GuiElemente.SearchPanel;

import java.awt.GridLayout;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Views.ListView;

public class CompanyExtendedSearchPanel extends ExtendedSearchPanel{
	
	public CompanyExtendedSearchPanel(EventListener controller,ListView view){
		super(controller, view);
	}

	@Override
	protected void addSearchFields() {
		addTextfield("Firmenname:","Name");
		addTextfield("Straﬂe:","Street");
		addTextfield("PLZ:","ZIP");
		addTextfield("Ort:","Town");
		addSearchButton();
	}
	
	
}
