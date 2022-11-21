package com.amjed.texteditor.services.database;


import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.Dto.UserDto;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.users.implementation.UserAlreadyExistException;

import java.util.ArrayList;
import java.util.Date;

public interface MongoServices {
    public void saveRequest(Object request, Date requestDate, String user, String ipAddress, String url, Integer port, Object response, Date responseDate);

    public String saveDictionary(Date created, Date updated, String createdBy, String updatedBy, String ipAddress,  String URL, Integer port,String path);

    public DictionaryTrie loadDictionary(String dictionaryID);

    public ArrayList<DataInput> getAllRequests();
    public ArrayList <DataInput> getAllRequests (Date fromDate,Date toDate);
    public Boolean registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
}
