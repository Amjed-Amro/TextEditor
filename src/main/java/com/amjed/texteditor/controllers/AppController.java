package com.amjed.texteditor.controllers;


import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.Dto.Response;
import com.amjed.texteditor.services.business.BusinessServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RequestMapping(path = "/")
@RestController
public class AppController {
    @Autowired
    private BusinessServiceHandler businessServiceHandler;

    @GetMapping(value = "saveDictionary")
    public ResponseEntity<Response> savaDictionary(HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.savaDictionary(httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "getDocumentData")
    public ResponseEntity<Response> getDocumentData(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.getDocumentData(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "getDistance")
    public ResponseEntity<Response> getDistance(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.getDistance(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "getPredictions")
    public ResponseEntity<Response> getPredictions(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.getPredictions(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "getSpellingCheck")
    public ResponseEntity<Response> getSpellingCheck(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.getSpellingCheck(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "encryptMessage")
    public ResponseEntity<Response> encryptMessage(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.encryptMessage(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "encryptMessageTwoKeys")
    public ResponseEntity<Response> encryptMessageTwoKeys(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.encryptMessageTwoKeys(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "decryptMessage")
    public ResponseEntity<Response> decryptMessage(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.decryptMessage(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "decryptMessageTwoKeys")
    public ResponseEntity<Response> decryptMessageTwoKeys(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.decryptMessageTwoKeys(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "crackMessage")
    public ResponseEntity<Response> crackMessage(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.crackMessage(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "vigEncrypt")
    public ResponseEntity<Response> vigEncrypt(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.vigEncrypt(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "vigDecrypt")
    public ResponseEntity<Response> vigDecrypt(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.vigDecrypt(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "vigBreak")
    public ResponseEntity<Response> vigBreak(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.vigBreak(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(value = "getAllRequests")
    public ResponseEntity<Response> getAllRequests(HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.getAllRequests(httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "getAllRequests")
    public ResponseEntity<Response> getAllRequests(@RequestBody DataInput dataInput, HttpServletRequest httpRequest) {
        Response response = businessServiceHandler.getAllRequests(dataInput, httpRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
