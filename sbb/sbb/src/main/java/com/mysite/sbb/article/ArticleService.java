package com.mysite.sbb.article;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void articleCreate(String subject, String content) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }

    public List<Article> getArticleList() {
        return this.articleRepository.findAll();
    }

    public Article getArticle(Integer id) {
        Optional<Article> oa = this.articleRepository.findById(id);
        if (oa.isPresent()) {
            return oa.get();
        }
        else {
            throw new DataNotFoundException("article not found");
        }
    }
}
