package Banking.Banking.Application.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Banking.Banking.Application.Demo.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}
