package com.jlmcdeveloper.exemplomvvm.data.local;

import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;
import com.jlmcdeveloper.exemplomvvm.data.model.db.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface ManagerLocal {

    Single<User> setLoginLocal(User user);

    Completable createLoginLocal(User user);

    Maybe<List<User>> getAllUserLocal();

    Single<Integer> deleteUserLocal(User user);

    Maybe<List<Note>> getAllNotesLocal();

    Maybe<List<Note>> getAllNotesLocal(User user);

    Completable createNoteLocal(Note note);

    Completable createAllNoteLocal(List<Note> notes);

    Single<Integer> updateNoteLocal(Note note);

    Single<Integer> deleteNoteLocal(Note note);
}
