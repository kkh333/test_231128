package com.mysite.sbb.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list (Model model) {
        List<Article> articleList = this.articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/create")
    public String create () {
        return "article_create";
    }

    @PostMapping("/create")
    public String create (@RequestParam("subject") String subject, @RequestParam("content") String content) {
        this.articleService.articleCreate(subject, content);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail (Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }
}
