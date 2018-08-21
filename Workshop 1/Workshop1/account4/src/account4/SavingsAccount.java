package account4;

import account4.Account;

public class SavingsAccount extends Account {
	
	
	public double withdraw(double moneyOut)
	{
		if (this.getBalance() - moneyOut < 0.0){
			System.out.println("Not enough money in the account");
		}
		else {
			super.withdraw(moneyOut);
		}
		
		return this.getBalance();
	}
	
	
	SavingsAccount(){
			super();
	}
	
	SavingsAccount(int id, double balance){
		super(id,balance);
	}
	
	public String toString(){
		return "This is a saving account with balance: " + "ID: " + this.getId() +"  Balance: "+ this.getBalance();
	}
	
	
}