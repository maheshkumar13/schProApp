package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.common.ExamAnalysisBO;
import scaits.bo.common.TestBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;


public interface ExamAnalysisRepository extends JpaRepository<ExamAnalysisBO, Long> {

	ExamAnalysisBO findByStudentAndTest(StudentBO stuval, TestBO testVal);

	List<ExamAnalysisBO> findByClassIdAndTest(StudyClassBO studyClass, TestBO testVal);

	
	
}
