package com.amjed.texteditor.services.database.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.database.MongoDictionaryRepo;
import com.amjed.texteditor.database.MongoRequestsRepo;
import com.amjed.texteditor.entities.DictionaryEntity;
import com.amjed.texteditor.entities.RequestEntity;
import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.database.MongoServices;
import com.amjed.texteditor.services.text.DictionaryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MongoServicesImpl implements MongoServices {
    @Autowired
    private MongoRequestsRepo mongoRequestsRepo;
    @Autowired
    private MongoDictionaryRepo mongoDictionaryRepo;
    @Autowired
    private DictionaryLoader dictionaryLoader;

    /**
     * this method is to save a request to database
     * @param request the request to save to the database
     * @param requestDate date of the request
     * @param user is the remote user sent the request
     * @param ipAddress is the remote IP address of the request sender
     * @param url the url used to send the request
     * @param port is the remote port of the request sender
     * @param response is the response of the request
     * @param responseDate the date of the response
     */
    @Override
    public void saveRequest(Object request, Date requestDate, String user, String ipAddress, String url, Integer port, Object response, Date responseDate) {
        mongoRequestsRepo.save(new RequestEntity(request, requestDate, user, ipAddress, url, port, response, responseDate));
    }

    /**
     * this method is used to save or update dictionary to or in database
     * @param created is the date creation
     * @param updated date of update
     * @param createdBy is the name of the user created the dictionary
     * @param updatedBy is the name of the user updated the dictionary
     * @param ipAddress is the remote IP address of the request sender
     * @param URL is the url used to send the request
     * @param port is the remote port of the request sender
     * @param path
     * @return
     */
    @Override
    public String saveDictionary(Date created, Date updated, String createdBy, String updatedBy, String ipAddress,  String URL,Integer port, String path) {
        mongoDictionaryRepo.save(new DictionaryEntity(created, updated, createdBy, updatedBy, ipAddress, port, URL, dictionaryLoader.loadDictionary(path)));
        return "Done";
    }

    /**
     * this method is to load a dictionary from the database by ID
     * @param dictionaryID the ID of the dictionary in the database
     * @return a dictionary
     */
    @Override
    public DictionaryTrie loadDictionary(String dictionaryID) {
        Optional<DictionaryEntity> dictionaryEntity = mongoDictionaryRepo.findById(dictionaryID);
        return dictionaryEntity.get().getDictionaryTrie();
    }
    @Override
    public DictionaryTrie loadAdminDictionary(){
        return mongoDictionaryRepo.findAllByCreatedBy(Constants.INITIAL_DICTIONARY_CREATED_BY).get().getDictionaryTrie();
    }


    /**
     * this method is to load all requests from the database
     * @return the request log from database
     */
    @Override
    public ArrayList<DataInput> getAllRequests() {
        List<RequestEntity> dbList = mongoRequestsRepo.findAll();
        ArrayList<DataInput> requestsList = new ArrayList<>() ;
        for (RequestEntity entity : dbList) {
            requestsList.add((DataInput) entity.getRequest());
        }
        return requestsList;
    }

    /**
     * this method is to load requests from database sent in time range
     * @param fromDate is the starting date
     * @param toDate is the ending date
     * @return request log by date
     */
    @Override
    public ArrayList <DataInput> getAllRequests (Date fromDate,Date toDate) {
        List<RequestEntity> dbList = mongoRequestsRepo.findAll();
        ArrayList<DataInput> calculatorList = new ArrayList<>();
        for (RequestEntity entity : dbList) {
            if (entity.getRequestDate().after(fromDate) && entity.getRequestDate().before(toDate)) {
                calculatorList.add((DataInput) entity.getRequest());
            }
        }
        return calculatorList;
    }
}
