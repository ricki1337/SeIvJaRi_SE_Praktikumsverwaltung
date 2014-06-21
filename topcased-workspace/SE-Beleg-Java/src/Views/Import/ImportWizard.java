package Views.Import;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

import Controller.ErrorManager;
import Import.CsvImport;
import Models.Datenbank.Database;
import Models.Datenbank.SqlTableStudent;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Dialog.OkDialog;


public class ImportWizard extends JDialog implements ActionListener {
	JFileChooser chooser = new JFileChooser();
	String comboBoxListe[] = {";",",","-"};
	Database db = Database.getInstance();
	
	//Zustandsvariablen
	boolean value_checkbox_zeileignorieren = true;
	String value_combo_seperator = "";
	String value_combo_spalte1 ="";
	String value_combo_spalte2 ="";
	String value_combo_spalte3 ="";
	String value_combo_spalte4 ="";
	String value_combo_spalte5 ="";
	String [] comboliste = new String[] {"Bitte wählen", "Name", "Vorname", "Matrikelnr", "Studiengruppe", "Bibliotheksnr" }; //Wenn die Reihnfolge geändert wird, muss dies auch im Switch case unter Event button importieren angepasst werden
	String file;
	
	DefaultTableModel tablemodel = new DefaultTableModel(comboliste,10);

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
	private javax.swing.JButton button_vorschau;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextArea logconsole;
	private javax.swing.JLabel label_datei;
	private javax.swing.JLabel label_fortschritt;
	private javax.swing.JLabel label_seperator;
	private javax.swing.JPanel panel_dateiauswahl;
	private javax.swing.JScrollPane scrollpane_table;
	private javax.swing.JTable table;
	private javax.swing.JTextField textfield_datei;
	// End of variables declaration  
	
	JComboBox [] comboboxarray;
	
public ImportWizard(){
	initComponents();
	initListeners();
	comboboxarray = new JComboBox[5];
    
	comboboxarray[0]=combo_spalte1;
    comboboxarray[1]=combo_spalte2;
    comboboxarray[2]=combo_spalte3;
    comboboxarray[3]=combo_spalte4;
    comboboxarray[4]=combo_spalte5;
	
    enableComboboxes(false);
}

private void enableComboboxes(boolean value){
	for(int i = 0 ; i<5 ; i++){comboboxarray[i].setEnabled(value);}

}

public void removeAllRows(){
	
	for(int i = tablemodel.getRowCount()-1; i>=0; i--){
		tablemodel.removeRow(i);
	}
	
	
}

private void checkIfColumnsAreSelected(){
	
	for(int i = 0 ; i<5 ; i++){
		if(comboboxarray[i].getSelectedIndex()==0) 
			return;
	}

	button_importieren.setEnabled(true);
}

private void setSelectedItem(String value){
	
	for(int i = 0 ; i<5 ; i++){
		comboboxarray[i].setEditable(true);
		comboboxarray[i].setSelectedItem(value);
		comboboxarray[i].setEditable(false);
	}
	
}

private void initListeners(){
	
	button_datei.addActionListener(this);
	button_importieren.addActionListener(this);
	button_abbrechen.addActionListener(this);
	button_vorschau.addActionListener(this);
	
	combo_spalte1.addActionListener(this);
	combo_spalte2.addActionListener(this);
	combo_spalte3.addActionListener(this);
	combo_spalte4.addActionListener(this);
	combo_spalte5.addActionListener(this);
	
	
	
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
    button_vorschau = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();
    jScrollPane1 = new javax.swing.JScrollPane();
    logconsole = new javax.swing.JTextArea();
    


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

    checkbox_zeileignorieren.setSelected(false);
    checkbox_zeileignorieren.setEnabled(false);
    checkbox_zeileignorieren.setText("1. Zeile ignorieren");
    

    label_seperator.setText("Seperator");

    combo_seperator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ";", ",", "-", ":" }));

    scrollpane_table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    table.setModel(tablemodel);
    
    table.getTableHeader().setReorderingAllowed(false);
    scrollpane_table.setViewportView(table);

    button_importieren.setText("Importieren");
    button_importieren.setEnabled(false);

    button_abbrechen.setText("Abbrechen");

    label_fortschritt.setText("");

    combo_spalte1.setModel(new javax.swing.DefaultComboBoxModel(comboliste));
    combo_spalte2.setModel(new javax.swing.DefaultComboBoxModel(comboliste));
    combo_spalte3.setModel(new javax.swing.DefaultComboBoxModel(comboliste));
    combo_spalte3.setName(""); // NOI18N
    combo_spalte4.setModel(new javax.swing.DefaultComboBoxModel(comboliste));
    combo_spalte5.setModel(new javax.swing.DefaultComboBoxModel(comboliste));

    button_vorschau.setText("Vorschau");
    button_vorschau.setEnabled(false);
    
    jScrollPane1.setBorder(null);
    jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.lightGray));

    logconsole.setEditable(false);
    logconsole.setColumns(20);
    logconsole.setRows(5);
    logconsole.setText("1. Bitte Datei auswählen und ein Trennzeichen wählen.\n2. Per Klick auf \"Vorschau\" Datensätze anzeigen lassen\n3. Spaltenbezeichnungen via ComboBox wählen.\n4. Datensätze Importieren");
    jScrollPane1.setViewportView(logconsole);

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
                    .addComponent(button_vorschau))
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
            .addComponent(button_vorschau)
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

	//BUTTON "DATEI"
	   if (e.getSource() == button_datei){
		   chooser.showOpenDialog(null);
		   try{
			   file=chooser.getSelectedFile().toString();
			   textfield_datei.setText(file);
			   button_vorschau.setEnabled(true);
		   }
		   catch(NullPointerException exeption){
				ErrorManager errorManager = new ErrorManager(exeption, "Die Datei \"" + file +"\" konnte nicht gefunden werden");
		   }
		   
		   this.enableComboboxes(false);
		   this.combo_seperator.setEnabled(true);
		   this.checkbox_zeileignorieren.setEnabled(false);
		   this.button_vorschau.setEnabled(true); 
		   
      }
	   
	//BUTTON "IMPORTIEREN"  	
	   if (e.getSource() == button_importieren){
		   
		   // Zuordnungsarray erstellen 
		   char [] zuordnung = new char [5];
		   for(int i = 0 ; i<5 ; i++){
			   switch(comboboxarray[i].getSelectedIndex()){
				
			   		case 1:zuordnung[i]='n'; //Name
							break; 
			   		case 2:zuordnung[i]='v'; //Vorname
							break; 
			   		case 3:zuordnung[i]='m'; //Matrikelnr
							break; 
			   		case 4:zuordnung[i]='s'; //Studiengruppe
							break; 
			   		case 5:zuordnung[i]='b'; //Bibliotheksnr
							break; 
				}	
			}
		   
		   int MatrNr = 0, rowIndexInit = 0, rowcount = table.getModel().getRowCount();
		   String Name = "", Firstname = "", Email = "", StGr = "", Note = ""; 
		   boolean error=false;
		   
		   if (checkbox_zeileignorieren.isSelected()){rowIndexInit=1;}
		   
		   for (int rowIndex=rowIndexInit; rowIndex < rowcount; rowIndex++){
			   error=false;
			   
			   for (int columnIndex = 0; columnIndex <5 ; columnIndex++){
				   try{
					   switch(zuordnung[columnIndex]){
					   		case 'n': Name = (String) table.getModel().getValueAt(rowIndex, columnIndex);//Name
									break; 
					   		case 'v': Firstname = (String) table.getModel().getValueAt(rowIndex, columnIndex);//Vorname
									break; 
					   		case 'm': MatrNr = Integer.parseInt(table.getModel().getValueAt(rowIndex, columnIndex).toString());//Matrikelnr
									break; 
					   		case 's': StGr = (String) table.getModel().getValueAt(rowIndex, columnIndex);//Studiengruppe
									break; 
					   		case 'b': Email = new String("s" + (String) table.getModel().getValueAt(rowIndex, columnIndex) + "@htw-dresden.de");//Bibliotheksnr wird zur Emailadresse erweitert
									break; 
					   }
				   }catch  (NumberFormatException exeption){
					   ErrorManager errorManager = new ErrorManager(exeption, "Bitte überprüfen Sie die Zuordnung der Spaltenbezeichnungen.");
					   logconsole.setText(logconsole.getText() + "Zeile: " + rowIndex + " konnte nicht importiert werden. [ Spalte : " + columnIndex + ", Wert; "+ table.getModel().getValueAt(rowIndex, columnIndex) + " ]" );
					   error=true;
				   }                    
					   

			   }
				   
				   
				   if(!error){
					  //Query bauen
					  
					   String sql = "INSERT INTO " +  SqlTableStudent.tableName + " VALUES (" + MatrNr + ",\"" + Name + "\",\"" + Firstname +"\",\""+ Email + "\",\"" + StGr + "\",\"" + Note + "\")";
					   this.logconsole.setText(logconsole.getText()+"\n"+sql);
					   db.setQueryandInformModels(sql);

				   }
			}
		   this.button_importieren.setEnabled(false);
		   this.button_abbrechen.setText("Fertig");
	   }
	   
	   //BUTTON "ABBRECHEN"
	   if (e.getSource() == button_abbrechen){
		   dispose();
	   }
	   
	   //BUTTON "VORSCHAU"
	   if (e.getSource() == button_vorschau){
		   value_combo_seperator=combo_seperator.getSelectedItem().toString();
		   DefaultTableModel datamodel = new DefaultTableModel(0,5);
		   CsvImport csvimport = new CsvImport(file,value_combo_seperator,false);
		   try {
				datamodel=csvimport.parseIt();
			} catch (IOException exception) {
				
				new ErrorManager(exception,"Datei konnte nicht gelesen werden.");
			}			
		   catch (ArrayIndexOutOfBoundsException exception) {
			  
			   new ErrorManager(exception, "Bitte überprüfen Sie ob das richtige Trennzeichen bzw die richtige Datei ausgewählte  wurde.");
			}
		   logconsole.setText("Es wurden " + csvimport.getcount() + " Datensätze gefunden.\n");
		  
		   if (csvimport.getcount()!=0){	// nur wenn Datensätze gefunden wurden nächste Arbeitschritte freischalten 
			   table.setModel(datamodel);
			   this.enableComboboxes(true);
			   this.combo_seperator.setEnabled(false);
			   this.checkbox_zeileignorieren.setEnabled(true);
			   this.button_vorschau.setEnabled(false);
		   }
	   }
	   
	 //COMBOS
	   if (e.getSource() == combo_spalte1){
		   int selection = combo_spalte1.getSelectedIndex();
		   table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(comboliste[selection]); 
		   checkIfColumnsAreSelected();
		   repaint();
	   }
	   
	   if (e.getSource() == combo_spalte2){
		   int selection = combo_spalte2.getSelectedIndex();
		   table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue(comboliste[selection]); 
		   checkIfColumnsAreSelected();
		   repaint();
	   }
	   
	   if (e.getSource() == combo_spalte3){
		   int selection = combo_spalte3.getSelectedIndex();
		   table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(comboliste[selection]);  
		   checkIfColumnsAreSelected();
		   repaint();
	   }
	   
	   if (e.getSource() == combo_spalte4){
		   int selection = combo_spalte4.getSelectedIndex();
		   table.getTableHeader().getColumnModel().getColumn(3).setHeaderValue(comboliste[selection]); 
		   checkIfColumnsAreSelected();
		   repaint();
	   }
	   
	   if (e.getSource() == combo_spalte5){
		   int selection = combo_spalte5.getSelectedIndex();
		   table.getTableHeader().getColumnModel().getColumn(4).setHeaderValue(comboliste[selection]); 
		   checkIfColumnsAreSelected();
		   repaint();
	   }

	
}
}
