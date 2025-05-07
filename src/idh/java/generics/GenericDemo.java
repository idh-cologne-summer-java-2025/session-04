package idh.java.generics;

public class GenericDemo {

	public static void main(String[] args) {
		// Eine Liste für Strings
		GenericArrayList<String> sl = new GenericArrayList<String>();
		sl.add("Erster");
		sl.add("Zweiter");
		sl.add("Dritter");
		
		// Eine Liste für Students
		Student studi1 = new Student("Max Moritz");
		GenericArrayList<Student> sl2 = new GenericArrayList<Student>();
		sl2.add(studi1);
		System.out.println(sl2.get(0));
		
		// Eine Liste für Integers
		GenericArrayList<Integer> gal1 = new GenericArrayList<Integer>(10);
		gal1.add(1002);
		gal1.remove(0);
		
		// Alles mit der gleichen Klasse realisiert!
		

	}

}
