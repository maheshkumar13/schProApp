package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.SubjectBO;


public interface SubjectRepository extends JpaRepository<SubjectBO, Long> {

	SubjectBO findBySubjectName(String string);

	List<SubjectBO> findByStatus(boolean b);

}
