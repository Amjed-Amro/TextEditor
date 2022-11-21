package com.amjed.texteditor.services.business.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.services.business.BusinessServiceHandler;
import com.amjed.texteditor.services.database.MongoServices;
import com.amjed.texteditor.services.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BusinesServiceHandlerImpl implements BusinessServiceHandler {
    @Autowired
    private MongoServices mongoServices;
    @Autowired
    private ResponseHandlerImpl responseHandler;
    @Autowired
    private DictionaryLoader dictionaryLoader;
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
    public Object savaDictionary(Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(mongoServices.saveDictionary(requestDate,null,
                    user,null,ipAddress,url,port,Constants.DICTIONARY_PATH), Boolean.TRUE);
            mongoServices.saveRequest(null, requestDate, user, ipAddress, url, port, response, new Date());
            return response;

        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(null, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }
    @Override
    public Object getDocumentData(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(documentProcess.processText(dataInput.getText()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getDistance(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(findPath.findPath(dataInput.getFirstWord(), dataInput.getSecondWord(),
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getPredictions(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(predictCompletion.predictCompletions(dataInput.getFirstWord(), 6,
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getSpellingCheck(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(predictCompletion.isCorrect(dataInput.getFirstWord(),
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object encryptMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(caesarCipher.encrypt(dataInput.getNumber(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object encryptMessageTwoKeys(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(caesarCipher.encrypt(dataInput.getNumber(), dataInput.getNumber2(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object decryptMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(caesarCipher.decrypt(dataInput.getNumber(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object decryptMessageTwoKeys(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(caesarCipher.decrypt(dataInput.getNumber(), dataInput.getNumber2(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object crackMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(caesarCipher.crackCaesarCipher(dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object vigEncrypt(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(vigenereCipher.encrypt(dataInput.getFirstWord(), dataInput.getSecondWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object vigDecrypt(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(vigenereCipher.decrypt(dataInput.getFirstWord(), dataInput.getSecondWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object vigBreak(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(vigenereCipher.breakVigenere(dataInput.getFirstWord(),
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getAllRequests( Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = responseHandler.buildApiResponse(mongoServices.getAllRequests(), Boolean.TRUE);
            mongoServices.saveRequest(null, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE);
            mongoServices.saveRequest(null, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }
    @Override
    public Object getAllRequests(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataInput.getFromDate());
            Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataInput.getToDate());
            Object response = responseHandler.buildApiResponse(mongoServices.getAllRequests(fromDate,toDate), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    responseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

}
