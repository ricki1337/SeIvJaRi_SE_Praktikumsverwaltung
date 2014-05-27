package Views;

import javax.swing.JLabel;









import drucken.MainTest;
import drucken.PrintExport;
import Views.Table.TableData;
import Controller.Controller;

public class Print extends SingleView{
	int anz=0;


	public Print(Controller controller,int selectedRows) {
		super(controller);
		this.setTitle("Mail senden");
		anz=selectedRows;
	}
	public String[] getDataFromMarkedRows(String collumn){
		String[] ret = new String[anz];
		try {
			for(int i=0;i<anz;i++){
				
			ret[i] = tableRowData.getStringValueFromPosition(i, collumn);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;	
	}

	public static void anzeigen(String[][] array){
		for (int i = 0; i < array.length; i++)
		{
		    for (int j = 0; j < array[i].length; j++)
		        System.out.print( array[i][j] + ", " );
		    System.out.println();
		}
	}
// TODO den Richtigen Array fürs drucken zusammenbasteln
	//"65878", "Hans Detlef", "muh@web.de", "11/43/01", "sap"	
	public static String[][] createArrayForPrinting( String[] matr,String[] vorname, String[] name, String[] mail,String[] stdgrp, String[] betrieb){
		int zeilenAnz = matr.length;
		String[][] array = new String[zeilenAnz][5]; 
		
		for(int i=0;i<zeilenAnz;i++){
			array[i][0]=matr[i];
			array[i][1]=vorname[i]+" "+name[i];
			array[i][2]=mail[i];
			array[i][3]=stdgrp[i];
			array[i][4]=betrieb[i];
			
		}
		anzeigen(array);
		return array;
	}
	@Override
	public void setElemente() {
		String[][] Printdata=
		createArrayForPrinting(	getDataFromMarkedRows("Matrikelnr."),
		getDataFromMarkedRows("Vorname"),
		getDataFromMarkedRows("Nachname"),
		getDataFromMarkedRows("Email"),
		getDataFromMarkedRows("Studiengruppe"),
		getDataFromMarkedRows("Firmenname"));	
		
		
		System.out.println("Print wird aufgerufen");
		//TODO Dialog öffnen
		PrintExport testding = null;
		try {
			testding = new PrintExport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testding.setData(Printdata, "Titel","Überschrift");
		try {
			testding.print();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}