package com.amjed.TextEditor.services.business.implimentation;

import com.amjed.TextEditor.constants.Constants;
import com.amjed.TextEditor.models.Response;
import com.amjed.TextEditor.services.business.TextResponseHandler;
import org.springframework.stereotype.Service;

@Service
public class TextResponseHandlerImpl implements TextResponseHandler {

    @Override
    public Response buildApiResponse(Object result, Boolean isSuccess) {
        if (isSuccess) {
            return new Response(Constants.RESPONSE_CODE.SUCCESS, Constants.RESPONSE_MESSAGE.SUCCESS, result);
        }
        return new Response(Constants.RESPONSE_CODE.FAILED, Constants.RESPONSE_MESSAGE.FAILED, result);
    }
}
