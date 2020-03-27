package com.jlmcdeveloper.exemplomvvm.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jlmcdeveloper.exemplomvvm.data.model.db.Note;
import com.jlmcdeveloper.exemplomvvm.databinding.CardNoteBinding;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHole> {

    private List<Note> notes;
    private Listener listener;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteHole onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardNoteBinding cardNoteBinding = CardNoteBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new NoteHole(cardNoteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHole holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }


    public void updateItems(List<Note> listNote) {
        notes.clear();
        this.notes.addAll(listNote);
        notifyDataSetChanged();
    }



    public interface Listener{
        void onClickNote(Long id);
    }


    //----------------- ViewHolder ---------------------
    class NoteHole extends RecyclerView.ViewHolder implements Listener{

        private CardNoteBinding cardNoteBinding;
        private MainItemViewModel itemViewModel;

        NoteHole(CardNoteBinding cardNoteBinding){
            super(cardNoteBinding.getRoot());
            this.cardNoteBinding = cardNoteBinding;
        }

        void onBind(int position) {
            itemViewModel = new MainItemViewModel(notes.get(position), this);
            cardNoteBinding.setViewModel(itemViewModel);
        }

        @Override
        public void onClickNote(Long id) {
            listener.onClickNote(id);
        }
    }
}
