package Views.GuiElemente.SearchPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableStudent;
import Views.Interfaces.ExtendedSearchBox;
import Views.Interfaces.ExtendedSearchBoxCtrl;

public class BoxElementExtendedSearchContracts extends JPanel implements ExtendedSearchBox, MouseListener{
	
    private javax.swing.JButton jb_filterentfernen;
    private javax.swing.JButton jb_suchen;
    private javax.swing.JLabel jl_betreuer;
    private javax.swing.JLabel jl_firma;
    private javax.swing.JLabel jl_matrikelnr;
    private javax.swing.JLabel jl_nachname;
    private javax.swing.JLabel jl_vorname;
    private javax.swing.JTextField jtf_betreuer;
    private javax.swing.JTextField jtf_firma;
    private javax.swing.JTextField jtf_matrikelnr;
    private javax.swing.JTextField jtf_nachname;
    private javax.swing.JTextField jtf_vorname;
	
    private ExtendedSearchBoxCtrl parent;
	
	public BoxElementExtendedSearchContracts(ExtendedSearchBoxCtrl parent){
		this.parent = parent;
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

        setPreferredSize(new java.awt.Dimension(600, 100));

        jl_vorname.setText("Vorname:");

        jl_nachname.setText("Nachname:");

        jl_matrikelnr.setText("Matrikelnr:");

        jb_suchen.setText("Suchen");

        jb_filterentfernen.setText("Filter entfernen");

        jl_firma.setText("Firma:");

        jl_betreuer.setText("Betreuer:");


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_vorname)
                    .addComponent(jl_nachname)
                    .addComponent(jl_matrikelnr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtf_vorname)
                    .addComponent(jtf_nachname)
                    .addComponent(jtf_matrikelnr, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jb_filterentfernen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_suchen))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_betreuer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_betreuer))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_firma)
                                .addGap(18, 18, 18)
                                .addComponent(jtf_firma, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_vorname)
                    .addComponent(jtf_vorname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_firma)
                    .addComponent(jtf_firma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nachname)
                    .addComponent(jtf_nachname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_betreuer)
                    .addComponent(jtf_betreuer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_matrikelnr)
                    .addComponent(jtf_matrikelnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_suchen)
                    .addComponent(jb_filterentfernen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
      this.setLayout(layout);
    }
	
	
	@Override
	public void setComponentNames() {
		jtf_vorname.setName(SqlTableStudent.TableNameDotVorname);
		jtf_nachname.setName(SqlTableStudent.TableNameDotNachname);
		
		jtf_matrikelnr.setName(SqlTableStudent.TableNameDotMatrikelNummer);
		jtf_betreuer.setName(SqlTableContracts.TableNameDotFK_Betreuer);//tablenamefk_betreuerdot
		jtf_firma.setName(SqlTableCompanies.TableNameDotFirmenname);  //Zugriff via TableNameDotFirmenname da in Where Clausel nur Zugriff auf Spalte via Tabellenalias.Spaltenname

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
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_filterentfernen)
			parent.buttonDeleteFilterClicked();
		
		if(e.getComponent() == jb_suchen)
			parent.buttonSearchSpecificClicked();
		
		
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
