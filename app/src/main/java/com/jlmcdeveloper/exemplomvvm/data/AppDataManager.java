package com.jlmcdeveloper.exemplomvvm.data;


import com.jlmcdeveloper.exemplomvvm.data.local.NotesRoomDatabase;
import com.jlmcdeveloper.exemplomvvm.data.model.LoggedInUser;
import com.jlmcdeveloper.exemplomvvm.data.model.LoginUser;
import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.LoginResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteRequest;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;
import com.jlmcdeveloper.exemplomvvm.data.model.db.User;
import com.jlmcdeveloper.exemplomvvm.data.remote.ApiManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Response;

public class AppDataManager implements DataManager{

    private final ApiManager apiManager;
    private NotesRoomDatabase roomDatabase;
    private List<Note> notes;
    private LoggedInUser user;
    private String token;


    @Inject
    AppDataManager(ApiManager apiManager, NotesRoomDatabase notesRoomDatabase){
        this.apiManager = apiManager;
        this.roomDatabase = notesRoomDatabase;
        notes = new ArrayList<>();
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void setLoggedInUser(LoggedInUser user) {
        this.user = user;
    }

    public LoggedInUser getUser() {
        return user;
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    // ------- network --------
    @Override
    public Single<Response<LoginResponse>> setLoginRemote(LoginUser user) {
        return apiManager.setLogin(new LoginRequest(user.getName(), user.getPassword()));
    }

    @Override
    public Single<Response<LoginResponse>> createLoginRemote(LoginUser user) {
        return apiManager.createLogin(new LoginRequest(user.getName(), user.getPassword()));
    }

    @Override
    public Single<Response<NoteAllResponse>> getAllNotesRemote() {
        return apiManager.getNotes(new NoteAllRequest(token, user.getUserId()));
    }

    @Override
    public Single<Response<NoteResponse>> createNoteRemote(Note note) {
        return apiManager.createNote(new NoteRequest(token, note));
    }

    @Override
    public Single<Response<NoteResponse>> updateNoteRemote(Note note) {
        return apiManager.updateNote(new NoteRequest(token, note));
    }

    @Override
    public Single<Response<NoteResponse>> deleteNoteRemote(Note note) {
        return apiManager.deleteNote(new NoteRequest(token, note));
    }


    // --------- Local -----------
    @Override
    public Single<User> setLoginLocal(User user) {
        return roomDatabase.userDao().getUser(user.getName(), user.getPassword());
    }


    @Override
    public Completable createLoginLocal(User user) {
        return roomDatabase.userDao().insertUser(user);
    }

    @Override
    public Maybe<List<User>> getAllUserLocal() {
        return roomDatabase.userDao().getAllUser();
    }

    @Override
    public Single<Integer> deleteUserLocal(User user) {
        return roomDatabase.userDao().deleteUser(user);
    }


    @Override
    public Maybe<List<Note>> getAllNotesLocal() {
        return roomDatabase.noteDao().getAllNotes(user.getUserId());
    }

    @Override
    public Maybe<List<Note>> getAllNotesLocal(User user) {
        return roomDatabase.noteDao().getAllNotes(user.getUserId());
    }


    @Override
    public Completable createNoteLocal(Note note) {
        return roomDatabase.noteDao().insertNote(note);
    }

    @Override
    public Completable createAllNoteLocal(List<Note> notes){
        return roomDatabase.noteDao().insertAllNote(notes);
    }

    @Override
    public Single<Integer> updateNoteLocal(Note note) {
        return roomDatabase.noteDao().updateNote(note);
    }

    @Override
    public Single<Integer> deleteNoteLocal(Note note) {
        return roomDatabase.noteDao().deleteNote(note);
    }
}
