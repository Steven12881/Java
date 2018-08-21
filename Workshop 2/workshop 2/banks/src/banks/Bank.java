package banks;

import java.util.ArrayList;

public class Bank {

    //public static final double limit = 201.0;
    private String id;
    private int numOfLoan;
    private double balance;
    private double totalAsset;
    private double amountOfBorrow;
    private boolean isUnsafe;
    private ArrayList<Bank> borrowers;

    public Bank(String id, double balance, int numOfLoan) {
	this.id = id;
	this.balance = balance;
	this.numOfLoan = numOfLoan;
	this.isUnsafe = false;
	this.borrowers = new ArrayList<Bank>();
    }

    public void setAmount(double amountOfBorrow) {
	this.amountOfBorrow = amountOfBorrow;
    }

    public void setTotalAsset(double amount) {
	this.totalAsset = amount;
    }

    public void setUnsafeBank(boolean isUnsafe) {
	this.isUnsafe = isUnsafe;
    }

    public void setBorrowers(Bank borrower) {
	borrowers.add(borrower);
    }

    public String getid() {
	return this.id;
    }

    public double getBalance() {
	return this.balance;
    }

    public int getNumOfLoan() {
	return this.numOfLoan;
    }

    public double getTotalAsset() {
	return this.totalAsset;
    }

    public double getAmount() {
	return this.amountOfBorrow;
    }

    public boolean getUnsafeBank() {
	return this.isUnsafe;
    }

    public ArrayList<Bank> getBorrowers() {
	return this.borrowers;
    }
}