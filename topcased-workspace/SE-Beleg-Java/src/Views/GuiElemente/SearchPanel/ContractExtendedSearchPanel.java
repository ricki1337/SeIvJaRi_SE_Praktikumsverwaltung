package Views.GuiElemente.SearchPanel;

import java.awt.GridLayout;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Views.ListView;

public class ContractExtendedSearchPanel extends ExtendedSearchPanel{
	
	public ContractExtendedSearchPanel(EventListener controller,ListView view){
		super(controller, view);
	}

	@Override
	protected void addSearchFields() {
		addTextfield("Matrikelnr.:","s.matrnr");
		addTextfield("Vorname:","s.firstname");
		addTextfield("Nachname:","s.name");
		addTextfield("Studiengruppe:","s.stgr");
		addTextfield("Firmenname:","co.name");
		addTextfield("Bericht:","bericht");
		addTextfield("Zeugnis:","zeugnis");
		addTextfield("Empfehlung:","empfehlung");
		addSearchButton();
	}
	
	
}
