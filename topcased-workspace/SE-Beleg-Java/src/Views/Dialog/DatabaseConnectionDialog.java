package Views.Dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import ConfigParser.Config;
import Models.Datenbank.Database;

public class DatabaseConnectionDialog extends JDialog {
	
    // Variables declaration - do not modify                     
    private javax.swing.JButton jb_abbrechen;
    private javax.swing.JButton jb_verbinden;
    private javax.swing.JLabel jl_caption;
    private javax.swing.JLabel jl_datenbank;
    private javax.swing.JLabel jl_infolabel;
    private javax.swing.JLabel jl_nutzername;
    private javax.swing.JLabel jl_passwort;
    private javax.swing.JPasswordField jpw_passwort;
    private javax.swing.JTextField jtf_datenbank;
    private javax.swing.JTextField jtf_nutzername;
    private String dbhost = "";
    // End of variables declaration          
	
	
    public DatabaseConnectionDialog(String dbhost, Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.dbhost=dbhost;
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
    }
    
    
    
    
    
    private void initComponents() {

        jb_verbinden = new javax.swing.JButton();
        jb_abbrechen = new javax.swing.JButton();
        jl_nutzername = new javax.swing.JLabel();
        jtf_nutzername = new javax.swing.JTextField();
        jl_passwort = new javax.swing.JLabel();
        jl_datenbank = new javax.swing.JLabel();
        jtf_datenbank = new javax.swing.JTextField();
        jl_caption = new javax.swing.JLabel();
        jpw_passwort = new javax.swing.JPasswordField();
        jl_infolabel = new javax.swing.JLabel();

        setTitle("Anmeldung");
        setMinimumSize(new java.awt.Dimension(250, 190));
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        jb_verbinden.setText("Verbinden");
        jb_verbinden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_verbindenActionPerformed(evt);
            }
        });

        jb_abbrechen.setText("Abbrechen");
        jb_abbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_abbrechenActionPerformed(evt);
            }
        });

        jl_nutzername.setText("Nutzername:");



        jl_passwort.setText("Passwort:");

        jl_datenbank.setText("Datenbank:");

        jl_caption.setText("Verbindung zur Datenbak herstellen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 80, Short.MAX_VALUE)
                        .addComponent(jb_verbinden, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_abbrechen))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_passwort)
                            .addComponent(jl_datenbank))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpw_passwort)
                            .addComponent(jtf_datenbank)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_nutzername)
                        .addGap(18, 18, 18)
                        .addComponent(jtf_nutzername))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_caption)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jl_infolabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jb_abbrechen, jb_verbinden});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jl_caption)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_nutzername)
                    .addComponent(jtf_nutzername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_passwort)
                    .addComponent(jpw_passwort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_datenbank)
                    .addComponent(jtf_datenbank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jl_infolabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_abbrechen)
                    .addComponent(jb_verbinden))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(jb_verbinden);
        
        String user=Config.getProperties("User");
        String datenbank=Config.getProperties("Datenbank");
       
        if(user!=null)
        	this.jtf_nutzername.setText(user);

        if(datenbank!=null)
        	this.jtf_datenbank.setText(datenbank);
        
        pack();
    }// </editor-fold>                        

    private void jb_verbindenActionPerformed(ActionEvent evt) {                                             
    	//String dbhost = this.dbhost;
    	String database = this.jtf_datenbank.getText();
		String user = this.jtf_nutzername.getText();
		
		char[] pw = this.jpw_passwort.getPassword();
		String password="";
		for (int i=0; i<pw.length; i++){
			password+=pw[i];
		}

    	Database db = Database.getInstance();
    	

		try {
			db.connect("MySql", dbhost, 3306, user, password, database);
    		Config.setProperties("Datenbank", database);
    		Config.setProperties("User", user);
			
			dispose();
		} catch (Exception e) {
			this.jl_infolabel.setText("BUG: RECONNECT FUNZT NICHT");
			e.printStackTrace();
		}
    	
    }                                            

    private void jb_abbrechenActionPerformed(ActionEvent evt) {                                             
    	System.exit(0);
    }                                            


	

}
