package com.amjed.TextEditor.services.database;


import com.amjed.TextEditor.models.dictionary.DictionaryTrie;

import java.util.Date;

public interface MongoServices {
    public void saveRequest(Object request, Date requestDate, String user, String ipAddress, String url, Integer port, Object response, Date responseDate);

    public void saveDictionary(Date created, Date updated, String createdBy, String updatedBy, String ipAddress, Double port, String URL, String path);

    public DictionaryTrie loadDictionary(String dictionaryID);
}
