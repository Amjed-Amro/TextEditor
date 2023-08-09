package com.amjed.texteditor.services.business.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.Dto.Response;
import com.amjed.texteditor.services.business.BusinessServiceHandler;
import com.amjed.texteditor.services.database.MongoServices;
import com.amjed.texteditor.services.text.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Service
public class BusinesServiceHandlerImpl implements BusinessServiceHandler {
    @Autowired
    private MongoServices mongoServices;
    @Autowired
    private ResponseHandlerImpl responseHandler;
    @Autowired
    private PredictCompletion predictCompletion;
    @Autowired
    private FindPath findPath;
    @Autowired
    private DocumentProcess documentProcess;
    @Autowired
    private CaesarCipher caesarCipher;
    @Autowired
    private VigenereCipher vigenereCipher;

    @Override
    public Response savaDictionary(HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(responseHandler.buildApiResponse(mongoServices.saveDictionary(new Date(),null,
                    httpRequest.getRemoteUser(),null,httpRequest.getRemoteAddr(),httpRequest.getRequestURL().toString()
                    ,httpRequest.getRemotePort(),Constants.DICTIONARY_PATH), Boolean.TRUE),Boolean.TRUE);
            mongoServices.saveRequest(null, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("new dictionary was saved successfully");
            return response;

        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(null, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to save new dictionary, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response getDocumentData(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(documentProcess.processText(dataInput.getText()),Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("document processing was successful");
            return response;

        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to process document, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response getDistance(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(findPath.findPath(dataInput.getFirstWord(), dataInput.getSecondWord(),
                    mongoServices.loadAdminDictionary()),Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("finding distance was successful");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to find distance, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response getPredictions(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(predictCompletion.predictCompletions(dataInput.getFirstWord(), 6,
                    mongoServices.loadAdminDictionary()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("finding predictions was successful");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to find predictions, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response getSpellingCheck(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(predictCompletion.predictCompletions(dataInput.getFirstWord(), 6,
                    mongoServices.loadAdminDictionary()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("spelling check was successful");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to check spilling, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response encryptMessage(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(caesarCipher.encrypt(dataInput.getNumber(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was encrypted successfully using caesar cipher one key");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to encrypt message using caesar cipher one key, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response encryptMessageTwoKeys(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(caesarCipher.encrypt(dataInput.getNumber(),dataInput.getNumber2()
                    ,dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was encrypted successfully using caesar cipher two keys");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to encrypt message using caesar cipher two keys, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response decryptMessage(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(caesarCipher.decrypt(dataInput.getNumber(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was decrypted successfully using caesar cipher one key");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to decrypt message using caesar cipher one key, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response decryptMessageTwoKeys(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(caesarCipher.decrypt(dataInput.getNumber()
                    ,dataInput.getNumber2(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was decrypted successfully using caesar cipher two keys");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to decrypt message using caesar cipher two keys, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response crackMessage(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(caesarCipher.crackCaesarCipher(dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was cracked successfully");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to crack message, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response vigEncrypt(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(vigenereCipher.encrypt(dataInput.getFirstWord(), dataInput.getSecondWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was encrypted successfully using vigenere cipher");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to encrypt message using vigenere cipher, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response vigDecrypt(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(vigenereCipher.decrypt(dataInput.getFirstWord(), dataInput.getSecondWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("message was decrypted successfully using vigenere cipher");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to decrypt message using vigenere cipher, error:{}",exception.getMessage());
            return response;
        }
    }
    @Override
    public Response vigBreak(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(vigenereCipher.breakVigenere(dataInput.getFirstWord(),
                    mongoServices.loadAdminDictionary()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("break message was successful using vigenere cipher breaker");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to break message using vigenere cipher, error:{}",exception.getMessage());
            return response;
        }
    }


    @Override
    public Response getAllRequests( HttpServletRequest httpRequest) {
        try {
            Response response = responseHandler.buildApiResponse(mongoServices.getAllRequests(), Boolean.TRUE);
            mongoServices.saveRequest(null, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(),
                    httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(), response, new Date());
            log.info("all requests were loaded successfully");
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(null, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    ,httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to load all request, error:{}",exception.getMessage());
            return response;
        }
    }

    @Override
    public Response getAllRequests(DataInput dataInput, HttpServletRequest httpRequest) {
        try {
            Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataInput.getFromDate());
            Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataInput.getToDate());
            Response response = responseHandler.buildApiResponse(mongoServices.getAllRequests(fromDate,toDate), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr(), httpRequest.getRequestURL().toString()
                    , httpRequest.getRemotePort(), response, new Date());
            log.info("all requests were loaded successfully from {} to {} ",fromDate,toDate);
            return response;
        } catch (Exception exception) {
            Response response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, new Date(), httpRequest.getRemoteUser(), httpRequest.getRemoteAddr()
                    , httpRequest.getRequestURL().toString(), httpRequest.getRemotePort(),response, new Date());
            log.error("failed to load all request by date, error:{}",exception.getMessage());
            return response;
        }
    }

}
