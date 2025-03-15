package Library.Books_Library.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library.Books_Library.Entity.books;
import Library.Books_Library.Repository.BooksRepo;

@Service
public class BooksService {

	@Autowired
	private BooksRepo repo;
	
	private BooksService(BooksRepo repo) {
		this.repo = repo;
	}
	
	public List<books> findAll() {
		return repo.findAll() ;
	}
	public books saveData(books book) {
		return repo.save(book);
	}
	public void delete(Long id) {
		 repo.deleteById(id);
	}
}
