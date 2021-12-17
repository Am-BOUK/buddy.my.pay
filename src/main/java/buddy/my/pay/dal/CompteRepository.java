package buddy.my.pay.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import buddy.my.pay.entity.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {
	@Query(nativeQuery = true, value = "SELECT * FROM Compte WHERE Email_CLIENT LIKE :x")
	public Page<Compte> findComptesByEmail(@Param("x") String email, Pageable pageable);
}
