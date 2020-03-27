package com.jlmcdeveloper.exemplomvvm.data.remote;

import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Response;

@Singleton
public class ApiManager {
    private ApiRestServer apiRestServer;

    @Inject
    public ApiManager(ApiRestServer apiRestServer) {
        this.apiRestServer = apiRestServer;
    }



    public Single<Response<LoginResponse>> setLogin(LoginRequest loginRequest) {
        return apiRestServer.setLogin(ApiEndPoint.setLogin, loginRequest);
    }


    public Single<Response<LoginResponse>> createLogin(LoginRequest loginRequest) {
        return apiRestServer.setLogin(ApiEndPoint.createLogin, loginRequest);
    }


    public Single<Response<NoteAllResponse>> getNotes(NoteAllRequest loginResponse) {
        return apiRestServer.getNotes(ApiEndPoint.getAllNotes, loginResponse);
    }


    public Single<Response<NoteResponse>> createNote(NoteRequest noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.createNote, noteRequest);
    }


    public Single<Response<NoteResponse>> updateNote(NoteRequest noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.updateNote, noteRequest);
    }


    public Single<Response<NoteResponse>> deleteNote(NoteRequest noteRequest) {
        return apiRestServer.setNote(ApiEndPoint.deleteNote, noteRequest);
    }
}
