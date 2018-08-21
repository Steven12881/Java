package account4;

import account4.SavingsAccount;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SavingsAccount acc3 = new SavingsAccount(1, 50.00);
		acc3.setAnnualInterestRate(0.045);
		acc3.withdraw(70.00);
		//acc2.deposit(3000.00);
		
		System.out.println("Balance = " + acc3.getBalance());
		System.out.println("Monthly Interest = " + acc3.getMonthlyInterestRate());
		System.out.println("Date created:" + acc3.getDateCreated());
		
		System.out.println(acc3.toString());
	}

}
