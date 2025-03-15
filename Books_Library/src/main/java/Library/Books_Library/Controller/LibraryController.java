package Library.Books_Library.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Library.Books_Library.Entity.books;
import Library.Books_Library.Service.BooksService;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/books")
public class LibraryController {

	private BooksService service;
	
	private LibraryController(BooksService service) {
		this.service = service;
	}
	@GetMapping("/")
	public String findBooks(Model model) {
		model.addAttribute("book" ,service.findAll());
		return "/home";
	}
	@GetMapping("/add")
	public String add() {
		return"/booksAdd";
	}
	@PostMapping("/added")
	public String bookadd(@ModelAttribute books book) {
		service.saveData(book);
		return "redirect:/books/";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam Long id) {
		service.delete(id);
		return"redirect:/books/";
	}
}
