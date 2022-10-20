package com.amjed.TextEditor.services.business.implimentation;

import com.amjed.TextEditor.constants.Constants;
import com.amjed.TextEditor.models.DataInput;
import com.amjed.TextEditor.services.business.BusinessServiceHandler;
import com.amjed.TextEditor.services.database.MongoServices;
import com.amjed.TextEditor.services.internal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BusinesServiceHandlerImpl implements BusinessServiceHandler {
    @Autowired
    private MongoServices mongoServices;
    @Autowired
    private TextResponseHandlerImpl textResponseHandler;
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
    public Object getDocumentData(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(documentProcess.processText(dataInput.getText()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getDistance(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(findPath.findPath(dataInput.getFirstWord(), dataInput.getSecondWord(),
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getPredictions(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(predictCompletion.predictCompletions(dataInput.getFirstWord(), 6,
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object getSpellingCheck(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(predictCompletion.isCorrect(dataInput.getFirstWord(),
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object encryptMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(caesarCipher.encrypt(dataInput.getNumber(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object encryptMessageTwoKeys(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(caesarCipher.encrypt(dataInput.getNumber(), dataInput.getNumber2(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object decryptMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(caesarCipher.decrypt(dataInput.getNumber(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object decryptMessageTwoKeys(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(caesarCipher.decrypt(dataInput.getNumber(), dataInput.getNumber2(), dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object crackMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(caesarCipher.crackCaesarCipher(dataInput.getFirstWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object vigEncrypt(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(vigenereCipher.encrypt(dataInput.getFirstWord(), dataInput.getSecondWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }

    @Override
    public Object vigDecrypt(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(vigenereCipher.decrypt(dataInput.getFirstWord(), dataInput.getSecondWord()), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }
    @Cacheable ("requests")
    @Override
    public Object vigBreak(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port) {
        try {
            Object response = textResponseHandler.buildApiResponse(vigenereCipher.breakVigenere(dataInput.getFirstWord(),
                    mongoServices.loadDictionary(Constants.INITIAL_DICTIONARY_ID)), Boolean.TRUE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port, response, new Date());
            return response;
        } catch (Exception exception) {
            Object response = textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED, Boolean.FALSE);
            mongoServices.saveRequest(dataInput, requestDate, user, ipAddress, url, port,
                    textResponseHandler.buildApiResponse(Constants.RESPONSE_MESSAGE.FAILED.concat(exception.getMessage()), Boolean.FALSE), new Date());
            return response;
        }
    }


}
