package com.klarmeister.nerdquiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import com.klarmeister.nerdquiz.controller.FrageBoardController;
import com.klarmeister.nerdquiz.controller.TeamController;

@Configuration
public class QuizConfig {

    @Bean
    @ApplicationScope
    public TeamController teamController() {
        return new TeamController();
    }

    @Bean
    @ApplicationScope
    public FrageBoardController frageBoardController() {
        return new FrageBoardController();
    }
}
