package Banking.Banking.Application.Demo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import Banking.Banking.Application.Demo.Entity.Account;
import Banking.Banking.Application.Demo.Repository.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository repo;
	private AccountService(AccountRepository repo) {
		this.repo = repo;
	}
	 public Account createAccount(String accountNumber,double balance) {
		 Account account = new Account(accountNumber,balance);
		 return repo.save(account);
	 }
	 public Optional<Account> getAccount(String accountNumber) {
		 return repo.findById(accountNumber);
	 }
	 public Boolean deposte(String accountNumber,double amount) {
		 return getAccount(accountNumber).map(account ->{
			 account.deposte(amount);
			 repo.save(account);
			 return true;
		 }).orElse(false);
	 }
	 public Boolean withdraw(String accountNumber,double amount) {
		 return getAccount(accountNumber).map(account ->{
			 account.withdarw(amount);
			repo.save(account);
			return true;
		 }).orElse(false);
	 }
	 public boolean tarancation(String fromAccount,String toAccount,double amount) {
		 Optional<Account> sender = getAccount(fromAccount);
		 Optional<Account> recvier = getAccount(toAccount);
		 
		 if(sender.isPresent() && recvier.isPresent() && sender.get().withdarw(amount)) {
		 recvier.get().deposte(amount);
		 repo.save(sender.get());
		 repo.save(recvier.get());
		 return true;
	   }
		 return false;
	 }
	 
}
