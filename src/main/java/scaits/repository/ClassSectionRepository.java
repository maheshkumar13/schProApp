package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.StudyClassBO;

public interface ClassSectionRepository extends JpaRepository<ClassSectionBO, Long> {

	List<ClassSectionBO> findByClassId(StudyClassBO classId);


}
