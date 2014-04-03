import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;


import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


//gute doku siehe http://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
public class Prak_TopMenu extends JMenuBar{
	
	//die menuitems
	JMenu mDatei,mDatenbank;
	
	List<String> mListTextButton = Arrays.asList("Studenten", "Verträge", "Firmen","Professor");
	JButton[] mButtonMenu;
	
	
	public Prak_TopMenu(final Prakt_MainWindow mWindow){
		
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
		
		//Buttons für die 4 Menüs aktivieren (Student,Vertrag,Firma,Professor)  initialisieren
		//Exception ist eher unglücklich ... bessere möglichkeiten?
		add(Box.createHorizontalGlue());
		mButtonMenu = new JButton[mListTextButton.size()];
		for (int i=0;i<mListTextButton.size();i++) {
		
			mButtonMenu[i] = new JButton(mListTextButton.get(i));
			mButtonMenu[i].setName(mListTextButton.get(i));
			mButtonMenu[i].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				JButton button = (JButton)ae.getSource();
	            String txt = button.getText();
				//System.out.println(txt);
			try {
				mWindow.addInternalFrame(Prakt_CreatePane.createInternalFrame(mWindow,txt));
			}catch(Exception e){System.out.println("Fehler beim Erkennen des Buttons.");}
			
			}});
		mButtonMenu[i].setBorderPainted(false);
		mButtonMenu[i].setPreferredSize(new Dimension(95,20));
		
		
		add(mButtonMenu[i]);
		}
	}

}

//SwingAction Class
//class ExitAction extends AbstractAction
//{
//  {
//    putValue( Action.NAME,                        "Student" );
//    putValue( Action.DISPLAYED_MNEMONIC_INDEX_KEY, 0 );
//  }
//
//  @Override public void actionPerformed( ActionEvent e )
//  {
//    MainWindow.addInternalFrame(new Prakt_ViewStudent());
//  }
//}