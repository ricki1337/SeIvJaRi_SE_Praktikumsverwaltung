package Views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import Controller.Controller;
import Models.Model;
import Views.Interfaces.BasicBox;
import Views.Interfaces.EditBox;

/**
 * View, welche die Grundlage für alle Box Elemente stellt und die vertikale
 * Liste zum einordnen der Boxen verwaltet.
 */
public class View extends JInternalFrame implements UpdateView {

	protected Model model;
	protected Controller controller;

	private Box contentBox;

	protected ArrayList<BasicBox> listOfallComponentsOnView;

	/**
	 * Initialisiert einen JInternalFrame und erstellt die Liste für die Box
	 * Elemente.
	 * 
	 * @param controller
	 */
	public View(Controller controller) {
		super("", true, true, true, true);

		this.controller = controller;

		listOfallComponentsOnView = new ArrayList<BasicBox>();

		setCloseOperation();
		setCloseOnEscapeKey();
		contentBox = new Box(BoxLayout.Y_AXIS);
		this.add(contentBox);
	}

	/**
	 * Setzt das übergebene Model in einer Klassenvariable.
	 * 
	 * @param model
	 *            Model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Ermöglicht das Schließen der View über die "ESC"-Taste<br>
	 * Meldet das Model von der Database ab und schließt die View.
	 */
	private void setCloseOnEscapeKey() {
		final View instance = this;
		String cancelName = "cancel";
		InputMap inputMap = getRootPane().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
		ActionMap actionMap = getRootPane().getActionMap();
		actionMap.put(cancelName, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				instance.controller.close();
			}
		});
	}

	/**
	 * Ermöglicht das Schließen der View über das Kreuz<br>
	 * Meldet das Model von der Database ab und schließt die View.
	 */
	private void setCloseOperation() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		final JInternalFrame instance = this;
		this.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				model.modelClose();
				instance.dispose();
			}
		});
	}

	/**
	 * Fügt eine {@link Views.Interfaces.BasicBox} der Box-Liste hinzu.
	 * @param component	Eine BasicBox Implementation
	 */
	public void addComponentToView(BasicBox component) {
		JComponent comp = component.getJComponent();
		comp.setAlignmentY(Component.TOP_ALIGNMENT);
		comp.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentBox.add(comp);
		addComponentToInternList(component);
	}

	/**
	 * Fügt eine BasicBox der internen Liste zu. Diese Liste wird für die Aktualisierung genutzt.
	 * @param component	Eine BasicBox Implementation.
	 */
	private void addComponentToInternList(BasicBox component) {
		listOfallComponentsOnView.add(component);
	}

	/**
	 * Zeigt die Box Liste an.
	 */
	public final void display() {
		contentBox.setVisible(true);
		setVisible(true);
		pack();
	}

	@Override
	public void modelHasChanged() {
		for (BasicBox box : listOfallComponentsOnView) {
			box.refreshContent();
		}
	}

	@Override
	public Object[][] getInputValues() {
		Map<String, Object> inputValues = new HashMap<String, Object>();
		for (BasicBox box : listOfallComponentsOnView) {
			if (box instanceof EditBox)
				inputValues.putAll(((EditBox) box).getInputValues());
		}
		Object[][] result = new Object[inputValues.size()][2];
		int index = 0;
		for (Map.Entry<String, Object> listEntry : inputValues.entrySet()) {
			result[index][0] = listEntry.getKey();
			result[index][1] = listEntry.getValue();
			index++;
		}

		return result;
	}
}
