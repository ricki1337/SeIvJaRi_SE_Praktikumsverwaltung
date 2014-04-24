package csvImport;


import java.util.ArrayList;

public class StudentContainer {
	private ArrayList<Student> list;
	
	StudentContainer(){
		list = new ArrayList<Student>();
	}
	
	public void addStudent(String name,String vorname,String studiengruppe, int bibliotheksnr,int matrikelnr){
		list.add(new Student(name, vorname,studiengruppe, bibliotheksnr,matrikelnr));
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public int size(){
		return list.size();
	}
	
	public Student getStudent(int index){
		return list.get(index);
	}
}
