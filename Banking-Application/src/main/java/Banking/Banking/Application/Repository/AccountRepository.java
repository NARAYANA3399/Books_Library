package Banking.Banking.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Banking.Banking.Application.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {}
