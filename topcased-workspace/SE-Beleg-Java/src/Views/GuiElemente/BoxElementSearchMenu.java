package Views.GuiElemente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ConfigParser.Debug;
import Views.Interfaces.SearchBox;
import Views.Interfaces.SearchBoxCtrl;

/**
 * Implementiert eine SearchBox zum Suchen von Datensätzen und für die Neuanlage von Datensätzen.
 */
public class BoxElementSearchMenu extends JPanel implements SearchBox,MouseListener, DocumentListener, ActionListener{
	 	private javax.swing.JButton jb_erweitertesuche;
	    private javax.swing.JButton jb_neuanlegen;
	    private javax.swing.JLabel jl_anzdaten;
	    private javax.swing.JLabel jl_suchen;
	    private javax.swing.JTextField jtf_anzdaten;
	    private javax.swing.JTextField jtf_suchen;
	    
	    private SearchBoxCtrl controller;

	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	SearchBoxCtrl Objekt, welches die Nutzereingaben verarbeitet.
	 */
    public BoxElementSearchMenu(SearchBoxCtrl controller){
    	super();
    	this.controller = controller;
    	initComponents();
    	setComponentNames();
    	setComponentValues();
    	setComponentEventHandler();
    	setToolTip();
    }

    @Override
	public void initComponents() {

        jl_suchen = new javax.swing.JLabel();
        jtf_suchen = new javax.swing.JTextField();
        jb_erweitertesuche = new javax.swing.JButton();
        jb_neuanlegen = new javax.swing.JButton();
        jl_anzdaten = new javax.swing.JLabel();
        
        jtf_anzdaten = new javax.swing.JTextField();
        
        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(600, 45));

        jl_suchen.setText("Suchen:");

        jb_erweitertesuche.setText("Erweiterte Suche");

        jb_neuanlegen.setText("Neu anlegen");
        

        jl_anzdaten.setText("Anz. Datens\u00e4tze");
        
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_suchen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtf_suchen, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_erweitertesuche)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_neuanlegen, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl_anzdaten)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtf_anzdaten, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_suchen)
                    .addComponent(jtf_suchen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_erweitertesuche)
                    .addComponent(jb_neuanlegen)
                    .addComponent(jl_anzdaten)
                    .addComponent(jtf_anzdaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }
	
	@Override
	public void setComponentNames() {
		jb_neuanlegen.setName("anlegen");
		jtf_anzdaten.setName("anzDatensaetze");
		jb_erweitertesuche.setName("displayExtendedSearchPanel");
	}
	
	@Override
	public void setComponentValues() {
		jtf_anzdaten.setText(String.valueOf(controller.getSqlRecordLimit()));
		
	}
	
	@Override
	public void setComponentEventHandler() {
		jb_neuanlegen.addMouseListener(this);
		jtf_suchen.getDocument().addDocumentListener(this);
		jtf_anzdaten.addActionListener(this);
		jb_erweitertesuche.addMouseListener(this);		
	}
	
	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		int curserPos = jtf_suchen.getSelectionStart();
		controller.searchFieldChanged();
		jtf_suchen.setSelectionStart(curserPos);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		int curserPos = jtf_suchen.getSelectionStart();
		controller.searchFieldChanged();
		jtf_suchen.setSelectionStart(curserPos);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		int curserPos = jtf_suchen.getSelectionStart();
		controller.searchFieldChanged();
		jtf_suchen.setSelectionStart(curserPos);
	}

	@Override
	public void refreshContent() {
		jtf_anzdaten.setText(String.valueOf(controller.getSqlRecordLimit()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JTextField && (JTextField)(e.getSource()) == jtf_anzdaten){
				controller.RecordLimitFieldChanged();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_neuanlegen)
			controller.buttonAddNewDataClicked();
		
		if(e.getComponent() == jb_erweitertesuche)
			controller.buttonShowExtendedSearchClicked();
		
	}
	
	@Override
	public int getValueOfRecordLimitField() {
		int valueOfRecordLimitField = Integer.parseInt(jtf_anzdaten.getText());
		if(valueOfRecordLimitField <=0){
			valueOfRecordLimitField = 20;
			jtf_anzdaten.setText(String.valueOf(controller.getSqlRecordLimit()));
		}
		return valueOfRecordLimitField;
	}

	@Override
	public String getValueOfSearchField() {
		return jtf_suchen.getText();
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
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
	
}
