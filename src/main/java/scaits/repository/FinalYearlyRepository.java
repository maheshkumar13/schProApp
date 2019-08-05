package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import scaits.bo.common.FinalYearlyBO;
import scaits.bo.common.TestBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;


public interface FinalYearlyRepository extends JpaRepository<FinalYearlyBO, Long> {

	FinalYearlyBO findByStudentAndTest(StudentBO stuval, TestBO testVal);
	
	List<FinalYearlyBO> findByClassId(StudyClassBO study);

	List<FinalYearlyBO> findByClassIdAndTest(StudyClassBO studyClass, TestBO testVal);

	List<FinalYearlyBO> findByMathsIsNull();

	List<FinalYearlyBO> findByPhysicsIsNull();

	List<FinalYearlyBO> findByChemistryIsNull();

	List<FinalYearlyBO> findByEnglishIsNull();

	List<FinalYearlyBO> findByHindiIsNull();

	List<FinalYearlyBO> findByEvcIsNull();

	List<FinalYearlyBO> findByComputersTotalIsNull();
	
	@Query(value = "SELECT F.* FROM T_FINAL_YEARLY F, T_STUDENT S , T_CLASS_SECTION CS WHERE F.STUDENT_ID=S.STUDENT_NO AND  S.CLASS_ID = CS.CLASS_ID AND S.CLASS_ID = ?1 AND CS.SECTION_ID = ?2", nativeQuery = true)
	List<FinalYearlyBO> findByClass(Long studyClass, Long section);

	FinalYearlyBO findByStudentAndClassId(StudentBO stuval, StudyClassBO classId);


	
	
}
