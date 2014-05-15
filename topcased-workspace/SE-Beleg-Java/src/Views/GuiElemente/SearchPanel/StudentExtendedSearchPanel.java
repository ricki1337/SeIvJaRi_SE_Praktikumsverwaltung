package Views.GuiElemente.SearchPanel;

import java.awt.GridLayout;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Views.ListView;

public class StudentExtendedSearchPanel extends ExtendedSearchPanel{
	
	public StudentExtendedSearchPanel(EventListener controller,ListView view){
		super(controller, view);
	}

	@Override
	protected void addSearchFields() {
		addTextfield("Matrikelnr.:","MatrNr");
		addTextfield("Vorname:","Firstname");
		addTextfield("Nachname:","Name");
		addTextfield("E-Mail:","Email");
		addTextfield("Studiengruppe:","StGr");
		addSearchButton();
	}
	
	
}
