package buddy.my.pay.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import buddy.my.pay.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
//	@Query("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
	@Query(nativeQuery = true, value = "SELECT * FROM Operation WHERE CODE_COMPTE LIKE :x")
	public Page<Operation> listOperation(@Param("x") String codeCompte, Pageable pageable);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM Operation WHERE CODE_COMPTE_.Email_CLIENT LIKE :x")
//	public Page<Operation> listOperationByEmail(@Param("x") String email, Pageable pageable);

}
