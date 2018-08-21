package account1;

import account1.Account;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Account acc = new Account(1122, 20000.00);
		acc.setAnnualInterestRate(0.045);
		acc.withdraw(2500.00);
		acc.deposit(3000.00);
		
		System.out.println("Balance = " + acc.getBalance());
		System.out.println("Monthly Interest = " + acc.getMonthlyInterestRate());
		System.out.println("Date created:" + acc.getDateCreated());
	}

}
