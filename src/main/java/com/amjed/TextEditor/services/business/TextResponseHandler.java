package com.amjed.TextEditor.services.business;

import com.amjed.TextEditor.models.Response;

public interface TextResponseHandler {
    public Response buildApiResponse(Object result, Boolean isSuccess);
}
