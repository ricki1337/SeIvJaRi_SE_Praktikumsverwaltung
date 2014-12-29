package Views.Dialog;



import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Praktikumsverwaltung.Praktikumsverwaltung;

/**
 * 	Implementiert einen modalen Dialog, welcher eine Nachricht anzeigen kann.
 */
public class OkDialog extends javax.swing.JDialog {

	    private javax.swing.JLabel jl_message;
	    private javax.swing.JButton okButton;
	
	    private int returnStatus = RET_CANCEL;
	    public static final int RET_CANCEL = 0;
	
	    public static final int RET_OK = 1;
	
	    private String message;
    
    /**
     * Erstellt einen Dialog mit einer übergebenen Nachricht. Der Dialog verfügt über einen "OK" Button.
     * @param message	Nachricht, welche angezeigt werden soll.
     */
    public OkDialog(String message) {
        
    	super(Praktikumsverwaltung.getFrame(), true);
    	this.message = message;
        initComponents();
		this.setLocationRelativeTo(null);
		this.setTitle("Hinweis");
		this.setVisible(true);
        
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }
    
    /**
     * Erstellt alle Dialog-Elemente und positioniert sie.
     */
    private void initComponents() {

        okButton = new javax.swing.JButton();
        jl_message = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jl_message.setText(this.message);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_message)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addContainerGap())
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        doClose(RET_OK);
    }                                        

    /**
     * Schließt den Dialog.
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {                             
        doClose(RET_CANCEL);
    }                            
    
    /**
     * Schließt den Dialog und setzt den Returnstatusflag.
     */
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }



    
}
