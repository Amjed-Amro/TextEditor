package com.amjed.texteditor.services.business;

import com.amjed.texteditor.models.Dto.DataInput;

import java.util.Date;

public interface BusinessServiceHandler {
    //TODO: there is no need for public here as it is by default public
    //TODO: Be Specif for retrun object
    //TODO: if the function accepts more than 4 param, you may encabslte to one object
    public Object savaDictionary(Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object getDocumentData(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object getDistance(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object getPredictions(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object getSpellingCheck(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object encryptMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object encryptMessageTwoKeys(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object decryptMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object decryptMessageTwoKeys(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object crackMessage(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object vigEncrypt(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object vigDecrypt(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object vigBreak(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object getAllRequests(Date requestDate, String user, String ipAddress, String url, Integer port);
    public Object getAllRequests(DataInput dataInput, Date requestDate, String user, String ipAddress, String url, Integer port);
}
