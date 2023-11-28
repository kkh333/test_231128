package com.mysite.sbb.article;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list (Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Article> articlePage = this.articleService.getList(page);
        model.addAttribute("articlePage", articlePage);
        return "article_list";
    }

    @GetMapping("/create")
    public String create (ArticleForm articleForm) {
        return "article_create";
    }

    @PostMapping("/create")
    public String create (@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_create";
        }
        this.articleService.articleCreate(articleForm.getSubject(), articleForm.getContent());
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail (Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }
}
