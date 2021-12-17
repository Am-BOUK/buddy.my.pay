package buddy.my.pay.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import buddy.my.pay.entity.Client;


public interface ClientRepository extends JpaRepository<Client, String> {
	@Query(nativeQuery = true, value= "SELECT * FROM Client WHERE name LIKE :x")
    public Page<Client> findClientByName(@Param("x")String name, Pageable pageable);
}
