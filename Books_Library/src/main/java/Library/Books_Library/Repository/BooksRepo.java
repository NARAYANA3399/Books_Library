package Library.Books_Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Library.Books_Library.Entity.books;

@Repository
public interface BooksRepo extends JpaRepository<books, Long>{

}
