package Banking.Banking.Application.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Banking.Banking.Application.Entity.Account;
import Banking.Banking.Application.Repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber, double initialBalance) {
        Account account = new Account(accountNumber, initialBalance);
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    public boolean deposit(String accountNumber, double amount) {
        return getAccount(accountNumber).map(account -> {
            account.deposit(amount);
            accountRepository.save(account);
            return true;
        }).orElse(false);
    }

    public boolean withdraw(String accountNumber, double amount) {
        return getAccount(accountNumber).map(account -> {
            if (account.withdraw(amount)) {
                accountRepository.save(account);
                return true;
            }
            return false;
        }).orElse(false);
    }
    
    @Transactional
    public boolean transfer(String fromAccount, String toAccount, double amount) {
        Optional<Account> source = getAccount(fromAccount);
        Optional<Account> target = getAccount(toAccount);

        if (source.isPresent() && target.isPresent() && source.get().withdraw(amount)) {
            target.get().deposit(amount);
            accountRepository.save(source.get());
            accountRepository.save(target.get());
            return true;
        }
        return false;
    }
}
