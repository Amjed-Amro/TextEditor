package com.amjed.TextEditor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class TextEditorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextEditorApplication.class, args);
    }
}
