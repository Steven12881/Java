package name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Name {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> topNames =  new ArrayList<String>();
		
		topNames.add("Amelia");
		topNames.add("Olivia");
		topNames.add("emily");
		topNames.add("Isla");
		topNames.add("ava");
		topNames.add("oliver");
		topNames.add("Jack");
		topNames.add("Charlie");
		topNames.add("harry");
		topNames.add("Jacob");
		
		//1A
		
		Collections.sort(topNames, (String s1, String s2) -> s1.compareToIgnoreCase(s2));
		
		
		final String finalNames = topNames.stream() 
					    .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)) 
					    .reduce("", (a,b) -> a +" " + b);
		
		System.out.println(finalNames);
		
	}


}
