import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Prakt_CreatePane extends JInternalFrame{
	



	public static final JInternalFrame createInternalFrame (Prakt_MainWindow mWindow,String mButtonHit) {
		
		switch(mButtonHit) {
		
		case "Studenten":
			
			return new Prakt_ViewStudent(mWindow, -1);
		case "Firmen":
			return new Prakt_ViewFirmen();
		case "Professor":
			return new Prakt_ViewBetreuer();
		case "Verträge":
			return  new  Prakt_ViewVertrag();
		}
		
	//das ist scheisse ... mir fällt aber gerade nix anderes ein ...	
	return null;

	}

}
