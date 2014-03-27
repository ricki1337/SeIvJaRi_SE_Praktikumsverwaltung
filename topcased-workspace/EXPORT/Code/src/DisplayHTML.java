import java.io.IOException;
import java.io.InputStream;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class DisplayHTML extends JFrame {

	JEditorPane ed1;

	public DisplayHTML(InputStream in) {
		// http://ostermiller.org/convert_java_outputstream_inputstream.html

		ed1 = new JEditorPane();
		ed1.setContentType("text/html");
		ed1.setEditable(false);
		setVisible(true);
		setSize(600, 600);
		add(ed1);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			ed1.read(in, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
