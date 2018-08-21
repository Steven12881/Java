package nameTaskB;

import java.util.ArrayList;
import java.util.Collections;

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

		Collections.sort(topNames);
		
		for(String finalNames: topNames)
		{
			finalNames = finalNames.substring(0,1).toUpperCase() + finalNames.substring(1);
			System.out.println(finalNames);
		}
		
	}

}
