package com.mysite.sbb.answer;

import com.mysite.sbb.article.Article;
import com.mysite.sbb.article.ArticleRepository;
import com.mysite.sbb.article.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Queue;

@RequiredArgsConstructor
@RequestMapping("/answer")
@Controller
public class AnswerController {
    private final ArticleService articleService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String create (Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Article article = this.articleService.getArticle(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            return "article_detail";
        }
        this.answerService.answerCreate(article, answerForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }
}
