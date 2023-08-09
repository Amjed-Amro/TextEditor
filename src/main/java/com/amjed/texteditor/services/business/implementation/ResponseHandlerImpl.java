package com.amjed.texteditor.services.business.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.Dto.Response;
import com.amjed.texteditor.services.business.ResponseHandler;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandlerImpl implements ResponseHandler {

    @Override
    public Response buildApiResponse(Object result, Boolean isSuccess) {
        if (isSuccess) {
            return new Response(Constants.RESPONSE_CODE.SUCCESS, Constants.RESPONSE_MESSAGE.SUCCESS, result);
        }
        return new Response(Constants.RESPONSE_CODE.FAILED, Constants.RESPONSE_MESSAGE.FAILED, result);
    }
}
