package com.amjed.TextEditor.services.database.implementation;

import com.amjed.TextEditor.database.MongoDictionaryRepo;
import com.amjed.TextEditor.database.MongoRequestsRepo;
import com.amjed.TextEditor.entities.DictionaryEntity;
import com.amjed.TextEditor.entities.RequestEntity;
import com.amjed.TextEditor.models.dictionary.DictionaryTrie;
import com.amjed.TextEditor.services.database.MongoServices;
import com.amjed.TextEditor.services.internal.DictionaryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MongoServicesImpl implements MongoServices {
    @Autowired
    private MongoRequestsRepo mongoRequestsRepo;
    @Autowired
    private MongoDictionaryRepo mongoDictionaryRepo;
    @Autowired
    private DictionaryLoader dictionaryLoader;

    @Override
    public void saveRequest(Object request, Date requestDate, String user, String ipAddress, String url, Integer port, Object response, Date responseDate) {
        mongoRequestsRepo.save(new RequestEntity(request, requestDate, user, ipAddress, url, port, response, responseDate));
    }

    @Override
    public void saveDictionary(Date created, Date updated, String createdBy, String updatedBy, String ipAddress, Double port, String URL, String path) {
        mongoDictionaryRepo.save(new DictionaryEntity(created, updated, createdBy, updatedBy, ipAddress, port, URL, dictionaryLoader.loadDictionary(path)));
    }

    @Override
    public DictionaryTrie loadDictionary(String dictionaryID) {
        Optional<DictionaryEntity> dictionaryEntity = mongoDictionaryRepo.findById(dictionaryID);
        return dictionaryEntity.get().getDictionaryTrie();
    }
}
