package Controller;

import java.io.File;

import Mail.createPDF;
import Mail.MailSender;
import Models.Datenbank.Observer;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Views.ViewNew;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSendMail;
import Views.GuiElemente.BoxElementBottomNaviProgress;
import Views.GuiElemente.BoxElementMail;
import Views.GuiElemente.BoxElementMailing;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.MailBox;
import Views.Interfaces.MailBoxCtrl;
import Views.Interfaces.NaviAbortSendMailBoxCtrl;
import Views.Interfaces.NaviProgressBoxCtrl;

public class Mailing extends ControllerNew implements 	BasicBoxCtrl, 
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
	
	private ViewNew view;
	protected Models.ListModel model;
	private MailBox boxMail;
	private BoxElementBottomNaviProgress progressBox;
	
	private String tmpPathForPDF;
	
	private Object primaryKeyValues;
	private String primaryKey;
	
	private int hundretPercentValue=0;
	private int nullPercentValue=0;
	private int currentPercentValue=0;
	
	private Thread mailingThread;
	
	protected Mailing(){}
	
	public Mailing(String primaryKey, Object primaryKeyValues){
		super();
		setPrimaryKey(primaryKey);
		setPrimaryKeyValues(primaryKeyValues);
		configurePdfPath();
		setListModel();
		mailingThread = new Thread(this);
		setView(view = new Views.ViewNew(this));
		
		setElements();
	}

	public void setPrimaryKey(String primaryKey) {
		if(primaryKey == null)
			throw new IllegalArgumentException();
		this.primaryKey = primaryKey;
	}


	public void setPrimaryKeyValues(Object primaryKeyValues) {
		if(primaryKeyValues == null)
			throw new IllegalArgumentException();
		this.primaryKeyValues = primaryKeyValues;
	}

	public Object getPrimaryKeyValues() {
		return primaryKeyValues;
	}


	public String getPrimaryKey() {
		return primaryKey;
	}

	protected void setListModel() {
		model = new Models.ListModel(sqlQueryString,SqlTableContracts.tableName,getPrimaryKey());
		setModelFilter();
		model.setResult();
		model.tableRowData.addColumnAtBegin("sendMail", (boolean)true);
		setModel(model);
	}
	


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
	
	public void configurePdfPath(){
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
	
	

	@Override
	public void buttonSendMailClicked() {
		if(mailingThread.isAlive()) return;
		mailingThread.start();
		System.out.println("Thread started");
		//run();
    }
	
	public void deletePdfFromLocalSystem(String path){
		File file = new File(path);  
		if(file.exists()){
			file.delete();
		}
	}


	@Override
	public String[][] getMailData() {
		return createArrayForMailing();
	}
	
	public String[][] createArrayForMailing(){
		String[][] array = new String[model.tableRowData.getRowCount()][9]; 
		try {
			for(int i=0;i<model.tableRowData.getRowCount();i++){
				array[i][0]= model.tableRowData.getStringValueFromPosition(i, "Vorname");
				array[i][1]= model.tableRowData.getStringValueFromPosition(i, "Nachname");
				array[i][2]= model.tableRowData.getStringValueFromPosition(i, "Matrikelnr.");
				array[i][3]= model.tableRowData.getStringValueFromPosition(i, "Betreuer");
				array[i][4]= model.tableRowData.getStringValueFromPosition(i, "Studiengruppe");
				array[i][5]= model.tableRowData.getStringValueFromPosition(i, "Firmenname");
				array[i][6]= model.tableRowData.getStringValueFromPosition(i, "Bericht");
				array[i][7]= model.tableRowData.getStringValueFromPosition(i, "Zeugnis");
				array[i][8]= getLocalPartOfEmailAdress(model.tableRowData.getStringValueFromPosition(i, "email"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
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
	public void setSendFlag(int index, boolean status) {
		model.tableRowData.setValueAtPosition(index, "sendMail", status);
	}


	@Override
	public void setBerichtFlag(int index, boolean status) {
		model.tableRowData.setValueAtPosition(index, "Bericht", status);
	}


	@Override
	public void setZeugnisFlag(int index, boolean status) {
		model.tableRowData.setValueAtPosition(index, "Zeugnis", status);
	}
	
	@Override
	public void display() {
		view.display();
	}

	@Override
	public void run() {
		String absender = boxMail.getSenderEmailAdress();
		char[] pw = boxMail.getSenderEmailPassword();
        createPDF ps = new createPDF();
        try {
        	nullPercentValue = 0;
        	currentPercentValue = 0;
        	for(int i=0;i<model.tableRowData.getRowCount();i++)
        		if(model.tableRowData.getBooleanValueFromPosition(i, "sendMail"))
        			hundretPercentValue++;
        	progressBox.setComponentValues();
        	boxMail.setComponentValues();
        	MailSender mailer = new MailSender(this);
        	String queryString = new String();
        	mailer.connect(absender, pw);
        	
        	for(int i=0;i<model.tableRowData.getRowCount();i++){
        	
				if(model.tableRowData.getBooleanValueFromPosition(i, "sendMail")){
					currentPercentValue++;
				    ps.createPdf(	tmpPathForPDF,
									model.tableRowData.getBooleanValueFromPosition(i, "Bericht"),
									model.tableRowData.getBooleanValueFromPosition(i, "Zeugnis"),
									model.tableRowData.getStringValueFromPosition(i, "Vorname"),
									model.tableRowData.getStringValueFromPosition(i, "Nachname"),
									model.tableRowData.getStringValueFromPosition(i, "Matrikelnr."),
									model.tableRowData.getStringValueFromPosition(i, "Studiengruppe"),
									model.tableRowData.getStringValueFromPosition(i, "Betreuer"),
									model.tableRowData.getStringValueFromPosition(i, "Firmenname")
								); 
				    
				    boolean sendStatus = mailer.sendMail(boxMail.getRecipientEmailAdress(i));

				    boxMail.setMailSend(i, sendStatus);
					if(sendStatus){
						queryString += "update "+ SqlTableContracts.tableName + " set " +
								SqlTableContracts.Bericht + " = " + model.tableRowData.getBooleanValueFromPosition(i, "Bericht") + ", " +
								SqlTableContracts.Zeugnis + " = " + model.tableRowData.getBooleanValueFromPosition(i, "Zeugnis") + 
								" where "+ SqlTableContracts.Id + " = " + model.tableRowData.getStringValueFromPosition(i, "ID") +"; ";
						model.updateDatabase(queryString);
						progressBox.refreshContent();
					}
					deletePdfFromLocalSystem(tmpPathForPDF);
				}
        	}//ende der mail senden for schleife
        	 
        } catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				buttonSendMailClicked();
		}
       
		
	}

	@Override
	public int getValueEqualToNullPercent() {
		return nullPercentValue;
	}

	@Override
	public int getValueEqualToHundretPercent() {
		return hundretPercentValue;
	}

	@Override
	public int getCurrentValue() {
		return currentPercentValue;
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
	public void refresh(String[] infoAboutChanged) {
		boxMail.setMailingStatus(infoAboutChanged[0]);
	}

}
