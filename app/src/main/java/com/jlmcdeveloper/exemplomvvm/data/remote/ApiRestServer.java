package com.jlmcdeveloper.exemplomvvm.data.remote;


import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteResponse;


import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiRestServer {

    @POST("peoples/")
    Single<Response<LoginResponse>> setLogin(@Query("method") String method, @Body LoginRequest loginRequest);

    @POST("notes/")
    Single<Response<NoteAllResponse>> getNotes(@Query("method") String method, @Body NoteAllRequest noteAllRequest);

    @POST("notes/")
    Single<Response<NoteResponse>> setNote(@Query("method") String method, @Body NoteRequest noteRequest);

}
