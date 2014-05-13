package Import;


public class starter {

	public  static void main(String [] args){
		
		char [] a = {'n','v','m','b','s'};
		csvImport myimport = new csvImport("D:\\Programme\\Topcased-5.3.0\\workspace\\csvImport\\Mappe1.csv", ";",a);
		StudentContainer s;
		Student st;
		s=myimport.parseIt();
		
		for(int i = 0; i<s.size(); i++){
			st=s.getStudent(i);
			
			System.out.println(String.format("Zeile %d", i+1));
			System.out.println("--------------------------------------");
			System.out.println("Vorname: " + st.getVorame());
			System.out.println("Nachname: " + st.getName());
			System.out.println("Studiengruppe: " + st.getStudiengruppe());
			System.out.println("Matrikelnr: " + st.getMatrikelnr());
			System.out.println("Bibliotheksnr: " + st.getBibliotheksnr());
			System.out.println("");
			
		}
		
	}
	
}
