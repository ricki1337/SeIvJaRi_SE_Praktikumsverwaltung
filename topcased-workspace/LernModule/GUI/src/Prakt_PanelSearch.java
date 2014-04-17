import javax.swing.JLabel;
import javax.swing.JPanel;


public class Prakt_PanelSearch extends JPanel{
	
	public Prakt_PanelSearch(Prakt_ViewBasis mWindow) {
		
		JLabel label = new JLabel("Suchen nach:");
		add(label);
		setVisible(true);
		mWindow.setSize(mWindow.getWidth(),mWindow.getHeight()+100);
		
	}

}

