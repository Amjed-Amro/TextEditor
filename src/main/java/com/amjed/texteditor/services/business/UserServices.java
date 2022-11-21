package com.amjed.texteditor.services.business;

import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.Dto.UserDto;

import java.util.Date;

public interface UserServices {

    public Object registerNewUserAccount(UserDto userDto, Date requestDate, String user, String ipAddress, String url, Integer port);
}
