package com.mysite.sbb;

import com.mysite.sbb.article.ArticleRepository;
import com.mysite.sbb.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private ArticleService articleService;
	@Test
	void exampleData() {
		for (int i = 1; i <= 100; i++) {
			String subject = "제목입니다" + i + ".";
			String content = "내용입니다" + i + ".";
			this.articleService.articleCreate(subject, content);
		}
	}

}
