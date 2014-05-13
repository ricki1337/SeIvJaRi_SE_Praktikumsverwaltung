package Views.Import;


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

 


public class CopyOfImportWizard extends JDialog implements ActionListener {
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

	
public CopyOfImportWizard(){

	
	okbutton.addActionListener(this);
	
	GroupLayout layout = new GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);

	layout.setHorizontalGroup(layout.createSequentialGroup()
	    .addComponent(label)
	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addComponent(textfield)
	        .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(ceckBox))
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(seperator))))
	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addComponent(okbutton)
	        .addComponent(abbrechen))
	);
	layout.linkSize(SwingConstants.HORIZONTAL, okbutton, abbrechen);

	layout.setVerticalGroup(layout.createSequentialGroup()
	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        .addComponent(label)
	        .addComponent(textfield)
	        .addComponent(okbutton))
	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(ceckBox)
	                .addComponent(seperator)))
	        .addComponent(abbrechen))
	);
	

	


}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	   if (e.getSource() == okbutton){
		   chooser.showOpenDialog(null);
		   textfield.setText(chooser.getSelectedFile().toString());
      }
	
}


	}
