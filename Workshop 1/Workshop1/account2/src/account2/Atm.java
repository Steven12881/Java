package account2;

import java.util.Scanner;

public class Atm {

	//Account mainMenu = new Atm ();
	
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner (System.in);
		
		
		//create 10 accounts with 100 dollars in each
		Account[] accounts = new Account[10];
		for (int i=0;i<accounts.length;i++){
			accounts[i] = new Account(i,100.00);
		}
		
	
		int accountID=0;
		do {
			System.out.print("Enter an id: ");
			accountID = scanner.nextInt();
		 } while ((accountID <0 ||  accountID > 9));
		//now we have the correct ID;
		//System.out.print("This is id testing: " + accountID);
	
	boolean done = false;	
		while ( !done ) {
		System.out.println("\n");
		System.out.println("Main menu");
		System.out.println("1: check balance");
		System.out.println("2: withdraw");
		System.out.println("3: deposit");
		System.out.println("4: exit");
		System.out.print("Enter a choice: ");
		
		int choice = scanner.nextInt();
		
		switch(choice){
		      case 1:	
		    	  double balance = accounts[accountID].getBalance();
		    	  System.out.print("The balance is: " + balance);
		    	  //System.out.print("testing: ");
		    	  break;
		      case 2:
		    	  System.out.print("Enter the amount to withdraw: ");
		    	  double moneyOut = scanner.nextDouble();
		    	  accounts[accountID].withdraw(moneyOut);
		    	  break;
		      case 3:
		    	  System.out.print("Enter the amount to deposit: ");
		    	  double moneyIn = scanner.nextDouble();
		    	  accounts[accountID].deposit(moneyIn);
		    	  break;
		        
		      case 4:
		    	  done = true;
		    	  break;
		    	  
		    default:
		    		System.out.println("You've entered an invalid choice. Try again.");
		    	  
		}
		//scanner.close();		
		}
		System.out.println("Enter an id: ");
	}
	
}
