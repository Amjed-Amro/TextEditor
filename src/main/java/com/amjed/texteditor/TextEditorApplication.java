package com.amjed.texteditor;


import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.text.DictionaryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableCaching
public class TextEditorApplication {


    @Autowired
    DictionaryLoader dictionaryLoader;
    public static void main(String[] args) {
        SpringApplication.run(TextEditorApplication.class, args);
    }

/**
    @PostConstruct
    private void LoadDictionary(){
       reposnen=  repo.getObject;
       if(Object.isnull(respose)){
           DictionaryTrie dictionaryTrie = dictionaryLoader.loadDictionary(Constants.DICTIONARY_PATH);

       }
        if()
        DictionaryTrie dictionaryTrie = dictionaryLoader.loadDictionary(Constants.DICTIONARY_PATH);
        repo.save (DictionaryTrie);
    }
    **/
}
