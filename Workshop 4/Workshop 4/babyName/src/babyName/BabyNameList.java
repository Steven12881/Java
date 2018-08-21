package babyName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BabyNameList {

	private int year;
	private String fileName;
	private String gender;
	
	private List<String> boys;
	private List<String> girls;
	
	
	BabyNameList(String fileName, int year){
		this.fileName = fileName;
		this.year = year;
		boys = new ArrayList<String>();
		girls = new ArrayList<String>();
				
	}
	
	public boolean readFile() {

		
		try {
		BufferedReader br = new BufferedReader(new FileReader(this.fileName));
		String line  = " ";
		while (line!=null){
		
				line  = br.readLine();
			
			if (line==null) break;	
			String[] parts = line.split(",");

			String boy = parts[1];
			String girl = parts[4]; 
			boys.add(boy);
			girls.add(girl);
		}
		br.close();
		} catch (IOException e) {
			System.out.println("Unable to read file: " + this.fileName + ".  Quitting.");
			System.exit(-1);
			
		}
		return true;
	}
	

	
	public int findName(String name, String gender){
		
		List<String> search ;
		if (gender.equalsIgnoreCase("M")){
			search = this.boys;
		} else {
			search=this.girls;
		}
		
		int rank = search.indexOf(name) + 1;
		return rank;
			
	}
	
	
}
