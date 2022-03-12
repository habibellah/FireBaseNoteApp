package habibellah.ayata.firenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FireBaseOperation {
   @SuppressLint("StaticFieldLeak")
   private static FireBaseOperation fireBaseOperation;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Notes");
    private FireBaseOperation()
    {
    }
    public static synchronized FireBaseOperation getInstance()
    {
        if(fireBaseOperation == null)
        {
            fireBaseOperation = new FireBaseOperation();
        }
        return fireBaseOperation;
    }

    //this function to store the title of note and his description in firebase
    public void addToFireBase(EditText title, EditText note,Context context)
    {
       if(!checkIfEmpty(title,note))
       {
           String id = mRef.push().getKey();

           Note mNote = new Note(id,title.getText().toString(),note.getText().toString());
           if(id != null)
               mRef.child(id).setValue(mNote);
       }
       else {

           Toast.makeText(context, "empty notes", Toast.LENGTH_SHORT).show();
       }
    }

    //this function to check if title and note is empty or note
    private boolean checkIfEmpty(EditText title, EditText note)
    {
        return isEmpty1(title)|| isEmpty1(note);
    }

    //this function check if edit text is empty
    private boolean isEmpty1(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    //this function to read from firebase or get data from it
     public void readFromFireBase(ArrayList<Note> mList)
     {
         mRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 mList.clear();
                 for(DataSnapshot noteSnapShot: snapshot.getChildren())
                 {
                     //noinspection CatchMayIgnoreException
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
     }

     //this function to delete note from firebase
     public void deleteNote(Note note)
     {
         mRef.child(note.getId()).removeValue();
     }

     //this function to update note from firebase
    public void updateNote(Note newNote)
    {
        mRef.child(newNote.getId()).setValue(newNote);
    }


}
