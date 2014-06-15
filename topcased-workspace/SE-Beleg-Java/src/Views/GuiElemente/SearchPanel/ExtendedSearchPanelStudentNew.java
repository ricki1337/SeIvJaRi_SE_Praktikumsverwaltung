package Views.GuiElemente.SearchPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Models.Datenbank.SqlTableStudent;
import Views.Interfaces.ExtendedSearchBox;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.ExtendedStudentSearchBoxCtrl;

public class ExtendedSearchPanelStudentNew extends JPanel implements ExtendedSearchBox, MouseListener{
	
	private javax.swing.JLabel jl_studiengruppe;
    private javax.swing.JLabel jl_vertrag;
    private javax.swing.JTextField jtf_studiengruppe;
    private javax.swing.JCheckBox jcb_ohne_vertrag;
    private javax.swing.JCheckBox jcb_mit_vertrag;
    private javax.swing.JButton jb_filterentfernen;
    private javax.swing.JButton jb_suchen;
    private javax.swing.JLabel jl_matrikelnr;
    private javax.swing.JLabel jl_nachname;
    private javax.swing.JLabel jl_vorname;
    private javax.swing.JTextField jtf_matrikelnr;
    private javax.swing.JTextField jtf_nachname;
    private javax.swing.JTextField jtf_vorname;
	
    private ExtendedStudentSearchBoxCtrl controller;
	
	public ExtendedSearchPanelStudentNew(ExtendedStudentSearchBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();
	}
	
	@Override
	public void initComponents() {

		jl_vorname = new javax.swing.JLabel();
        jl_nachname = new javax.swing.JLabel();
        jl_matrikelnr = new javax.swing.JLabel();
        jtf_vorname = new javax.swing.JTextField();
        jtf_nachname = new javax.swing.JTextField();
        jtf_matrikelnr = new javax.swing.JTextField();
        jb_suchen = new javax.swing.JButton();
        jb_filterentfernen = new javax.swing.JButton();
        jl_studiengruppe = new javax.swing.JLabel();
        jtf_studiengruppe = new javax.swing.JTextField();
		 setPreferredSize(new java.awt.Dimension(600, 125));

        jl_vorname.setText("Vorname:");

        jl_nachname.setText("Nachname:");

        jl_matrikelnr.setText("Matrikelnr:");

        jb_suchen.setText("Suchen");

        jb_filterentfernen.setText("Filter entfernen");

        jl_studiengruppe.setText("Studiengruppe:");
        
        jcb_ohne_vertrag = new JCheckBox("ohne Vertrag");
        
        jcb_mit_vertrag = new JCheckBox("mit Vertrag");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(21)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jl_studiengruppe)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_vorname)
        						.addComponent(jl_nachname)
        						.addComponent(jl_matrikelnr))
        					.addGap(33)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        							.addComponent(jtf_nachname, Alignment.LEADING)
        							.addComponent(jtf_vorname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        							.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(jtf_studiengruppe))
        							.addComponent(jtf_matrikelnr, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
        					.addGap(48)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(2)
        							.addComponent(jb_filterentfernen))
        						.addComponent(jcb_ohne_vertrag))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jcb_mit_vertrag, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jb_suchen))))
        			.addGap(86))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_vorname)
        				.addComponent(jtf_vorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jcb_ohne_vertrag)
        				.addComponent(jcb_mit_vertrag))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_nachname)
        				.addComponent(jtf_nachname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_matrikelnr)
        				.addComponent(jtf_matrikelnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_studiengruppe)
        				.addComponent(jtf_studiengruppe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jb_suchen)
        				.addComponent(jb_filterentfernen))
        			.addContainerGap(62, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }
	
	
	@Override
	public void setComponentNames() {
		jtf_vorname.setName(SqlTableStudent.TableNameDotVorname);
		jtf_nachname.setName(SqlTableStudent.TableNameDotNachname);
		jtf_matrikelnr.setName(SqlTableStudent.TableNameDotMatrikelNummer);
		jtf_studiengruppe.setName(SqlTableStudent.TableNameDotStudiengruppe);
		jcb_ohne_vertrag.setName("extraOhneVertragFilter");
		jcb_mit_vertrag.setName("extraMitVertragFilter");
		jb_suchen.setName("extendedSearch");
		jb_filterentfernen.setName("clearSearchFields");
	}

	@Override
	public void setComponentEventHandler() {
		jb_suchen.addMouseListener(this);
		jb_filterentfernen.addMouseListener(this);
	}

	@Override
	public Map<String, Object> getSearchFilter() {
		Map<String, Object> searchFilter = new HashMap<String, Object>();
		for(Component component: getComponents()){
			if(component instanceof JTextField){
				if(((JTextField)component).getName() != null && ((JTextField)component).getText().length() > 0)
					searchFilter.put(((JTextField)component).getName(), ((JTextField)component).getText());
			}
			if(component instanceof JCheckBox){
				if(component == jcb_ohne_vertrag){
					if(((JCheckBox)component).isSelected())
						controller.setHasNoContractFilterOn();
					else
						controller.setHasNoContractFilterOff();
				}
				
				if(component == jcb_mit_vertrag){
					if(((JCheckBox)component).isSelected())
						controller.setHasContractFilterOn();
					else
						controller.setHasContractFilterOff();
				}
			}
			
		}
		return searchFilter;
	}


	@Override
	public JComponent getJComponent() {
		return this;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_filterentfernen)
			controller.buttonDeleteFilterClicked();

		if(e.getComponent() == jb_suchen)
			controller.buttonSearchSpecificClicked();
		
	}
	
	@Override
	public void clearFields() {
		for(Component component: getComponents()){
			if(component instanceof JTextField){
				if(((JTextField)component).getName() != null)
					((JTextField)component).setText("");
			}
			if(component instanceof JCheckBox){
				((JCheckBox)component).setSelected(false);
			}
		}
	}

	@Override
	public void setComponentValues() {}
	
	@Override
	public void refreshContent() {}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	
	
}
