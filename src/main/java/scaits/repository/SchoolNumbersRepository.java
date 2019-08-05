package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.AcademicYearBO;
import scaits.bo.student.SchoolNumberBO;


public interface SchoolNumbersRepository extends JpaRepository<SchoolNumberBO, Long> {

	SchoolNumberBO findByAcademicId(AcademicYearBO academicYear);

	

	
	
}
