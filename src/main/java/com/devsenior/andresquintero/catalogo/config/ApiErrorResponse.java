package com.devsenior.andresquintero.catalogo.config;

import java.util.HashMap;

public interface ApiErrorResponse {

    void setMessage(String message);

    void setErrors(HashMap<String,String> errors);

    void setCode(int value);

}
