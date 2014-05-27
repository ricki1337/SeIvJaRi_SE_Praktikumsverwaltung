package Views;

import javax.swing.JLabel;


import Mail_1version.guiMail;
//import Mail_1version.guiMail;
import Views.Table.TableData;
import Controller.Controller;

public class Mailing extends SingleView{
	int anz=0;


	public Mailing(Controller controller,int selectedRows) {
		super(controller);
		this.setTitle("Mail senden");
		anz=selectedRows;
	}
	public String[] getDataFromMarkedRows(String collumn){
		String[] ret = new String[anz];
		try {
			for(int i=0;i<anz;i++){
				if(collumn.equals("email")){
					//schneide snummer von der mail adresse ab
					ret[i] = (tableRowData.getStringValueFromPosition(i, collumn)).substring(0, 6);
				}
				else{
			ret[i] = tableRowData.getStringValueFromPosition(i, collumn);}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;	
	}
	
	//kann/sollte weg
	public static void anzeigen(String[][] array){
		for (int i = 0; i < array.length; i++)
		{
		    for (int j = 0; j < array[i].length; j++)
		        System.out.print( array[i][j] + ", " );
		    System.out.println();
		}
	}
//"hans", "wurst", "34049" , "beck", "043", "wurstbetrieb","0","1","s68551"
	public static String[][] createArrayForMailing(String[] vorname, String[] name, String[] matr, String[] betreuer, 
			String[] stdgrp,String[] betrieb, String[] bericht, String[] zeugnis,String[] Snummer){
		int zeilenAnz = matr.length;
		String[][] array = new String[zeilenAnz][9]; 
		
		for(int i=0;i<zeilenAnz;i++){
			array[i][0]=vorname[i];
			array[i][1]=name[i];
			array[i][2]=matr[i];
			array[i][3]=betreuer[i];
			array[i][4]=stdgrp[i];
			array[i][5]=betrieb[i];
			array[i][6]=bericht[i];
			array[i][7]=zeugnis[i];
			array[i][8]=Snummer[i];
			
		}
		anzeigen(array);
		return array;
	}
	@Override
	public void setElemente() {
		String[][] Maildata=
		createArrayForMailing(	getDataFromMarkedRows("Vorname"),
		getDataFromMarkedRows("Nachname"),
		getDataFromMarkedRows("Matrikelnr."),
		getDataFromMarkedRows("Betreuer"),
		getDataFromMarkedRows("Studiengruppe"),
		getDataFromMarkedRows("Firmenname"),
		getDataFromMarkedRows("Bericht"),
		getDataFromMarkedRows("Zeugnis"),
		getDataFromMarkedRows("email"));	
		
		
		System.out.println("view wird angezeigt");
		guiMail bl = new guiMail(Maildata);
	    bl.setVisible(true);
		//root
		//seproject
	//addTextfieldWithSqlReference("Matrikelnr.", "Matrikelnr.", "MatrNr", 1, 1);
	//addTextfieldWithSqlReference("Vorname", "Vorname", "Firstname", 1, 2);
	//addBottomMenu(1);
		
	}
	
	

}