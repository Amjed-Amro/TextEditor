package com.amjed.TextEditor.configs;

import com.amjed.TextEditor.services.business.BusinessServiceHandler;
import com.amjed.TextEditor.services.business.TextResponseHandler;
import com.amjed.TextEditor.services.business.implimentation.BusinesServiceHandlerImpl;
import com.amjed.TextEditor.services.business.implimentation.TextResponseHandlerImpl;
import com.amjed.TextEditor.services.database.MongoServices;
import com.amjed.TextEditor.services.database.implementation.MongoServicesImpl;
import com.amjed.TextEditor.services.internal.*;
import com.amjed.TextEditor.services.internal.implementation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {

    @Bean
    public BusinessServiceHandler businessServiceHandler() {
        return new BusinesServiceHandlerImpl();
    }

    @Bean
    public TextResponseHandler textResponseHandler() {
        return new TextResponseHandlerImpl();
    }

    @Bean
    public MongoServices mongoServices() {
        return new MongoServicesImpl();
    }

    @Bean
    public DictionaryLoader dictionaryLoader() {
        return new DictionaryLoaderImpl();
    }

    @Bean
    public FindPath findPath() {
        return new FindPathImpl();
    }

    @Bean
    public DocumentProcess documentProcess() {
        return new DocumentProcessImpl();
    }

    @Bean
    public NearbyWords nearbyWords() {
        return new NearbyWordsImpl();
    }

    @Bean
    public PredictCompletion predictCompletion() {
        return new PredictCompletionImpl();
    }

    @Bean
    public VigenereCipher vigenereCipher() {
        return new VigenereCipherImpl();
    }

}
