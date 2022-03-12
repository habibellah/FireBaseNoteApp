package habibellah.ayata.firenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
//this class to show dialogue to add new note
public class ShowDialogueAdd {
    @SuppressLint("StaticFieldLeak")
    private static ShowDialogueAdd showDialogueAdd;
    FireBaseOperation fireBaseOperation = FireBaseOperation.getInstance();
  private ShowDialogueAdd()
  {
  }
  public  synchronized static ShowDialogueAdd getInstance()
  {
      if(showDialogueAdd == null)
      {
          showDialogueAdd = new ShowDialogueAdd();
      }

          return showDialogueAdd;
  }

  //this function make a dialogue
    public void showDialogue(Context context)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view1 = inflater.inflate( R.layout.alert_note, null );
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button add = view1.findViewById(R.id.add_note);
        EditText title = view1.findViewById(R.id.title_note);
        EditText note = view1.findViewById(R.id.note);
        viewsDialogueOperation(add,title,note,alertDialog,context);

    }
    //this function make the operation of every view in dialogue from button and edit text
    private void viewsDialogueOperation(Button add,EditText title,EditText note,AlertDialog alertDialog,Context context)
    {
        add.setOnClickListener(view -> {
            fireBaseOperation.addToFireBase(title,note,context);
            alertDialog.dismiss();
        });

    }

}
