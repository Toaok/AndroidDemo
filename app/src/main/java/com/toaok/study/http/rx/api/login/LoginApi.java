package com.toaok.study.http.rx.api.login;

import com.toaok.study.http.rx.RequestManager;
import com.toaok.study.http.rx.api.login.request.LoginRequest;
import com.toaok.study.http.rx.api.login.request.RegisterRequest;

import io.reactivex.Observable;

/**
 * @author Toaok
 * @version 1.0  2018/8/20.
 */
public class LoginApi {
    interface Api {

        Observable login(LoginRequest loginRequest);

        Observable register(RegisterRequest registerRequest);
    }

    private Api api;

    public LoginApi (){
        api= RequestManager.getRequest(Api.class);
    }

}

