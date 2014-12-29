package Views.GuiElemente.SearchPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Views.Interfaces.ExtendedContractsSearchBoxCtrl;
import Views.Interfaces.ExtendedSearchBox;
/**
 * Implementiert die erweiterte Suche nach Verträgen.
 */
public class BoxElementExtendedSearchContracts extends JPanel implements ExtendedSearchBox, MouseListener{
	
	    private JButton jb_filterentfernen;
	    private JButton jb_suchen;
	    private JLabel jl_betreuer;
	    private JLabel jl_firma;
	    private JLabel jl_matrikelnr;
	    private JLabel jl_nachname;
	    private JLabel jl_vorname;
	    private JTextField jtf_betreuer;
	    private JTextField jtf_firma;
	    private JTextField jtf_matrikelnr;
	    private JTextField jtf_nachname;
	    private JTextField jtf_vorname;
	    private JCheckBox jcb_praktikumserfolg;
	    private JCheckBox jcb_auslandspraktikum;
	    private JTextField jtf_vertragsid;
	    
	    private ExtendedContractsSearchBoxCtrl controller;
		
	/**
	 * Initialsiert die Box bringt sie aber NICHT zur Anzeige.
	 * @param controller	ExtendedContractsSearchBoxCtrl Objekt, welches auf Nutzerinteraktionen reagiert.
	 */
	public BoxElementExtendedSearchContracts(ExtendedContractsSearchBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();

	}
	
	@Override
	public void initComponents(){
		jl_vorname = new javax.swing.JLabel();
        jl_nachname = new javax.swing.JLabel();
        jl_matrikelnr = new javax.swing.JLabel();
        jtf_vorname = new javax.swing.JTextField();
        jtf_nachname = new javax.swing.JTextField();
        jtf_matrikelnr = new javax.swing.JTextField();
        jb_suchen = new javax.swing.JButton();
        jb_filterentfernen = new javax.swing.JButton();
        jl_firma = new javax.swing.JLabel();
        jtf_firma = new javax.swing.JTextField();
        jl_betreuer = new javax.swing.JLabel();
        jtf_betreuer = new javax.swing.JTextField();

        setPreferredSize(new Dimension(700, 125));

        jl_vorname.setText("Vorname:");

        jl_nachname.setText("Nachname:");

        jl_matrikelnr.setText("Matrikelnr:");

        jb_suchen.setText("Suchen");

        jb_filterentfernen.setText("Filter entfernen");

        jl_firma.setText("Firma:");

        jl_betreuer.setText("Betreuer:");
        
        jcb_praktikumserfolg = new JCheckBox("Praktikumserfolg");
        
        jcb_auslandspraktikum = new JCheckBox("Auslandspraktikum");
        
        JLabel lblVertragsId = new JLabel();
        lblVertragsId.setText("Vertrags ID:");
        
        jtf_vertragsid = new JTextField();


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(75)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jl_vorname)
        				.addComponent(jl_nachname)
        				.addComponent(jl_matrikelnr)
        				.addComponent(jl_betreuer))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jtf_betreuer)
        				.addComponent(jtf_vorname)
        				.addComponent(jtf_nachname)
        				.addComponent(jtf_matrikelnr, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(66)
        					.addComponent(jb_filterentfernen)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jb_suchen))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(55)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jcb_praktikumserfolg)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jcb_auslandspraktikum))
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(jl_firma, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addGap(18)
        								.addComponent(jtf_firma, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
        							.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        								.addComponent(lblVertragsId, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        								.addGap(18)
        								.addComponent(jtf_vertragsid, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))))))
        			.addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jl_vorname)
        						.addComponent(jtf_vorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jl_nachname)
        						.addComponent(jtf_nachname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jl_matrikelnr)
        						.addComponent(jtf_matrikelnr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jtf_firma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(6)
        							.addComponent(jl_firma)))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jcb_praktikumserfolg)
        						.addComponent(jcb_auslandspraktikum))
        					.addGap(7)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblVertragsId)
        						.addComponent(jtf_vertragsid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 3, Short.MAX_VALUE)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(9)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jtf_betreuer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jl_betreuer)))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jb_filterentfernen)
        						.addComponent(jb_suchen))))
        			.addContainerGap(14, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }
	
	
	@Override
	public void setComponentNames() {
		jtf_vorname.setName(SqlTableStudent.TableNameDotVorname);
		jtf_nachname.setName(SqlTableStudent.TableNameDotNachname);

		jtf_vertragsid.setName(SqlTableContracts.TableNameDotPrimaryKey);
		
		jtf_matrikelnr.setName(SqlTableStudent.TableNameDotMatrikelNummer);
		jtf_betreuer.setName(SqlTableProfs.TableNameDotName);//tablenamefk_betreuerdot
		jtf_firma.setName(SqlTableCompanies.TableNameDotFirmenname);  //Zugriff via TableNameDotFirmenname da in Where Clausel nur Zugriff auf Spalte via Tabellenalias.Spaltenname

		jcb_auslandspraktikum.setName("extraAuslandspraktikum");
		jcb_praktikumserfolg.setName("extraPraktikumserfolg");
		
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
				if(component == jcb_auslandspraktikum){
					if(jcb_auslandspraktikum.isSelected())
						controller.setInternationalInternshipFilterOn();
					else
						controller.setInternationalInternshipFilterOff();
				}
				
				if(component == jcb_praktikumserfolg){
					if(jcb_praktikumserfolg.isSelected())
						controller.setInternshipSuccessfulFilterOn();
					else
						controller.setInternshipSuccessfulFilterOff();
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
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_filterentfernen)
			controller.buttonDeleteFilterClicked();
		
		if(e.getComponent() == jb_suchen)
			controller.buttonSearchSpecificClicked();
		
		
	}
	
	@Override
	public void setToolTip() {
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void setComponentValues() {}
	
	@Override
	public void refreshContent() {}
	
	
	
}
