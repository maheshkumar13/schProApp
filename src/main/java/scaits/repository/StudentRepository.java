package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import scaits.bo.student.AcademicYearBO;
import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;

@Transactional(value = "mySqlTransactionManager")
public interface StudentRepository extends JpaRepository<StudentBO, String> {

	List<StudentBO> findByAcademicIdAndStudentNoContaining(AcademicYearBO acaVal, String admNo);

	List<StudentBO> findByAcademicIdAndSurNameContaining(AcademicYearBO acaVal, String surName);

	List<StudentBO> findByAcademicIdAndNameContaining(AcademicYearBO acaVal, String studentName);

	List<StudentBO> findByAcademicIdAndFatherNameContaining(AcademicYearBO acaVal, String parentName);

	List<StudentBO> findByAcademicId(AcademicYearBO academicYear);

	List<StudentBO> findByStudentNoContaining(String term);

	List<StudentBO> findByNameContaining(String term);

	List<StudentBO> findByClassId(StudyClassBO classId);

	StudentBO findByStudentNo(String admNo);

	List<StudentBO> findByClassIdAndSectionId(StudyClassBO classId,ClassSectionBO sectionId);

	List<StudentBO> findByAcademicIdAndMobileNoContaining(AcademicYearBO acaVal, String mobileNo);

}
