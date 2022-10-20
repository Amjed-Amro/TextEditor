package com.amjed.TextEditor.controllers;

import com.amjed.TextEditor.models.DataInput;
import com.amjed.TextEditor.services.business.BusinessServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController

public class AppController {

    @Autowired
    private BusinessServiceHandler businessServiceHandler;

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/getDocumentData", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> getFleschScore(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.getDocumentData(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/getDistance", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> getDistance(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.getDistance(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/getPredictions", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> getPredictions(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.getPredictions(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/getSpellingCheck", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> getSpellingCheck(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.getSpellingCheck(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/encryptMessage", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> encryptMessage(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.encryptMessage(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/encryptMessageTwoKeys", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> encryptMessageTwoKeys(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.encryptMessageTwoKeys(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/decryptMessage", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> decryptMessage(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.decryptMessage(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/decryptMessageTwoKeys", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> decryptMessageTwoKeys(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.decryptMessageTwoKeys(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/crackMessage", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> crackMessage(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.crackMessage(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/vigEncrypt", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> vigEncrypt(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.vigEncrypt(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/vigDecrypt", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> vigDecrypt(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.vigDecrypt(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:8080")
    @PostMapping(value = "/vigBreak", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Object> vigBreak(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Object response = businessServiceHandler.vigBreak(dataInput, new Date(), httpRequest.getRemoteUser(),
                httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString(), httpRequest.getRemotePort());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
