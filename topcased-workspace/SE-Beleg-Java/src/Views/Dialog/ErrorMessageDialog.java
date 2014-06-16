package Views.Dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Views.Interfaces.BasicBox;
import Views.Interfaces.ErrorManagerCtrl;

public class ErrorMessageDialog extends JDialog implements BasicBox, MouseListener{
	private final JPanel contentPanel = new JPanel();
	private JTextPane jtxtp_errorMessage;
	private JTextPane jtxtp_details;
	JLabel scrollPaneTmp;
	private JScrollPane scrollPane;
	private GroupLayout gl_contentPanel;
	private ErrorManagerCtrl controller;

	
	private JButton repeatButton;
	private JButton okButton;
	private JToggleButton tglbtn_details;
	
	public ErrorMessageDialog(ErrorManagerCtrl controller){
		super();
		this.controller = controller;		
		initComponents();
		setComponentEventHandler();
	}
	
	public void setErrorMessage(String errorMessage){
		if(errorMessage != null)
			jtxtp_errorMessage.setText(errorMessage);
	}
	
	public void setErrorDetails(Exception error){
		if(error == null) return;
		String errorDetails = new String();
		for(StackTraceElement stackElement:error.getStackTrace()){
			errorDetails += stackElement.toString() + '\n';
		}
		jtxtp_details.setText(errorDetails);
		
	}
	
	public void showErrorDialog() {
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == okButton)
			controller.buttonOkClicked();
		
		if(e.getComponent() == repeatButton)
			controller.buttonRepeatClicked();
		
		if(e.getComponent() == tglbtn_details){
			if(jtxtp_details.getText().length() > 0 && tglbtn_details.isSelected()){
				gl_contentPanel.replace(scrollPaneTmp, scrollPane);
				setBounds(100, 100, 505, 548);
				this.getLayout().layoutContainer(this);
			}
			
			if(!tglbtn_details.isSelected()){
				gl_contentPanel.replace(scrollPane, scrollPaneTmp);
				setBounds(100, 100, 505, 290);
				this.getLayout().layoutContainer(this);
			}
			
		}
			
	}

	
	@Override
	public void setComponentEventHandler() {
		okButton.addMouseListener(this);
		repeatButton.addMouseListener(this);
		tglbtn_details.addMouseListener(this);
		
	}

	@Override
	public void refreshContent() {}

	@Override
	public JComponent getJComponent() {
		return null;
	}

	@Override
	public void initComponents() {
		setModal(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 505, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
			jtxtp_errorMessage = new JTextPane();
			jtxtp_errorMessage.setEditable(false);
		
		
		JLabel lblNewLabel = new JLabel("Ein Fehler ist aufgetreten:");
		
		tglbtn_details = new JToggleButton("Details anzeigen");
		
		scrollPane = new JScrollPane();
		scrollPaneTmp = new JLabel(" ");
		gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneTmp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addComponent(jtxtp_errorMessage, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING)
						.addComponent(tglbtn_details, Alignment.LEADING))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(11)
					.addComponent(jtxtp_errorMessage, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(tglbtn_details)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPaneTmp, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
		);
		
		jtxtp_details = new JTextPane();
		jtxtp_details.setVisible(true);
		scrollPane.setViewportView(jtxtp_details);
		scrollPane.setVisible(true);
		
		jtxtp_details.setEditable(false);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				repeatButton = new JButton("Aktion wiederholen");
				buttonPane.add(repeatButton);
				getRootPane().setDefaultButton(repeatButton);
			}
			{
				okButton = new JButton("Ok");
				buttonPane.add(okButton);
			}
		}
		contentPanel.setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void setComponentNames() {}

	@Override
	public void setComponentValues() {}


}
