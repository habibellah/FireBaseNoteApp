package habibellah.ayata.firenote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import habibellah.ayata.firenote.databinding.FragmentNotesListBinding;


public class NotesList extends Fragment {

    FragmentNotesListBinding binding;
    ArrayList<Note> mList = null;
    FireBaseOperation fireBaseOperation = FireBaseOperation.getInstance();
    ShowDialogueAdd showDialogueAdd = ShowDialogueAdd.getInstance();
    ShowDialogueDeleteUpdate showDialogueDeleteUpdate = ShowDialogueDeleteUpdate.getInstance(getContext());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = FragmentNotesListBinding.inflate(getLayoutInflater(),container,false);

        mList = new ArrayList<>();

        binding.addNote.setOnClickListener(view -> showDialogueAdd.showDialogue(getActivity()));
           /*here the adapter of filling the information who take from thee fillLessonInfo function as an array list
        and implement a listener function take the position (number of lesson )
        and navigate to question page to get from date base the right questions  */
        NoteAdapter notesAdapter = new NoteAdapter(getContext(), mList, note -> showDialogueDeleteUpdate.showDialogue(note,getActivity()), this::navToAllNoteDescription);
        //here properties of the recycler view
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(lm);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(notesAdapter);

        return binding.getRoot();
    }

    //this function to navigate to the AllNoteDescription activity
    private void navToAllNoteDescription(Note note)
    {
        Navigation.findNavController(requireView()).navigate(R.id.action_notesList_to_noteDescription,putNoteInfo(note));
    }

    private Bundle putNoteInfo(Note note)
    {
        Bundle bundle = new Bundle();
        bundle.putString("title",note.getTitle());
        bundle.putString("note",note.getNote());
        return bundle;
    }

    @Override
    public void onStart() {
        super.onStart();

        //gat data from firebase
        fireBaseOperation.readFromFireBase(mList);
    }


}