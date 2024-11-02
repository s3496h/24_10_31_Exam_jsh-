package org.koreait.exit.exam_10_31.question.service;

import org.koreait.exit.exam_10_31.faq.entity.FAQ;
import org.koreait.exit.exam_10_31.faq.repository.FAQRepository;
import org.koreait.exit.exam_10_31.question.entity.Question;
import org.koreait.exit.exam_10_31.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromptAIService {
    @Autowired
    private FAQRepository faqRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public String getAutoResponse(String userQuestion) {
        // 사용자가 입력한 질문과 정확히 일치하는 FAQ 검색
        FAQ exactMatchFAQ = faqRepository.findByQuestionIgnoreCase(userQuestion);
        String response;

        if (exactMatchFAQ != null) {
            // 정확히 일치하는 질문이 있다면 해당 FAQ의 답변 반환
            response = exactMatchFAQ.getAnswer();
        } else {
            // 정확히 일치하는 질문이 없을 경우, 키워드 기반 검색 수행
            response = "현재 답변을 제공할 수 없습니다. 관리자에게 문의해 주세요.";
            String[] keywords = userQuestion.split(" ");

            for (String keyword : keywords) {
                List<FAQ> faqs = faqRepository.findByQuestionContainingIgnoreCase(keyword);
                if (!faqs.isEmpty()) {
                    FAQ bestMatch = faqs.get(0);
                    response = bestMatch.getAnswer();
                    break;
                }
            }
        }

        // 질문과 시스템의 응답을 Question 엔티티에 저장
        Question question = new Question();
        question.setUserQuestion(userQuestion);
        question.setSystemAnswer(response);
        question.setAskedAt(LocalDateTime.now());
        questionRepository.save(question);

        return response;
    }
}