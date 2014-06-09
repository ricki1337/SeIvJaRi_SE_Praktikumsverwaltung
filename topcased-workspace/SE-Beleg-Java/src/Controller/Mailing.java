package Controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.itextpdf.text.DocumentException;

import Mail.createPDF;
import Mail.mail;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Views.ViewNew;
import Views.GuiElemente.BoxElementMail;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.MailBox;
import Views.Interfaces.MailBoxCtrl;

public class Mailing extends ControllerNew implements BasicBoxCtrl, MailBoxCtrl {

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
									" JOIN " + SqlTableProfs.tableNameWithAlias + " ON " + SqlTableProfs.TableNameDotName + " = " + SqlTableContracts.TableNameDotFK_Betreuer;
	
	private ViewNew view;
	protected Models.ListModel model;
	private MailBox boxMail;
	
	private String PDF;
	
	private Object primaryKeyValues;
	private String primaryKey;
	
	protected Mailing(){}
	
	public Mailing(String primaryKey, Object primaryKeyValues){
		setPrimaryKey(primaryKey);
		setPrimaryKeyValues(primaryKeyValues);
		
		setListModel();
		
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
				if(primaryKey.equals(SqlTableStudent.MatrikelNummer)|| primaryKey.equals("ID") || primaryKey.equals("IDCompanies")){
					model.setOrFilter(primaryKey, new IntFilter(value.toString()));
				}
				
				if(primaryKey.equals("Name")){
					model.setOrFilter(primaryKey, new StringFilter(value));
				}
			}
		} else{
			if(primaryKey.equals("Matrikelnr") || primaryKey.equals("ID")|| primaryKey.equals("IDCompanies")){
				model.setOrFilter(primaryKey, new IntFilter(primaryKeyValues.toString()));
			}
			
			if(primaryKey.equals("Name")){
				model.setOrFilter(primaryKey, new StringFilter(primaryKeyValues));
			}
		}
	}
	
	@Override
	public void setElements() {
		view.setTitle("Mail senden");
		boxMail = new BoxElementMail(this);
		view.addComponentToView(boxMail);
	}
	
	public void configurePdfPath(String path){
		path = System.getProperty("user.dir");
		
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
		PDF = path;
	}
	
	

	@Override
	public void buttonSendMailClicked() {
		String absender = boxMail.getSenderEmailAdress();
		char[] pw = boxMail.getSenderEmailPassword();
        createPDF ps = new createPDF();
        try {
        for(int i=0;i<model.tableRowData.getRowCount();i++){
        	
				if(model.tableRowData.getBooleanValueFromPosition(i, "sendMail")){
				    ps.createPdf(	PDF,
									model.tableRowData.getBooleanValueFromPosition(i, "Bericht"),
									model.tableRowData.getBooleanValueFromPosition(i, "Zeugnis"),
									model.tableRowData.getStringValueFromPosition(i, "Vorname"),
									model.tableRowData.getStringValueFromPosition(i, "Nachname"),
									model.tableRowData.getStringValueFromPosition(i, "Matrikelnr."),
									model.tableRowData.getStringValueFromPosition(i, "Studiengruppe"),
									model.tableRowData.getStringValueFromPosition(i, "Betreuer"),
									model.tableRowData.getStringValueFromPosition(i, "Firmenname")
								); 
					boolean sendStatus = mail.sendMail(absender, boxMail.getRecipientEmailAdress(i), pw);
				    boxMail.setMailsSend(sendStatus);
					if(sendStatus){
						String queryString = "update "+ SqlTableContracts.tableName + " set " +
								SqlTableContracts.Bericht + " = " + model.tableRowData.getBooleanValueFromPosition(i, "Bericht") + "," +
								SqlTableContracts.Zeugnis + " = " + model.tableRowData.getBooleanValueFromPosition(i, "Zeugnis") + 
								" where "+ SqlTableContracts.Id + " = " + model.tableRowData.getBooleanValueFromPosition(i, "ID") +";";
						model.updateDatabase(queryString);
					}
					deletePdfFromLocalSystem(PDF);
				}
        }//ende der mail senden for schleife
        } catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				buttonSendMailClicked();
		}
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

}
