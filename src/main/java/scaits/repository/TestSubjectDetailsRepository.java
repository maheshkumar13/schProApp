package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.common.TestBO;
import scaits.bo.common.TestSubjectDetailsBO;
import scaits.bo.student.SubjectBO;


public interface TestSubjectDetailsRepository extends JpaRepository<TestSubjectDetailsBO, Long> {

	TestSubjectDetailsBO findByTestAndTestSubject(TestBO testVal, SubjectBO mathSub);

	
	
}
