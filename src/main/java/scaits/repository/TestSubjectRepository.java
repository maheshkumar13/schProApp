package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.common.TestBO;
import scaits.bo.common.TestSubjectDetailsBO;


public interface TestSubjectRepository extends JpaRepository<TestSubjectDetailsBO, Long> {

	List<TestSubjectDetailsBO> findByTest(TestBO testVal);

	List<TestSubjectDetailsBO> findByTestAndStatus(TestBO testVal, boolean b);



	
	
}
