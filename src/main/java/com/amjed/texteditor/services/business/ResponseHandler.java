package com.amjed.texteditor.services.business;

import com.amjed.texteditor.models.Dto.Response;

public interface ResponseHandler {
    Response buildApiResponse(Object result, Boolean isSuccess);
}
