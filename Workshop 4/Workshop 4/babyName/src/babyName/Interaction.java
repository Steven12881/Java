package babyName;

import java.util.Scanner;

public class Interaction {
	
	private int year;
	private String gender;
	private String name;
	private Scanner sc;
	
	public int getYear(){
		return year;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getName(){
		return name;
	}
	
	
	Interaction(){
		this.year=-1;
		this.gender="";
		this.name="";
		sc = new Scanner(System.in);
	}
	
	public void ask(){	
		
		boolean error = false;
	
	   
		do {
		error=false;	
		System.out.print("Enter the year: ");
		try {
			this.year = sc.nextInt();
		} catch (Exception e){
			System.out.println("Invalid year, please enter a number.");
			sc.next();
			error=true;
			continue;
		}
			if ((year<2001) || (year > 2010)){
				System.out.println("Invalid year.  Enter year between 2001 and 2010");
				error =true;
			}
		}while (error);
		

		do {
			error=false;
			System.out.print("Enter the gender: ");
			this.gender = sc.next();
			if ((!this.gender.equalsIgnoreCase("M"))  && (!this.gender.equalsIgnoreCase("F"))){
				System.out.println("Invalid gender. Enter M or F.");
				error = true;
			}
				
		
		}while (error);
		
		do {
			error=false;
			System.out.print("Enter the name: ");
			this.name = sc.next();
		    if (this.name.isEmpty()){
		    	System.out.println("Invalid name.  You must enter at least one character.");
		    	error = true;
		    }
			
	
	}while (error);
		
		
		//sc.close();
	}
	
	
	public boolean another(){
		
		//Scanner sc = new Scanner(System.in);
	
		boolean error = false;
		do {
		
			System.out.print("Enter another inquiry? ");
			sc.reset();
			
			String choice = sc.next();
			error=false;
			if (choice.equalsIgnoreCase("Y")){
				//sc.close();
				return true;
			} else if (choice.equalsIgnoreCase("N")){
			//	sc.close();
				return false;
			} else {
				System.out.println("Invalid choice.  Enter Y or N.");
				error = true;
			}
		}while (error);
	//	sc.close();
		return false;
	}
	
	
	public String getGenderWord(){
		if (this.gender.equalsIgnoreCase("M")){
			return "Boy";
			
		} else {
			return "Girl";
		}
	}
	
}