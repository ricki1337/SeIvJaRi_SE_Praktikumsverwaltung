package Controller;

import java.io.File;

import Mail.createPDF;
import Mail.MailSender;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Models.Interfaces.Observer;
import Views.View;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSendMail;
import Views.GuiElemente.BoxElementBottomNaviProgress;
import Views.GuiElemente.BoxElementMailing;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.MailBox;
import Views.Interfaces.MailBoxCtrl;
import Views.Interfaces.NaviAbortSendMailBoxCtrl;
import Views.Interfaces.NaviProgressBoxCtrl;

public class Mailing extends Controller implements 		BasicBoxCtrl, 
														MailBoxCtrl, 
														Runnable, 
														NaviProgressBoxCtrl, 
														NaviAbortSendMailBoxCtrl,
														Observer{

	private String sqlQueryString = "SELECT " +
								SqlTableContracts.TableNameDotPrimaryKey + " as ID," +
								SqlTableStudent.TableNameDotVorname + " as Vorname, "  +
								SqlTableStudent.TableNameDotNachname + " as Nachname, " +
								SqlTableStudent.TableNameDotMatrikelNummer + " as 'Matrikelnr.', " +
								SqlTableContracts.TableNameDotFK_Betreuer + " as Betreuer, " +
								SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
								SqlTableCompanies.TableNameDotFirmenname + " as Firmenname, " +
								SqlTableContracts.TableNameDotBericht + " as Bericht, " +
								SqlTableContracts.TableNameDotZeugnis + " as Zeugnis, " +
								SqlTableProfs.TableNameDotId + " as email " +
							"FROM " + SqlTableContracts.tableNameWithAlias + 
									" JOIN " + SqlTableStudent.tableNameWithAlias + " ON " + SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotPrimaryKey +
									" JOIN " + SqlTableCompanies.tableNameWithAlias + " ON " + SqlTableContracts.TableNameDotFK_Firma + " = "+ SqlTableCompanies.TableNameDotPrimaryKey + 
									" JOIN " + SqlTableProfs.tableNameWithAlias + " ON " + SqlTableProfs.TableNameDotPrimaryKey + " = " + SqlTableContracts.TableNameDotFK_Betreuer;
	
	private View view;
	protected Models.ListModel model;
	private MailBox boxMail;
	private BoxElementBottomNaviProgress progressBox;
	
	private String tmpPathForPDF;
	
	private Object primaryKeyValues;
	private String primaryKey;
	
	private int hundretPercentValueForProgressBox=0;
	private int nullPercentValueForProgressBox=0;
	private int currentPercentValueForProgressBox=0;
	
	private Thread mailingThread;
	
	/**
	 * Standardkonstruktor von {@link Mailing} ist nicht erlaubt.
	 */
	protected Mailing(){}
	
	/**
	 * Initialisiert die Mailansicht auf Grundlage der übergebenen Primärschlüssel.<br>
	 * Erstellt den Mailing-Thread, welcher die Mails letztendlich verschickt.<br>
	 * Erstellt das Datenmodel auf Grundlage der übergebenen Schlüsselpaare.
	 * 
	 * @param primaryKey		Eine Referenz einer SqlTable-Definition.
	 * @param primaryKeyValues	Der Wert für primaryKey, auf welche gefiltert wird.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public Mailing(String primaryKey, Object primaryKeyValues){
		super();
		setPrimaryKey(primaryKey);
		setPrimaryKeyValues(primaryKeyValues);
		configurePdfPath();
		setListModel();
		mailingThread = new Thread(this);
		setView(view = new Views.View(this));
		
		setElements();
	}

	/**
	 * Setzt den internen primaryKey.
	 * @param primaryKey	Wert aus einer SqlTable-Definition.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setPrimaryKey(String primaryKey) {
		if(primaryKey == null)
			throw new IllegalArgumentException();
		this.primaryKey = primaryKey;
	}
	
	/**
	 * Gibt den gesetzten primaryKey zurück.
	 * @return	primaryKey.
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}
	
	/**
	 * Setzt die internen primaryKeyValues, welche zum primaryKey gehören.
	 * @param primaryKeyValues	gesetzte primaryKeyValues.	
	 */
	public void setPrimaryKeyValues(Object primaryKeyValues) {
		if(primaryKeyValues == null)
			throw new IllegalArgumentException();
		this.primaryKeyValues = primaryKeyValues;
	}

	/**
	 * Gibt die gesetzten primaryKeyValues zurück.
	 * @return gesetzte primaryKeyValues.
	 */
	public Object getPrimaryKeyValues() {
		return primaryKeyValues;
	}

	/**
	 * Initialisiert das Datenmodel auf Grundlage des übergebenen primaryKey und der primaryKeyValues.<br>
	 * Fügt die Spalte "sendMail" an den Anfang des Models hinzu.
	 */
	protected void setListModel() {
		model = new Models.ListModel(sqlQueryString,SqlTableContracts.tableName,getPrimaryKey());
		setModelFilter();
		model.setResult();
		model.addColumnAtBegin("sendMail", (boolean)true);
		setModel(model);
	}
	
	/**
	 * Fügt die gesetzten Filter dem Model hinzu.<br>
	 * Grundlage der Filter sind die übergebenen Werte von primaryKey und primaryKeyValues.
	 */
	protected void setModelFilter() {
		Object primaryKeyValues = getPrimaryKeyValues();
		String primaryKey = getPrimaryKey();
		
		if(primaryKeyValues instanceof Object[]){		
			for(Object value:(Object[])getPrimaryKeyValues()){
				if(primaryKey.equals(SqlTableStudent.TableNameDotMatrikelNummer)|| primaryKey.equals(SqlTableContracts.TableNameDotPrimaryKey) || primaryKey.equals(SqlTableCompanies.TableNameDotPrimaryKey)){
					model.setOrFilter(primaryKey, new IntFilter(value.toString()));
				}
				
				if(primaryKey.equals(SqlTableProfs.TableNameDotPrimaryKey)){
					model.setOrFilter(primaryKey, new StringFilter(value));
				}
			}
		} else{
			if(primaryKey.equals(SqlTableStudent.TableNameDotMatrikelNummer)|| primaryKey.equals(SqlTableContracts.TableNameDotPrimaryKey) || primaryKey.equals(SqlTableCompanies.TableNameDotPrimaryKey)){
				model.setOrFilter(primaryKey, new IntFilter(primaryKeyValues.toString()));
			}
			
			if(primaryKey.equals(SqlTableProfs.TableNameDotPrimaryKey)){
				model.setOrFilter(primaryKey, new StringFilter(primaryKeyValues));
			}
		}
	}
	
	/**
	 * erstellt den Dateipfad für die temporäre Ablage der PDF für den Anhang der Mail.
	 */
	protected void configurePdfPath(){
		String path = System.getProperty("user.dir");
		
		char [] pathCharArray;
		pathCharArray = path.toCharArray();
		
		for(int i=0; i<pathCharArray.length; i++){
			// '\' wird zu '/', da der Pfad sonst nicht akzeptiert wird
			// 92 = ascii code für '\'
        	if (pathCharArray[i]==92){
        		pathCharArray[i]='/';}

        }
		path = String.valueOf(pathCharArray);
		path = path + "/anerkennung.pdf";
		tmpPathForPDF = path;
	}
	
	/**
	 * Löscht die temporär abgelegte PDF Datei.
	 * 
	 * @param path	Pfad zur PDF.
	 */
	protected void deletePdfFromLocalSystem(String path){
		File file = new File(path);  
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * Erstellt die Datenbasis für die Anzeige der View.<br> 
	 * @return	2D-Array mit den Daten für die View.
	 */
	protected String[][] createArrayForMailing(){
		String[][] array = new String[model.getTableRowCount()][9]; 
		try {
			for(int i=0;i<model.getTableRowCount();i++){
				array[i][0]= model.getStringValueFromPosition(i, "Vorname");
				array[i][1]= model.getStringValueFromPosition(i, "Nachname");
				array[i][2]= model.getStringValueFromPosition(i, "Matrikelnr.");
				array[i][3]= model.getStringValueFromPosition(i, "Betreuer");
				array[i][4]= model.getStringValueFromPosition(i, "Studiengruppe");
				array[i][5]= model.getStringValueFromPosition(i, "Firmenname");
				array[i][6]= model.getStringValueFromPosition(i, "Bericht");
				array[i][7]= model.getStringValueFromPosition(i, "Zeugnis");
				array[i][8]= getLocalPartOfEmailAdress(model.getStringValueFromPosition(i, "email"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * Separiert den Localpart einer Emailadresse.<br>
	 * 
	 * @param emailAdress	Emailadresse, von welcher der Localpart separiert werden soll.
	 * @return	Localpart der übergebenen Emailadresse.
	 */
	public String getLocalPartOfEmailAdress(String emailAdress){
		if(emailAdress == null) return null;
		
		String localPartOfEmailAdress = new String(emailAdress);
		
		if(localPartOfEmailAdress.contains("@")){
			int atPosition = localPartOfEmailAdress.indexOf("@");
			if(atPosition != -1)
				localPartOfEmailAdress = localPartOfEmailAdress.substring(0, atPosition);
		}
			
		return localPartOfEmailAdress;	
	}
	
	@Override
	public void display() {
		view.display();
	}
	
	@Override
	public void setElements() {
		view.setTitle("E-Mail senden");
		
		boxMail = new BoxElementMailing(this);
		view.addComponentToView(boxMail);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		progressBox = new BoxElementBottomNaviProgress(this);
		navi.addBoxToLeftSide(progressBox);
		navi.addBoxToRightSide(new BoxElementBottomNaviAbortSendMail(this));
		view.addComponentToView(navi);
	}
	
	@Override
	public void buttonSendMailClicked() {
		if(mailingThread.isAlive()) return;
		if(mailingThread.getState().equals(Thread.State.TERMINATED))
			mailingThread = new Thread(this);
		mailingThread.start();
    }
	
	@Override
	public void buttonAbortClicked() {
		if(mailingThread.isAlive()){
			mailingThread.suspend();
		}
		view.dispose();
		model.modelClose();
	}

	@Override
	public String[][] getMailData() {
		return createArrayForMailing();
	}

	@Override
	public void setSendFlag(int index, boolean status) {
		model.setValueAtPosition(index, "sendMail", status);
	}


	@Override
	public void setBerichtFlag(int index, boolean status) {
		model.setValueAtPosition(index, "Bericht", status);
	}

	@Override
	public void setZeugnisFlag(int index, boolean status) {
		model.setValueAtPosition(index, "Zeugnis", status);
	}
	
	/**
	 * Versendet die Mails in einem separatem Thread.
	 */
	@Override
	public void run() {
		String absender = boxMail.getSenderEmailAdress();
		char[] pw = boxMail.getSenderEmailPassword();
        createPDF ps = new createPDF();
        try {
        	nullPercentValueForProgressBox = 0;
        	currentPercentValueForProgressBox = 0;
        	for(int i=0;i<model.getTableRowCount();i++)
        		if(model.getBooleanValueFromPosition(i, "sendMail"))
        			hundretPercentValueForProgressBox++;
        	progressBox.setComponentValues();
        	boxMail.setComponentValues();
        	MailSender mailer = new MailSender(this);
        	String queryString = new String();
        	mailer.connect(absender, pw);
        	
        	for(int i=0;i<model.getTableRowCount();i++){
				if(model.getBooleanValueFromPosition(i, "sendMail")){
					currentPercentValueForProgressBox++;
				    ps.createPdf(	tmpPathForPDF,
									model.getBooleanValueFromPosition(i, "Bericht"),
									model.getBooleanValueFromPosition(i, "Zeugnis"),
									model.getStringValueFromPosition(i, "Vorname"),
									model.getStringValueFromPosition(i, "Nachname"),
									model.getStringValueFromPosition(i, "Matrikelnr."),
									model.getStringValueFromPosition(i, "Studiengruppe"),
									model.getStringValueFromPosition(i, "Betreuer"),
									model.getStringValueFromPosition(i, "Firmenname")
								); 
				    
				    boolean sendStatus = mailer.sendMail(boxMail.getRecipientEmailAdress(i));

				    boxMail.setMailSend(i, sendStatus);
					if(sendStatus){
						queryString = "update "+ SqlTableContracts.tableName + " set " +
								SqlTableContracts.Bericht + " = " + model.getBooleanValueFromPosition(i, "Bericht") + ", " +
								SqlTableContracts.Zeugnis + " = " + model.getBooleanValueFromPosition(i, "Zeugnis") + 
								" where "+ SqlTableContracts.Id + " = " + model.getStringValueFromPosition(i, "ID") +"; ";
						model.updateDatabase(queryString);
						progressBox.refreshContent();
					}
					deletePdfFromLocalSystem(tmpPathForPDF);
				}
        	}
        } catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				buttonSendMailClicked();
		}
	}

	@Override
	public int getValueEqualToNullPercent() {
		return nullPercentValueForProgressBox;
	}

	@Override
	public int getValueEqualToHundretPercent() {
		return hundretPercentValueForProgressBox;
	}

	@Override
	public int getCurrentValue() {
		return currentPercentValueForProgressBox;
	}

	@Override
	public void refresh(String[] infoAboutChanged) {
		boxMail.setMailingStatus(infoAboutChanged[0]);
	}

}
