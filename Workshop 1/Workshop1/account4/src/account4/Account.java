package account4;

import java.util.Date;

public class Account {

	private int id;
	private double balance;
	private double annualInterestRate;
	private double monthlyInterestRate;
	private double interest;
	//private double newBalance;
	//private double moneyOut;
	//private double moneyIn;
	private Date dateCreated;
	
	
	public Date getDateCreated(){
		return this.dateCreated;
	}
	
	
	public double getAnnualInterestRate(){
		return annualInterestRate;
	}
	
	public void setAnnualInterestRate(double annualInterestRate){
		this.annualInterestRate = annualInterestRate;
	}
	
	//accessor
	public int getId(){
		return this.id;
	}
	
	
	public void setId(int id){
		this.id=id;
	}
	
	public double getBalance(){
		return this.balance;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	
	private void setCurrentDate(){
		this.dateCreated = new Date();
	}
	
	Account()
	{
		id = 0;
		balance = 0.0;
		annualInterestRate = 0.0;
		setCurrentDate();
	}
	
	
	Account(int id, double balance)
	{
		this.id = id;
		this.balance = balance;
		setCurrentDate();
	}

	public double getMonthlyInterestRate(){
		
		monthlyInterestRate = annualInterestRate / 12;
	
		return monthlyInterestRate;
		
	}
	
	public double getMonthlyInterest()
	{
		interest = balance * monthlyInterestRate;
		
		return interest;
	}
	
	public double withdraw(double moneyOut)
	{
		balance = balance - moneyOut;
		
		return balance;
	}
	
	public double deposit(double moneyIn)
	{
		balance = balance + moneyIn;   // balance += moneyIn;
		
		return balance;
	}
}