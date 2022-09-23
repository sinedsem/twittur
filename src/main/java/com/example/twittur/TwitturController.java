package com.example.twittur;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwitturController {

    @PostMapping("/load")
    public void loadTweets() {
        System.out.println("hello");
    }



}
