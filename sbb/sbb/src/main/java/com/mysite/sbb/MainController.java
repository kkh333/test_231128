package com.mysite.sbb;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String redirect() {
        return "redirect:/article/list";
    }
}
