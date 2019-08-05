package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import scaits.bo.common.PeriodicTestBO;
import scaits.bo.common.TestBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;

public interface PeriodicTestRepository extends JpaRepository<PeriodicTestBO, Long> {

	PeriodicTestBO findByStudentAndTest(StudentBO stuval, TestBO testVal);

	List<PeriodicTestBO> findByClassIdAndTest(StudyClassBO studyClass, TestBO testVal);

	List<PeriodicTestBO> findByMathsIsNull();

	List<PeriodicTestBO> findByPhysicsIsNull();

	List<PeriodicTestBO> findByChemistryIsNull();

	List<PeriodicTestBO> findByEnglishIsNull();

	List<PeriodicTestBO> findByHindiIsNull();

	List<PeriodicTestBO> findByEvcIsNull();

	List<PeriodicTestBO> findByComputersTotalIsNull();

	List<PeriodicTestBO> findByTest(TestBO testVal);

	List<PeriodicTestBO> findByClassId(StudyClassBO studyClass);

	@Query(value = "SELECT P.* FROM T_PERIODIC_TEST P, T_STUDENT S , T_CLASS_SECTION CS WHERE P.STUDENT_ID=S.STUDENT_NO AND  S.CLASS_ID = CS.CLASS_ID AND S.CLASS_ID =?1 AND CS.SECTION_ID = ?2", nativeQuery = true)
	List<PeriodicTestBO> findByClass(Long studyClass, Long section);

	PeriodicTestBO findByStudentAndClassId(StudentBO student, StudyClassBO classId);

	PeriodicTestBO findByStudentAndClassIdAndTest(StudentBO student, StudyClassBO classId, TestBO testVal);

}
