package com.mysite.sbb.answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.article.Article;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer answerCreate (Article article, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setArticle(article);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
