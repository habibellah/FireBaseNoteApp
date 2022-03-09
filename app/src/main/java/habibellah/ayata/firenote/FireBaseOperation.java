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
    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Notes");
    private FireBaseOperation(Context context)
    {
        this.context = context;
    }
    public static synchronized FireBaseOperation getInstance(Context context)
    {
        if(fireBaseOperation == null)
        {
            fireBaseOperation = new FireBaseOperation(context);
        }
        return fireBaseOperation;
    }

    public void addToFireBase(EditText title, EditText note)
    {
        String id = mRef.push().getKey();
        if(isEmpty1(title)|| isEmpty1(note))
        {
            Toast.makeText(context, "empty notes", Toast.LENGTH_SHORT).show();

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
}
