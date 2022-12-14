package com.amjed.texteditor.services.business;

import com.amjed.texteditor.models.Dto.DataInput;
import com.amjed.texteditor.models.Dto.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface BusinessServiceHandler {
    //TODO: if the function accepts more than 4 param, you may encabslte to one object
    Response savaDictionary(HttpServletRequest httpRequest);
    Response getDocumentData(DataInput dataInput, HttpServletRequest httpRequest);
    Response getDistance(DataInput dataInput, HttpServletRequest httpRequest);
    Response getPredictions(DataInput dataInput, HttpServletRequest httpRequest);
    Response getSpellingCheck(DataInput dataInput, HttpServletRequest httpRequest);
    Response encryptMessage(DataInput dataInput, HttpServletRequest httpRequest);
    Response encryptMessageTwoKeys(DataInput dataInput, HttpServletRequest httpRequest);
    Response decryptMessage(DataInput dataInput, HttpServletRequest httpRequest);
    Response decryptMessageTwoKeys(DataInput dataInput, HttpServletRequest httpRequest);
    Response crackMessage(DataInput dataInput, HttpServletRequest httpRequest);
    Response vigEncrypt(DataInput dataInput, HttpServletRequest httpRequest);
    Response vigDecrypt(DataInput dataInput, HttpServletRequest httpRequest);
    Response vigBreak(DataInput dataInput, HttpServletRequest httpRequest);
    Response getAllRequests(HttpServletRequest httpRequest);
    Response getAllRequests(DataInput dataInput, HttpServletRequest httpRequest);
}
