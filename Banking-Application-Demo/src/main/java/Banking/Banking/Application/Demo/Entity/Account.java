
package Banking.Banking.Application.Demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {

	@Id
	private String accountNumber;
	private double balance;
	
	public Account(String accountNumber,double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public Account() {}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}
	
	public void deposte(double amount) {
		this.balance += amount;
	}

	public boolean withdarw(double amount) {
		if(balance>= amount) {
		this.balance -= amount;
		return true;
	}
	    return false;
	}
	
}
