package com.amjed.texteditor.services.business;

import com.amjed.texteditor.models.Dto.Response;

public interface ResponseHandler {
    public Response buildApiResponse(Object result, Boolean isSuccess);
}
