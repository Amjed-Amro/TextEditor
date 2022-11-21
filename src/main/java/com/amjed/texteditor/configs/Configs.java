package com.amjed.texteditor.configs;

import com.amjed.texteditor.services.business.BusinessServiceHandler;
import com.amjed.texteditor.services.business.ResponseHandler;
import com.amjed.texteditor.services.business.UserServices;
import com.amjed.texteditor.services.business.implementation.BusinesServiceHandlerImpl;
import com.amjed.texteditor.services.business.implementation.ResponseHandlerImpl;
import com.amjed.texteditor.services.business.implementation.UserServicesImpl;
import com.amjed.texteditor.services.database.MongoServices;
import com.amjed.texteditor.services.database.implementation.MongoServicesImpl;
import com.amjed.texteditor.services.text.*;
import com.amjed.texteditor.services.text.implementation.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@ComponentScan ("com.amjed.texteditor")
@EnableAutoConfiguration
@Configuration
public class Configs {

    @Bean
    public BusinessServiceHandler businessServiceHandler() {
        return new BusinesServiceHandlerImpl();
    }

    @Bean
    public ResponseHandler responseHandler() {
        return new ResponseHandlerImpl();
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

    @Bean
    @Primary
    public UserServices usersServices() {
        return new UserServicesImpl();
    }


}
