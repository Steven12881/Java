package account3;

import account3.Account;

public class CheckingAccount extends Account{
	private double overDraftLimit;
	
	public void setOverDraftLimit(double overDraftLimit){
		this.overDraftLimit = overDraftLimit;
	}
	
	CheckingAccount(){
		super();
		overDraftLimit = 0;
	}
	
	CheckingAccount(int id, double balance, double overDraftLimit){
		super(id, balance);
		this.overDraftLimit = overDraftLimit;
	}
	
	public double withdraw(double moneyOut)
	{
		
		//if my balance is 10,,, and i want to take out 100, then i can only do that if my overdraft allows it
		
		if ((this.getBalance() - moneyOut) < this.overDraftLimit){
			System.out.println("Not enough money in the account");
		}
		else {
			super.withdraw(moneyOut);
		}
		
		return this.getBalance();
	}
	
	
	public String toString(){
		return "This is a checking account with balance: " + "ID: " + this.getId() +"  Balance: "+ this.getBalance();
	}
}
