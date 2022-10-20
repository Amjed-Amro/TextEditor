package com.amjed.TextEditor.services.business;

import com.amjed.TextEditor.models.DataInput;

import java.util.Date;

public interface BusinessServiceHandler {
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
}
