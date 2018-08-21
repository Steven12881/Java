package banks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankTest {
    private static TreeMap<String, Bank> banks = new TreeMap<String, Bank>();

    //Number of banks: 5
    public static String getNumBanks(String line) {
	String numBanks = null;
	if (line != null) {
	    String[] items = line.split(":");
	    if (items.length == 2) {
		numBanks = items[1].trim();
	    }
	}
	return numBanks;
    }

    //Minimum asset limit: 201
    public static String getAssetLimit(String line) {
	String assetLimit = null;
	if (line != null) {
	    String[] items = line.split(":");
	    if (items.length == 2) {
		assetLimit = items[1].trim();
	    }
	}
	return assetLimit;
    }

    /**
     * Collect bank info with given line from test data
     * 
     * Bank # 0 -> Balance: 25 -> Number of banks Loaned: 2 -> Bank ID: 1 -> Amount: 100.5 -> Bank ID: 4 -> Amount: 320.5
     * Bank id: Bank #([0-9]+)*
     * Balance: Balance:([0-9]+)*
     * Number of banks Loaned: Number of banks Loaned:([0-9]+)*
     * @param line
     */
    public static void collectBankInfo(String line) throws Exception {
	//Get bank id from the record
	String bank_id = getToken(line, "Bank #(\\s[0-9]+)*", 1);
	if (bank_id != null) {
	    bank_id = bank_id.trim();
	}
	//Get bank balance from the record
	String bank_balance = getToken(line, "Balance:(\\s[0-9]+)*", 1);
	if (bank_balance != null) {
	    bank_balance = bank_balance.trim();
	}
	//Get number of loan from the record
	String num_loan = getToken(line, "Number of banks Loaned:(\\s[0-9]+)*", 1);
	if (num_loan != null) {
	    num_loan = num_loan.trim();
	}
	//System.out.println(line + " " + bankId + " " + balance + " " + numLoan);
	//Create new bank with given bank id, balance, number of loan
	if (bank_id != null && bank_balance != null && num_loan != null && isNumber(bank_balance)
		&& isNumber(num_loan)) {
	    double balance = Double.parseDouble(bank_balance);
	    int numLoan = Integer.parseInt(num_loan);
	    //System.out.println(bank_id + " " + balance + " " + numLoan);
	    Bank bank = new Bank(bank_id, balance, numLoan);
	    //if banks map does not contain the id, then add the bank object to the map
	    if (!banks.containsKey(bank_id)) {
		banks.put(bank_id, bank);
	    } else {
		throw new Exception("ERROR: Duplicate bank entered!");
	    }
	} else {
	    throw new Exception("ERROR: Invalid bank information entered!");
	}
    }

    /**
     * Bank ID:(\s[0-9]+)\s->\sAmount:(\s[0-9.]+)
     * 
     * Collect borrower info with given record and bank id
     * @param line
     * @param id
     */
    public static void collectBorrowerInfo(String line, String id) throws Exception {
	setBorrowers(line, "Bank ID:(\\s[0-9]+)\\s->\\sAmount:(\\s[0-9.]+)", 1, 2, id);
    }

    /**
     * Return token using regular expression
     * @param line - record
     * @param pattern - regular expression
     * @param index - regular expression group index 
     * @return
     */
    private static String getToken(String line, String pattern, int index) throws Exception {
	String token = null;
	if (line != null && pattern != null) {
	    Pattern p = Pattern.compile(pattern);
	    if (p != null) {
		Matcher m = p.matcher(line);
		if (m != null && m.find()) {
		    token = m.group(index);
		    //System.out.println("Matching word is " + token);
		}
	    } else {
		throw new Exception("ERROR: Invalid record and regex pattern entered!");
	    }
	} else {
	    throw new Exception("ERROR: Invalid record and regex pattern entered!");
	}
	return token;
    }

    /**
     * Set borrowers for each bank.
     * 
     * @param line
     * @param pattern
     * @param idIndex
     * @param amountIndex
     * @param id - Non-negative integer
     */
    private static void setBorrowers(String line, String pattern, int idIndex, int amountIndex, String id)
	    throws Exception {
	String bankId = null;
	String bankAmt = null;
	//Main bank object with given bank id
	if (id != null && id.length() > 0) {
	    Bank mainBank = banks.get(id);
	    if (mainBank != null) {
		//Run regular expression match
		int totalLoan = mainBank.getNumOfLoan();
		Pattern p = Pattern.compile(pattern);
		if (p != null) {
		    Matcher m = p.matcher(line);
		    if (m != null) {
			int count = 0;
			while (m.find()) {
			    //Get bank id from the regular expression
			    bankId = m.group(idIndex).trim();
			    //Get bank amount from the regular expression
			    bankAmt = m.group(amountIndex).trim();
			    //System.out.println(mainBank.getid() + " " + bankId + " " + bankAmt);
			    if (bankId != null && bankId.length() > 0 && bankAmt != null && isNumber(bankAmt)) {
				//Create new bank object to store the loan amount
				Bank bank = new Bank(bankId, 0.0, 0);
				//Get amount from bank amount
				double amount = Double.parseDouble(bankAmt);
				//Set loan amount to bank object
				bank.setAmount(amount);
				//System.out.println(bank.getid() + " " + bank.getAmount());
				//Add the bank object to the main bank object
				mainBank.setBorrowers(bank);
				count++;
			    } else {
				throw new Exception("ERROR: Invalid borrower bank information entered!");
			    }
			}
			//System.out.println(count + " " + totalLoan);
			if (count != totalLoan)
			    throw new Exception("ERROR: Invalid borrower bank information entered!");
		    } else {
			throw new Exception("ERROR: Invalid borrower bank information entered!");
		    }
		} else {
		    throw new Exception("ERROR: Given regular expression matching pattern invalid!");
		}
	    } else {
		throw new Exception("ERROR: Given bank ID " + id + " doesn't exist!");
	    }
	}
    }

    /**
     * Calculate total asset with given asset limit
     * 
     * @param limit
     */
    public static void calculateTotalAsset(double limit) {
	//Difference between current # of unsafe banks
	int difference = 0;
	//Previous # of unsafe banks
	int prevUnsafe = 0;
	//Current # of unsafe banks
	int countUnsafe = 0;
	do {
	    //Reset the # of unsafe banks for new round
	    countUnsafe = 0;
	    for (Map.Entry<String, Bank> entry : banks.entrySet()) {
		//Get bank object
		Bank bank = entry.getValue();
		//Get bank balance
		double asset = bank.getBalance();
		//System.out.println("Bank " + entry.getKey() + " " + asset);
		//Loop the bank's borrower's list
		for (Bank borrower : bank.getBorrowers()) {
		    //System.out.println(borrower.getid() + " " + borrower.getAmount());
		    //Get borrower amount from each borrower bank
		    double borrowAmount = borrower.getAmount();
		    //if the bank is not unsafe bank, then add the borrow amount to the asset total
		    if (!banks.get(borrower.getid()).getUnsafeBank())
			asset += borrowAmount;
		}
		//Set the total asset amount to the bank's asset amount
		bank.setTotalAsset(asset);
		//if asset is less than or equal to the given asset safe limit
		if (asset <= limit) {
		    //Set the bank's unsafe flag to be true
		    bank.setUnsafeBank(true);
		    //Increment the # of unsafe banks by 1
		    countUnsafe++;
		    //System.out.println(countUnsafe);
		}
		//System.out.println(bank.getid() + " " + bank.getTotalAsset() + " " + bank.getUnsafeBank());
	    }
	    //calculate difference between current # of unsafe banks and previous # of unsafe banks
	    difference = countUnsafe - prevUnsafe;
	    //assign the current # of unsafe banks to the previous # of unsafe banks
	    prevUnsafe = countUnsafe;
	    //System.out.println("# of unsafe: " + difference);
	    //check if difference is greater than zero. If greater than zero, then run another loop
	} while (difference > 0);
    }

    /**
     * Evaluate unsafe banks
     */
    public static void evaluateUnsafeBanks() {
	String msg = "Unsafe banks are ";
	//Create array list for unsafe banks
	ArrayList<String> unsafeBanks = new ArrayList<String>();
	//Loop the entire map to collect unsafe banks
	for (Map.Entry<String, Bank> entry : banks.entrySet()) {
	    Bank bank = entry.getValue();
	    //System.out.println(entry.getKey() + ": " + bank.getTotalAsset());
	    //if bank is unsafe, then add it to the array list
	    if (bank.getUnsafeBank()) {
		//System.out.println(entry.getKey());
		unsafeBanks.add(entry.getKey());
	    }
	}
	int count = 0;
	//Loop the unsafe banks
	for (String id : unsafeBanks) {
	    //construct the output message
	    msg += "Bank " + id;
	    //append comma if it's not the last one
	    if (count < unsafeBanks.size() - 1)
		msg += ", ";
	    count++;
	}
	System.out.println(msg);
    }

    private static boolean isNumber(String value) {
	boolean number = false;
	if (value != null && value.length() > 0) {
	    if (value.contains(".")) {
		String[] nums = value.split("\\.");
		if (nums.length == 2) {
		    number = isUnitNumeric(nums[0]);
		    if (number) {
			number = isUnitNumeric(nums[1]);
		    }
		}
	    } else {
		number = isUnitNumeric(value);
	    }
	}
	return number;
    }

    private static boolean isUnitNumeric(String num) {
	boolean isSingleNumber = true;
	if (num != null && num.length() > 0) {
	    char[] numbers = num.toCharArray();
	    for (char number : numbers) {
		if (number != '0' && number != '1' && number != '2' && number != '3' && number != '4' && number != '5'
			&& number != '6' && number != '7' && number != '8' && number != '9') {
		    isSingleNumber = false;
		    break;
		}
	    }
	} else
	    isSingleNumber = false;
	return isSingleNumber;
    }

    public static void main(String[] args) throws Exception {
	ArrayList<String> list = new ArrayList<String>();
	FileReader fr = new FileReader(Paths.get("").toAbsolutePath().toString() + File.separator + "banks.txt");
	BufferedReader br = new BufferedReader(fr);
	String s = br.readLine();
	String numBanks = getNumBanks(s);
	s = br.readLine();
	String assetLimit = getAssetLimit(s);
	//System.out.println(numBanks + " " + assetLimit);
	if (numBanks != null && assetLimit != null && isNumber(numBanks) && isNumber(assetLimit)) {
	    int row = 0;
	    int num_banks = Integer.parseInt(numBanks);
	    s = br.readLine();
	    //System.out.println(row + " " + num_banks + " " + s);
	    while (s != null && row < num_banks) {
		collectBankInfo(s);
		list.add(s);
		s = br.readLine();
		row++;
		//System.out.println(row + " " + num_banks + " " + s);
	    }
	    br.close();
	    int i = 0;
	    for (Map.Entry<String, Bank> entry : banks.entrySet()) {
		String id = entry.getKey();
		String line = list.get(i);
		//System.out.println(id + " " + line);
		collectBorrowerInfo(line, id);
		i++;
	    }
	    calculateTotalAsset(Double.parseDouble(assetLimit));
	    evaluateUnsafeBanks();
	} else {
	    br.close();
	    throw new Exception("ERROR: # of banks is invalid or/and asset limit is invalid!");
	}
    }
}