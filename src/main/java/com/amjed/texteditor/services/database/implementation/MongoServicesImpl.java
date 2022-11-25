package com.amjed.texteditor.services.database.implementation;

import com.amjed.texteditor.database.MongoDictionaryRepo;
import com.amjed.texteditor.database.MongoRequestsRepo;
import com.amjed.texteditor.database.MongoUserRepository;
import com.amjed.texteditor.entities.DictionaryEntity;
import com.amjed.texteditor.entities.RequestEntity;
import com.amjed.texteditor.entities.UserEntity;
import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.Dto.UserDto;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.database.MongoServices;
import com.amjed.texteditor.services.text.DictionaryLoader;
import com.amjed.texteditor.services.users.implementation.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
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
    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Override
    public void saveRequest(Object request, Date requestDate, String user, String ipAddress, String url, Integer port, Object response, Date responseDate) {
        mongoRequestsRepo.save(new RequestEntity(request, requestDate, user, ipAddress, url, port, response, responseDate));
    }
    @Override
    public String saveDictionary(Date created, Date updated, String createdBy, String updatedBy, String ipAddress,  String URL,Integer port, String path) {
        mongoDictionaryRepo.save(new DictionaryEntity(created, updated, createdBy, updatedBy, ipAddress, port, URL, dictionaryLoader.loadDictionary(path)));
        return "Done";
    }
    @Override
    public DictionaryTrie loadDictionary(String dictionaryID) {
        Optional<DictionaryEntity> dictionaryEntity = mongoDictionaryRepo.findById(dictionaryID);
        return dictionaryEntity.get().getDictionaryTrie();
    }
    @Override
    public ArrayList<DataInput> getAllRequests() {
        List<RequestEntity> dbList = mongoRequestsRepo.findAll();
        ArrayList<DataInput> requestsList = new ArrayList<>() ;
        for (RequestEntity entity : dbList) {
            requestsList.add((DataInput) entity.getRequest());
        }
        return requestsList;
    }
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

    @Override
    public Boolean registerNewUserAccount(UserDto userDto)  {
        if (emailExists(userDto.getEmail())) {
            return Boolean.FALSE;
        }

        UserEntity user = new UserEntity();
        user.setUserDictionaryTrie(new DictionaryTrie());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setUserRule("User");
        user.setEnabled(Boolean.TRUE);
        user.setAccountNonExpired(Boolean.TRUE);
        user.setAccountNonLocked(Boolean.TRUE);
        user.setCredentialsNonExpired(Boolean.TRUE);

        mongoUserRepository.save(user);
        return true;
    }
    //TODO: isEmail....
    private boolean emailExists(String email) {
        if (mongoUserRepository.findById(email) != null){
            return true;
        }
        return (false);
    }

}
