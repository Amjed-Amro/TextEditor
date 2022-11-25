package com.amjed.texteditor.controllers;

import com.amjed.texteditor.annotations.PasswordMatches;
import com.amjed.texteditor.models.Dto.UserDto;
import com.amjed.texteditor.services.business.UserServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
@Log4j2
@Controller
public class RegistrationController {

    @Autowired
    private UserServices userServices;

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/user/registration", consumes = {"application/json"})

    public @ResponseBody ResponseEntity<Object> registration(@RequestBody @Valid UserDto userDto,HttpServletRequest httpRequest) {
        log.info(httpRequest.getRequestURL());
                Object response = userServices.registerNewUserAccount(userDto, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
