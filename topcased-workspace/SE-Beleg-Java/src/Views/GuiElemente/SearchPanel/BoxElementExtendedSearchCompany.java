package Views.GuiElemente.SearchPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableProfs;
import Views.Interfaces.ExtendedSearchBox;
import Views.Interfaces.ExtendedSearchBoxCtrl;

public class BoxElementExtendedSearchCompany extends JPanel implements ExtendedSearchBox, MouseListener{
	
    private javax.swing.JButton jb_filterentfernen;
    private javax.swing.JButton jb_suchen;
    private javax.swing.JLabel jl_land;
    private javax.swing.JLabel jl_name;
    private javax.swing.JLabel jl_ort;
    private javax.swing.JLabel jl_plz;
    private javax.swing.JTextField jtf_land;
    private javax.swing.JTextField jtf_name;
    private javax.swing.JTextField jtf_ort;
    private javax.swing.JTextField jtf_plz;
	
    private ExtendedSearchBoxCtrl parent;
	
	public BoxElementExtendedSearchCompany(ExtendedSearchBoxCtrl parent){
		this.parent = parent;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();

	}
	
	@Override
	public void initComponents(){

        jl_name = new javax.swing.JLabel();
        jl_ort = new javax.swing.JLabel();
        jl_land = new javax.swing.JLabel();
        jtf_name = new javax.swing.JTextField();
        jtf_ort = new javax.swing.JTextField();
        jtf_land = new javax.swing.JTextField();
        jb_suchen = new javax.swing.JButton();
        jb_filterentfernen = new javax.swing.JButton();
        jl_plz = new javax.swing.JLabel();
        jtf_plz = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(600, 100));

        jl_name.setText("Name:");

        jl_ort.setText("Ort:");

        jl_land.setText("Land:");

        jb_suchen.setText("Suchen");

        jb_filterentfernen.setText("Filter entfernen");

        jl_plz.setText("Plz:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_name)
                    .addComponent(jl_ort)
                    .addComponent(jl_land))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtf_name)
                    .addComponent(jtf_ort)
                    .addComponent(jtf_land, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_plz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtf_plz, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jb_filterentfernen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_suchen)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_name)
                    .addComponent(jtf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_plz)
                    .addComponent(jtf_plz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_ort)
                    .addComponent(jtf_ort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_land)
                    .addComponent(jtf_land, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_suchen)
                    .addComponent(jb_filterentfernen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
      this.setLayout(layout);
    }
	
	
	@Override
	public void setComponentNames() {
		jtf_name.setName(SqlTableCompanies.TableNameDotFirmenname);
		jtf_ort.setName(SqlTableCompanies.TableNameDotOrt);
		jtf_land.setName(SqlTableCompanies.TableNameDotLand);
		jtf_plz.setName(SqlTableCompanies.TableNameDotPLZ);
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
