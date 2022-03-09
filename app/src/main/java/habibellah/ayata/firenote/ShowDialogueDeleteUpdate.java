package habibellah.ayata.firenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class ShowDialogueDeleteUpdate {
    @SuppressLint("StaticFieldLeak")
    private static ShowDialogueDeleteUpdate showDialogueDeleteUpdate;
    Context contexts;
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
    public void showDialogue()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(contexts);
        LayoutInflater inflater = (LayoutInflater) contexts.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view1 = inflater.inflate( R.layout.update_delete_dialogue, null );
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        EditText title = view1.findViewById(R.id.title_note);
        EditText note = view1.findViewById(R.id.note);
       Button delete = view1.findViewById(R.id.delete);
       Button update = view1.findViewById(R.id.update);


    }
}
