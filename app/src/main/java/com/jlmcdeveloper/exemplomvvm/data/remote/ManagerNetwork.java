package com.jlmcdeveloper.exemplomvvm.data.remote;

import com.jlmcdeveloper.exemplomvvm.data.model.LoginUser;
import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public interface ManagerNetwork {

    Single<Response<LoginResponse>> setLoginRemote(LoginUser user);

    Single<Response<LoginResponse>> createLoginRemote(LoginUser user);

    Single<Response<NoteAllResponse>> getAllNotesRemote();

    Single<Response<NoteResponse>> createNoteRemote(Note note);

    Single<Response<NoteResponse>> updateNoteRemote(Note note);

    Single<Response<NoteResponse>> deleteNoteRemote(Note note);
}
