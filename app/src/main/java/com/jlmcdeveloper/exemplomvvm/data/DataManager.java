package com.jlmcdeveloper.exemplomvvm.data;

import com.jlmcdeveloper.exemplomvvm.data.local.ManagerLocal;
import com.jlmcdeveloper.exemplomvvm.data.model.LoggedInUser;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;
import com.jlmcdeveloper.exemplomvvm.data.remote.ManagerNetwork;

import java.util.List;

public interface DataManager extends ManagerNetwork, ManagerLocal {

    void setLoggedInUser(LoggedInUser user);

    LoggedInUser getUser();

    void setToken(String token);

    List<Note> getNotes();

    void setNotes(List<Note> notes);
}
