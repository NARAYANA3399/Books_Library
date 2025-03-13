package Banking.Banking.Application.Demo.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Banking.Banking.Application.Demo.Entity.Account;
import Banking.Banking.Application.Demo.Service.AccountService;

@Controller
@RequestMapping("/bank")
public class BankController {

	private AccountService service;
	
	private BankController(AccountService service) {
		this.service = service;
	}
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("message","Bank Of Sample");
		return "home";
	}
	@PostMapping("/create")
	@ResponseBody
	public String createAccount(@RequestParam String accountNumber,@RequestParam double balance) {
		service.createAccount(accountNumber, balance);
		return "Account " + accountNumber + " created with balance: " + balance;
	}
	@PostMapping("/deposit")
	@ResponseBody
	public String deposite(@RequestParam String accountNumber,@RequestParam double amount) {
		
		if(service.deposte(accountNumber, amount)) {
			return "Account " + accountNumber + " Deposite with balance: " + amount ;
		}
		return "Account Not Found";
	}
	@PostMapping("/withdraw")
	@ResponseBody
	public String withDraw(@RequestParam String accountNumber,@RequestParam double amount) {
		if(service.withdraw(accountNumber, amount)) {
			return "Account " + accountNumber + " WithDraw with balance: " + amount;
		}
		return "Account Not Found";
	}
	@PostMapping("/transfer")
	@ResponseBody
	public String tranction(@RequestParam String fromAccount,@RequestParam String toAccount,@RequestParam double amount) {
		if(service.tarancation(fromAccount, toAccount, amount)) {
			return"Sender Account " + fromAccount + ", Recvier Account "+ toAccount  + " Tranfer Amount: " + amount;
		}
		return "Account Not Found";
	}
	@GetMapping("/balance")
	@ResponseBody
	public String balance(@RequestParam String accountNumber) {
		Optional<Account> account = service.getAccount(accountNumber);
		return account.map(a ->"Your Account No : " + accountNumber + " Total Balance: " + a.getBalance()
			
		).orElse("Account Not Found");
		
		 
	}
}
