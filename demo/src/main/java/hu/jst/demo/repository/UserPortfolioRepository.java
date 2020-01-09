package hu.jst.demo.repository;

import hu.jst.demo.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {

}
