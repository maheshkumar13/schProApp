package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import scaits.bo.common.TestBO;
import scaits.bo.student.ClassSectionBO;


public interface TestRepository extends JpaRepository<TestBO, Long> {

	List<TestBO> findByStatus(boolean b);

	List<TestBO> findByStatusAndName(boolean b, String testTypeVal);
	
	@Query(value = "select * from  t_test  where  status=true and test_id in (?1)", nativeQuery = true)
	List<TestBO> findByTestIds(List<Long> ids);

	
	
}
