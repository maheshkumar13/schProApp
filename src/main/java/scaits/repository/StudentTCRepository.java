package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.StudentBO;
import scaits.bo.student.StudentTCDetailsBO;


public interface StudentTCRepository extends JpaRepository<StudentTCDetailsBO, Long> {

	StudentTCDetailsBO findTop1ByOrderByIdDesc();

	StudentTCDetailsBO findByAdmissionNo(String studentNo);


	
	
}
