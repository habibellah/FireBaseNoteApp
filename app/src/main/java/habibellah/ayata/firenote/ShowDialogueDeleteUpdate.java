package habibellah.ayata.firenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
//in this class we can delete or update a note
public class ShowDialogueDeleteUpdate {
    @SuppressLint("StaticFieldLeak")
    private static ShowDialogueDeleteUpdate showDialogueDeleteUpdate;
    Context contexts;
    FireBaseOperation fireBaseOperation = FireBaseOperation.getInstance();
    private ShowDialogueDeleteUpdate(Context context)
    {
        this.contexts = context;
    }
    public synchronized static ShowDialogueDeleteUpdate getInstance(Context context)
    {
        if(showDialogueDeleteUpdate == null)
        {
            showDialogueDeleteUpdate = new ShowDialogueDeleteUpdate(context);
        }

        return showDialogueDeleteUpdate;
    }

    //this function to show dialogue of update or delete a note
    public void showDialogue(Note notePosition,Context context)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view1 = inflater.inflate( R.layout.update_delete_dialogue, null );
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        EditText title = view1.findViewById(R.id.title_note);
        EditText note = view1.findViewById(R.id.note);
       Button delete = view1.findViewById(R.id.delete);
       Button update = view1.findViewById(R.id.update);
       viewsDialogueOperation(delete,update,title,note,alertDialog,notePosition);
    }

    //this function make the operation of every view in dialogue from button and edit text
    private void viewsDialogueOperation(Button delete,Button update
            ,EditText title,EditText note,AlertDialog alertDialog,Note notePosition)
    {
        title.setText(notePosition.getTitle());
        note.setText(notePosition.getNote());
 delete.setOnClickListener(view -> {
     fireBaseOperation.deleteNote(notePosition);
     alertDialog.dismiss();
 });

 update.setOnClickListener(view -> {
     Note afterUpdate = new Note(notePosition.getId(),title.getText().toString(),note.getText().toString());
     fireBaseOperation.updateNote(afterUpdate);
     alertDialog.dismiss();
 });
    }
}
