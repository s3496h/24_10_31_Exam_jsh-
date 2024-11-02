package org.koreait.exit.exam_10_31.faq.controller;

import org.koreait.exit.exam_10_31.faq.entity.FAQ;
import org.koreait.exit.exam_10_31.faq.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
public class FAQController {
    @Autowired
    private FAQRepository faqRepository;

    // FAQ 데이터 추가 API
    @PostMapping
    public FAQ createFAQ(@RequestBody FAQ faq) {
        return faqRepository.save(faq);
    }
    @GetMapping
    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }
}