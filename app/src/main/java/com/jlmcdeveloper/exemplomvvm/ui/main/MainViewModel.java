package com.jlmcdeveloper.exemplomvvm.ui.main;

import androidx.lifecycle.MutableLiveData;

import com.jlmcdeveloper.exemplomvvm.data.DataManager;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteAllResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseViewModel;
import com.jlmcdeveloper.exemplomvvm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel<MainNavigator> {


    private MutableLiveData<List<Note>> notesLiveData;

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
        notesLiveData = new MutableLiveData<>();
        fetchNotes();
    }


    MutableLiveData<List<Note>> getNotesLiveData() {
        return notesLiveData;
    }


    public void setNewNote(){
        getNavigator().openNote(Constants.ID_NEW_NOTE);
    }

    void fetchNotes(){
        setIsLoading(true);
        List<Note> notes = new ArrayList<>();
        getCompositeDisposable().add(Flowable.concat(
                getDataManager().getAllNotesLocal()         //local database
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(),

                getDataManager().getAllNotesRemote()        //remote database
                        .map(Response::body)
                        .map(NoteAllResponse::getNotes)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable()

        ).observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes1 -> {
                    for (Note note : notes1) {
                        if(!notes.contains(note))
                            notes.add(note);
                    }
                    notesLiveData.setValue(notes);
                    getDataManager().setNotes(notes);
                }, throwable-> {
                    getNavigator().handleError(throwable);
                    setIsLoading(false);
                }, () -> setIsLoading(false)));
    }
}
