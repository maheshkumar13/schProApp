package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import scaits.bo.common.HalfYearlyBO;
import scaits.bo.common.PeriodicTestBO;
import scaits.bo.common.TestBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;


public interface HalfYearlyRepository extends JpaRepository<HalfYearlyBO, Long> {

	HalfYearlyBO findByStudentAndTest(StudentBO stuval, TestBO testVal);
	
	List<HalfYearlyBO> findByClassId(StudyClassBO study);

	List<HalfYearlyBO> findByClassIdAndTest(StudyClassBO studyClass, TestBO testVal);

	List<HalfYearlyBO> findByMathsIsNull();

	List<HalfYearlyBO> findByPhysicsIsNull();

	List<HalfYearlyBO> findByChemistryIsNull();

	List<HalfYearlyBO> findByEnglishIsNull();

	List<HalfYearlyBO> findByHindiIsNull();

	List<HalfYearlyBO> findByEvcIsNull();

	List<HalfYearlyBO> findByComputersTotalIsNull();

	HalfYearlyBO findByStudent(StudentBO student);
	
	@Query(value = "SELECT H.* FROM T_HALF_YEARLY H, T_STUDENT S , T_CLASS_SECTION CS WHERE H.STUDENT_ID=S.STUDENT_NO AND  S.CLASS_ID = CS.CLASS_ID AND S.CLASS_ID = ?1 AND CS.SECTION_ID = ?2", nativeQuery = true)
	List<HalfYearlyBO> findByClass(Long studyClass, Long section);

	HalfYearlyBO findByStudentAndClassId(StudentBO stuval,StudyClassBO classId);


	
	
}
