package org.koreait.exit.exam_10_31.faq.repository;

import org.koreait.exit.exam_10_31.faq.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    List<FAQ> findByQuestionContainingIgnoreCase(String keyword);
    FAQ findByQuestionIgnoreCase(String question);
}
