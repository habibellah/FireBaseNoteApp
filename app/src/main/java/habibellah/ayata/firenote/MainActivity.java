package habibellah.ayata.firenote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import java.util.ArrayList;

import habibellah.ayata.firenote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding ;

    ArrayList<Note> mList = null;
    FireBaseOperation fireBaseOperation = FireBaseOperation.getInstance(this);
    ShowDialogueAdd showDialogueAdd = ShowDialogueAdd.getInstance(this);
  ShowDialogueDeleteUpdate showDialogueDeleteUpdate = ShowDialogueDeleteUpdate.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
binding.addNote.setOnClickListener(view -> showDialogueAdd.showDialogue());
mList = new ArrayList<>();
    /*here the adapter of filling the information who take from thee fillLessonInfo function as an array list
        and implement a listener function take the position (number of lesson )
        and navigate to question page to get from date base the right questions  */
        NoteAdapter notesAdapter = new NoteAdapter(this, mList, (position, note) -> showDialogueDeleteUpdate.showDialogue(note), this::navToAllNoteDescription);

        //here properties of the recycler view
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(lm);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(notesAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        //gat data from firebase
    fireBaseOperation.readFromFireBase(mList);


    }

    //this function to navigate to the AllNoteDescription activity
    private void navToAllNoteDescription(Note note)
    {
        Intent intent = new Intent(MainActivity.this,AllNoteDescription.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",note.getTitle());
        bundle.putString("note",note.getNote());
        intent.putExtras(bundle);
        startActivity(intent);
    }



}