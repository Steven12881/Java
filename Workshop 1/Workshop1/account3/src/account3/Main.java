package account3;

import account3.CheckingAccount;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckingAccount acc2 = new CheckingAccount(1, 50.00,-100.00);
		acc2.setAnnualInterestRate(0.045);
		acc2.withdraw(200.00);
		//acc2.deposit(3000.00);
		
		System.out.println("Balance = " + acc2.getBalance());
		System.out.println("Monthly Interest = " + acc2.getMonthlyInterestRate());
		System.out.println("Date created:" + acc2.getDateCreated());
		
		System.out.println(acc2.toString());
	}

}
