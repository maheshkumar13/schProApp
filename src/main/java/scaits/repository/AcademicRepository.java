package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.AcademicYearBO;


public interface AcademicRepository extends JpaRepository<AcademicYearBO, Long> {

	
	AcademicYearBO findByReceiptStatus(String status);
	
}
