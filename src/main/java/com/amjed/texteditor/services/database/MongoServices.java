package com.amjed.texteditor.services.database;


import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;

import java.util.ArrayList;
import java.util.Date;

public interface MongoServices {
    void saveRequest(Object request, Date requestDate, String user, String ipAddress, String url, Integer port, Object response, Date responseDate);

    String saveDictionary(Date created, Date updated, String createdBy, String updatedBy, String ipAddress,  String URL, Integer port,String path);

    DictionaryTrie loadDictionary(String dictionaryID);

    DictionaryTrie loadAdminDictionary();

    ArrayList<DataInput> getAllRequests();
    ArrayList <DataInput> getAllRequests (Date fromDate,Date toDate);
}
