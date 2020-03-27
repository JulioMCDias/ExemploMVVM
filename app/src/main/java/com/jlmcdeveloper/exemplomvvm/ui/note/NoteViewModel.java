package com.jlmcdeveloper.exemplomvvm.ui.note;

import androidx.databinding.ObservableField;

import com.jlmcdeveloper.exemplomvvm.R;
import com.jlmcdeveloper.exemplomvvm.data.DataManager;
import com.jlmcdeveloper.exemplomvvm.data.model.api.NoteResponse;
import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;
import com.jlmcdeveloper.exemplomvvm.ui.base.BaseViewModel;
import com.jlmcdeveloper.exemplomvvm.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class NoteViewModel extends BaseViewModel<NoteNavigator> {

    private Note note;
    private ObservableField<String> title;
    private ObservableField<String> description;

    public NoteViewModel(DataManager dataManager) {
        super(dataManager);
        title = new ObservableField<>();
        description = new ObservableField<>();
    }

    void setIdNote(Long id){
        if(!id.equals(Constants.ID_NEW_NOTE)){
            for (Note n : getDataManager().getNotes()) {
                if(n.getNoteID().equals(id)) {
                    setNoteView(n);
                    return;
                }
            }
        }
    }


    private void setNoteView(Note note){
        this.note = note;
        title.set(note.getTitle());
        description.set(note.getDescription());
    }


    void save() {
        if(note == null)
            createNote();
        else
            updateNote();

        getNavigator().closeActivity();
    }

    private void updateNote(){
        note.setTitle(getNavigator().getNoteTitle());
        note.setDescription(getNavigator().getNoteDescription());
        getCompositeDisposable().add(getDataManager()
                .updateNoteRemote(note)
                .map(Response::body)
                .map(NoteResponse::getNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((note1, throwable) -> {
                    if(note1 == null || note1.getNoteID() == -1)
                        getNavigator().handleError(R.string.error_in_update);
                }));

        getCompositeDisposable().add(getDataManager()
                .updateNoteLocal(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    private void createNote(){
        note = new Note();
        note.setTitle(getNavigator().getNoteTitle());
        note.setDescription(getNavigator().getNoteDescription());
        note.setUserID(getDataManager().getUser().getUserId());

        // remote
        note.setUserID(getDataManager().getUser().getUserId());
        getCompositeDisposable().add(getDataManager()
                .createNoteRemote(note)
                .map(Response::body)
                .map(NoteResponse::getNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((note1, throwable) -> {
                    if(note1 == null || note1.getNoteID() == -1)
                        getNavigator().handleError(R.string.error_in_save_remote);
                }));

        // local
        getCompositeDisposable().add(getDataManager()
                .createNoteLocal(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    void delete() {
        if(note != null) {
            getCompositeDisposable().add(getDataManager()
                    .deleteNoteRemote(note)
                    .map(Response::body)
                    .map(NoteResponse::getNote)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((note1, throwable) -> {
                        if(note1 == null || note1.getNoteID() == -1)
                            getNavigator().handleError(R.string.error_in_inscribe);
                    }));

            getCompositeDisposable().add(getDataManager()   //local
                    .deleteNoteLocal(note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }

        getNavigator().closeActivity();
    }



    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getDescription() {
        return description;
    }
}
