

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.ListController;

//checkboxes einfügen : Erste Zeile ignorieren
// "importieren" button
//"abbrechen" button
//Reihnfolge der Spalten angeben
//error konsole einfügen, ausgabe der Anzahl und nr der fehlerhaften spalten

 


public class ImportWizard extends JDialog implements ActionListener {
JFileChooser chooser = new JFileChooser();
JTextField textfield = new JTextField();
JButton okbutton = new JButton("csv Datei wählen");
JButton abbrechen = new JButton("Abbrechen");
JCheckBox ceckBox = new JCheckBox("1. Zeile ignorieren?");
JCheckBox ceckBox2 = new JCheckBox("2");
JCheckBox ceckBox3 = new JCheckBox("3");
JCheckBox ceckBox4 = new JCheckBox("4");
JLabel label = new JLabel("Datei: ");

String comboBoxListe[] = {";",",","-"};
JComboBox seperator = new JComboBox(comboBoxListe);


// Variables declaration - do not modify                     
private javax.swing.JButton button_abbrechen;
private javax.swing.JButton button_datei;
private javax.swing.JButton button_importieren;
private javax.swing.JCheckBox checkbox_zeileignorieren;
private javax.swing.JComboBox combo_seperator;
private javax.swing.JComboBox combo_spalte1;
private javax.swing.JComboBox combo_spalte2;
private javax.swing.JComboBox combo_spalte3;
private javax.swing.JComboBox combo_spalte4;
private javax.swing.JComboBox combo_spalte5;
private javax.swing.JButton jButton1;
private javax.swing.JScrollPane jScrollPane1;
private javax.swing.JSeparator jSeparator1;
private javax.swing.JTextArea jTextArea1;
private javax.swing.JLabel label_datei;
private javax.swing.JLabel label_fortschritt;
private javax.swing.JLabel label_seperator;
private javax.swing.JPanel panel_dateiauswahl;
private javax.swing.JScrollPane scrollpane_table;
private javax.swing.JTable table;
private javax.swing.JTextField textfield_datei;
// End of variables declaration                  
	
public ImportWizard(){
	initComponents();
}

private void initComponents() {

    panel_dateiauswahl = new javax.swing.JPanel();
    label_datei = new javax.swing.JLabel();
    textfield_datei = new javax.swing.JTextField();
    button_datei = new javax.swing.JButton();
    checkbox_zeileignorieren = new javax.swing.JCheckBox();
    label_seperator = new javax.swing.JLabel();
    combo_seperator = new javax.swing.JComboBox();
    scrollpane_table = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();
    button_importieren = new javax.swing.JButton();
    button_abbrechen = new javax.swing.JButton();
    label_fortschritt = new javax.swing.JLabel();
    combo_spalte1 = new javax.swing.JComboBox();
    combo_spalte2 = new javax.swing.JComboBox();
    combo_spalte3 = new javax.swing.JComboBox();
    combo_spalte4 = new javax.swing.JComboBox();
    combo_spalte5 = new javax.swing.JComboBox();
    jButton1 = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    label_datei.setText("Datei:");

    button_datei.setText("Datei wählen");

    javax.swing.GroupLayout panel_dateiauswahlLayout = new javax.swing.GroupLayout(panel_dateiauswahl);
    panel_dateiauswahl.setLayout(panel_dateiauswahlLayout);
    panel_dateiauswahlLayout.setHorizontalGroup(
        panel_dateiauswahlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panel_dateiauswahlLayout.createSequentialGroup()
            .addComponent(label_datei)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textfield_datei)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_datei))
    );
    panel_dateiauswahlLayout.setVerticalGroup(
        panel_dateiauswahlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panel_dateiauswahlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(label_datei)
            .addComponent(textfield_datei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(button_datei))
    );

    checkbox_zeileignorieren.setSelected(true);
    checkbox_zeileignorieren.setText("1. Zeile ignorieren");

    label_seperator.setText("Seperator");

    combo_seperator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ";", ",", "-", ":" }));

    scrollpane_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    table.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {
            "Name", "Vorname", "Matrikelnr", "Bibliotheksnr", "Studiengruppe"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    table.getTableHeader().setReorderingAllowed(false);
    scrollpane_table.setViewportView(table);

    button_importieren.setText("Importieren");
    button_importieren.setEnabled(false);

    button_abbrechen.setText("Abbrechen");

    label_fortschritt.setText("Image Fortschritt");

    combo_spalte1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Name", "Vorname", "Matrikelnr", "Studiengruppe", "Bibliotheksnr" }));

    combo_spalte2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vorname", "Name", "Matrikelnr", "Studiengruppe", "Bibliotheksnr" }));

    combo_spalte3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matrikelnr", "Vorname", "Name", "Studiengruppe", "Bibliotheksnr" }));
    combo_spalte3.setName(""); // NOI18N

    combo_spalte4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bibliotheksnr", "Studiengruppe", "Matrikelnr", "Vorname", "Name" }));

    combo_spalte5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Studiengruppe", "Bibliotheksnr", "Matrikelnr", "Vorname", "Name" }));

    jButton1.setText("Vorschau");

    jScrollPane1.setBorder(null);
    jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Error Log", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.lightGray));

    jTextArea1.setEditable(false);
    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jTextArea1.setText("fdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\nf\nf\nf\nf\nf\nf\nf\nf\n\n\nff");
    jScrollPane1.setViewportView(jTextArea1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(scrollpane_table)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addComponent(jScrollPane1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_dateiauswahl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(label_fortschritt)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_importieren)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button_abbrechen))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(combo_spalte1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(combo_spalte2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(combo_spalte3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(combo_spalte4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(combo_spalte5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(checkbox_zeileignorieren)
                            .addGap(18, 18, 18)
                            .addComponent(label_seperator, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(combo_seperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panel_dateiauswahl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(checkbox_zeileignorieren)
                .addComponent(label_seperator)
                .addComponent(combo_seperator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15, 15, 15)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_spalte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_spalte2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_spalte4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_spalte5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(combo_spalte3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(scrollpane_table, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(button_importieren)
                .addComponent(button_abbrechen)
                .addComponent(label_fortschritt))
            .addContainerGap())
    );

    pack();
}// </editor-fold>     



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	   if (e.getSource() == okbutton){
		   chooser.showOpenDialog(null);
		   textfield.setText(chooser.getSelectedFile().toString());
      }
	
}


	}
