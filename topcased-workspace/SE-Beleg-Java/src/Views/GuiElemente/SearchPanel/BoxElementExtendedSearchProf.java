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
import Models.Datenbank.SqlTableProfs;
import Views.Interfaces.ExtendedSearchBox;
import Views.Interfaces.ExtendedSearchBoxCtrl;

public class BoxElementExtendedSearchProf extends JPanel implements ExtendedSearchBox, MouseListener{
	
	private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtf_email;
    private javax.swing.JButton jb_filterentfernen;
    private javax.swing.JButton jb_suchen;
    private javax.swing.JLabel jl_name;
    private javax.swing.JTextField jtf_name;
	
    private ExtendedSearchBoxCtrl parent;
	
	public BoxElementExtendedSearchProf(ExtendedSearchBoxCtrl parent){
		this.parent = parent;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();
	}
	
	@Override
	public void initComponents() {

		jl_name = new javax.swing.JLabel();
        jtf_name = new javax.swing.JTextField();
        jb_suchen = new javax.swing.JButton();
        jb_filterentfernen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtf_email = new javax.swing.JTextField();

        setPreferredSize(new Dimension(600, 91));

        jl_name.setText("Name:");

        jb_suchen.setText("Suchen");

        jb_filterentfernen.setText("Filter entfernen");

        jLabel1.setText("E-Mail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jb_filterentfernen)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jb_suchen))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jl_name, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        					.addGap(10)
        					.addComponent(jtf_name, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
        					.addGap(52)
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jtf_email, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
        					.addGap(0, 0, Short.MAX_VALUE)))
        			.addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jtf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel1)
        				.addComponent(jtf_email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jl_name))
        			.addGap(26)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jb_suchen)
        				.addComponent(jb_filterentfernen))
        			.addContainerGap(20, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }
	
	
	@Override
	public void setComponentNames() {
		jtf_name.setName(SqlTableProfs.TableNameDotName);
		jtf_email.setName(SqlTableProfs.TableNameDotId);
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
