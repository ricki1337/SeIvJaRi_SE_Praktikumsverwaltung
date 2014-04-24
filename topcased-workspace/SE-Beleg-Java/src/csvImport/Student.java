package csvImport;

public class Student{
		private String name,vorname,studiengruppe;
		private int bibliotheksnr,matrikelnr;
		
		Student(String name,String vorname,String studiengruppe, int bibliotheksnr,int matrikelnr){
			this.name=name;
			this.vorname=vorname;
			this.studiengruppe=studiengruppe;
			this.bibliotheksnr=bibliotheksnr;
			this.matrikelnr=matrikelnr;
		}
		
		protected String getName(){
			return this.name;
		}
		
		protected String getVorame(){
			return this.vorname;
		}
		
		protected String getStudiengruppe(){
			return this.studiengruppe;
		}
		
		protected int getBibliotheksnr(){
			return this.bibliotheksnr;
		}
		
		protected int getMatrikelnr(){
			return this.matrikelnr;
		}
		
	}