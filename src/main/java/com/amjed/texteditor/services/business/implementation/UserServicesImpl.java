package com.amjed.texteditor.services.business.implementation;


import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.Dto.UserDto;
import com.amjed.texteditor.services.business.ResponseHandler;
import com.amjed.texteditor.services.business.UserServices;
import com.amjed.texteditor.services.database.MongoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private MongoServices mongoServices;
    @Autowired
    private ResponseHandler responseHandler;

    public Object registerNewUserAccount(UserDto userDto, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(mongoServices.registerNewUserAccount(userDto), Boolean.TRUE);
            //mongoServices.saveRequest(userDto, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            //mongoServices.saveRequest(userDto, requestDate, user, ipAddress, url, port,
                    //responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }
}
