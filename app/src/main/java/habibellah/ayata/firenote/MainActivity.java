package habibellah.ayata.firenote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import habibellah.ayata.firenote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding ;
    DatabaseReference mRef;
    ArrayList<Note> mList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Notes");
binding.addNote.setOnClickListener(view -> showDialogue());
mList = new ArrayList<>();



    }

    @Override
    protected void onStart() {
        super.onStart();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for(DataSnapshot noteSnapShot: snapshot.getChildren())
                {

                    try {
                        Note note =noteSnapShot.getValue(Note.class);
                        mList.add(0,note);

                    } catch (Exception e) {
                        e.getMessage();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
         /*here the adapter of filling the information who take from thee fillLessonInfo function as an array list
        and implement a listener function take the position (number of lesson )
        and navigate to question page to get from date base the right questions  */
        NoteAdapter notesAdapter = new NoteAdapter(this,mList);

        //here properties of the recycler view
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(lm);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(notesAdapter);

    }

    private void showDialogue()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view1 =   getLayoutInflater().inflate(R.layout.alert_note,null);
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button add = view1.findViewById(R.id.add_note);
        EditText title = view1.findViewById(R.id.title_note);
        EditText note = view1.findViewById(R.id.note);


        add.setOnClickListener(view -> {
           addToFireBase(title,note);
            alertDialog.dismiss();
        });

    }
    private void addToFireBase(EditText title,EditText note)
    {
        String id = mRef.push().getKey();
        if(isEmpty1(title)|| isEmpty1(note))
        {
            Toast.makeText(MainActivity.this, "empty notes", Toast.LENGTH_SHORT).show();

        }
        else
        {

            Note mNote = new Note(id,title.getText().toString(),note.getText().toString());
            if(id != null)
            mRef.child(id).setValue(mNote);
        }
    }
    private boolean isEmpty1(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}