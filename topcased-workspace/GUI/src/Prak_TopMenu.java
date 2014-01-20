import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


//gute doku siehe http://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
public class Prak_TopMenu extends JMenuBar{
	
	//die menuitems
	JMenu mDatei,mDatenbank;
	JButton mStartseite;
	public Prak_TopMenu(final Prakt_MainWindow MainWindow){
		JMenuItem mItem;
		//erster menuepunkt
		mDatei = new JMenu("Datei");
			mDatei.setMnemonic(KeyEvent.VK_F);
			//tooltip...
			//mDatei.getAccessibleContext().setAccessibleDescription("");
		add(mDatei);
		
		//Untermenupunkte
		mItem = new JMenuItem("Beenden");
		mDatei.add(mItem);
		
		
		//zweiter menuepunkt
		mDatenbank = new JMenu("Datenbank");
		mDatenbank.setMnemonic(KeyEvent.VK_D);
			//tooltip...
			//mDatei.getAccessibleContext().setAccessibleDescription("");
		add(mDatenbank);
		//Untermenupunkte
				mItem = new JMenuItem("Datenbankinfo");
				mDatenbank.add(mItem);
				mItem = new JMenuItem("Datenbank wechseln");
				mDatenbank.add(mItem);
				mItem = new JMenuItem("Datenbank sichern");
				mDatenbank.add(mItem);	
		mStartseite = new JButton("Start");
		mStartseite.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.addInternalFrame(new Prakt_Startseite(MainWindow));
				
			
			}});
		mStartseite.setBorderPainted(false);
		mStartseite.setPreferredSize(new Dimension(80,20));
		
		add(Box.createHorizontalGlue());
		add(mStartseite);
	}
}
