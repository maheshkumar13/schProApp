package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.StudentBO;
import scaits.bo.student.TransactionBO;


public interface TransactionRepository extends JpaRepository<TransactionBO, Long> {

	List<TransactionBO> findByStudent(StudentBO admNo);

	TransactionBO findByStudentAndHeadCode(StudentBO studentNo, String appFee);

	List<TransactionBO> findByStudentAndHeadCodeNotIn(StudentBO studentNo, String appFee);

	List<TransactionBO> findByStudentAndHeadCodeIn(StudentBO student, List<String> headList);

	
	
}
