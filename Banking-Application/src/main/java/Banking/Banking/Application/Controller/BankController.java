package Banking.Banking.Application.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Banking.Banking.Application.Entity.Account;
import Banking.Banking.Application.Service.AccountService;

@Controller
@RequestMapping("/bank")
public class BankController {
    private final AccountService accountService;

    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Banking Application!");
        return "home";
    }

    @PostMapping("/create")
    @ResponseBody
    public String createAccount(@RequestParam String accountNumber, @RequestParam double initialBalance) {
        accountService.createAccount(accountNumber, initialBalance);
        return "Account " + accountNumber + " created with balance: " + initialBalance;
    }

    @PostMapping("/deposit")
    @ResponseBody
    public String deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        if (accountService.deposit(accountNumber, amount)) {
            return "Deposited " + amount + " to account " + accountNumber;
        }
        return "Account not found";
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public String withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        if (accountService.withdraw(accountNumber, amount)) {
            return "Withdrawn " + amount + " from account " + accountNumber;
        }
        return "Insufficient funds or account not found";
    }
    
    @PostMapping("/transfer")
    @ResponseBody
    public String transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount) {
        if (accountService.transfer(fromAccount, toAccount, amount)) {
            return "Transferred " + amount + " from " + fromAccount + " to " + toAccount;
        }
        return "Transfer failed: Insufficient funds or account not found";
    }

    @GetMapping("/balance")
    @ResponseBody
    public String getBalance(@RequestParam String accountNumber) {
        Optional<Account> account = accountService.getAccount(accountNumber);
        return account.map(a -> "Balance of account " + accountNumber + " is: " + a.getBalance())
                      .orElse("Account not found");
    }
}
