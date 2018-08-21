package babyName;


import java.util.HashMap;
import java.util.Map;


public class Runner {

	public static void main(String[] args) {
		
		
		//read the 10 files into a list
		//List <String> babyNames = new ArrayList<String>();
		
		
	    Map<Integer, BabyNameList> babyLists = new HashMap<>();

		
	
		
		for (int c1=2001;c1<=2010;c1++){
			BabyNameList babies = new BabyNameList("c:\\names\\babynameranking" + c1 + ".txt",c1);

				babies.readFile();

			babyLists.put(c1, babies);
		}
		

		
		
		
	
		
		Interaction inter = new Interaction();
		
		do {
		    inter.ask();
		    

		    BabyNameList lst = babyLists.get(inter.getYear());
			int rank = lst.findName(inter.getName(),inter.getGender());
			if (rank==0){
				System.out.println("Cannot find " + inter.getName());
			} else {
			System.out.println(inter.getGenderWord() + " name " + inter.getName() + " is ranked #" + rank +  " in year " + inter.getYear());
			}
		
		
		} while (inter.another());
		
		
		
		

	}

}
