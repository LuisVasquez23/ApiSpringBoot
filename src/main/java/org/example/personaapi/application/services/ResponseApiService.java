package org.example.personaapi.application.services;

import org.example.personaapi.domain.models.BaseResponseModel;

public class ResponseApiService {
    public static BaseResponseModel response(int statusCode, Object data, String message) {
        boolean success = statusCode >= 200 && statusCode < 300;
        return new BaseResponseModel(statusCode, success, message, data);
    }
}
