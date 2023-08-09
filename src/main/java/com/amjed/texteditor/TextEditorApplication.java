package com.amjed.texteditor;


import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.database.MongoDictionaryRepo;
import com.amjed.texteditor.entities.DictionaryEntity;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.business.BusinessServiceHandler;
import com.amjed.texteditor.services.text.DictionaryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.Optional;


@SpringBootApplication
@EnableCaching
public class TextEditorApplication {
    @Autowired
    private BusinessServiceHandler businessServiceHandler;
    @Autowired
    private MongoDictionaryRepo dictionaryRepo;
    @Autowired
    DictionaryLoader dictionaryLoader;
    public static void main(String[] args) {
        SpringApplication.run(TextEditorApplication.class, args);
    }


    @PostConstruct
    private void loadDictionary (){
        if (dictionaryRepo.findAllByCreatedBy(Constants.INITIAL_DICTIONARY_CREATED_BY).isEmpty()){
            dictionaryRepo.save(new DictionaryEntity(null, null, Constants.INITIAL_DICTIONARY_CREATED_BY
                    , null, null, null, null, dictionaryLoader.loadDictionary(Constants.DICTIONARY_PATH)));
        }
    }

}
