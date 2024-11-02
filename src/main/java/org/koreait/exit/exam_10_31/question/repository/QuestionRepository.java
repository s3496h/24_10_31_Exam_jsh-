package org.koreait.exit.exam_10_31.question.repository;

import org.koreait.exit.exam_10_31.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}