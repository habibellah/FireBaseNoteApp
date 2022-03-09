package habibellah.ayata.firenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class ShowDialogueAdd {
    @SuppressLint("StaticFieldLeak")
    private static ShowDialogueAdd showDialogueAdd;
    Context contexts;
    FireBaseOperation fireBaseOperation = FireBaseOperation.getInstance(null);
  private ShowDialogueAdd(Context context)
  {
      this.contexts = context;
  }
  public  synchronized static ShowDialogueAdd getInstance(Context context)
  {
      if(showDialogueAdd == null)
      {
          showDialogueAdd = new ShowDialogueAdd(context);
      }

          return showDialogueAdd;
  }
    public void showDialogue()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(contexts);
        LayoutInflater inflater = (LayoutInflater) contexts.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view1 = inflater.inflate( R.layout.alert_note, null );
        builder.setView(view1);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button add = view1.findViewById(R.id.add_note);
        EditText title = view1.findViewById(R.id.title_note);
        EditText note = view1.findViewById(R.id.note);


        add.setOnClickListener(view -> {
            fireBaseOperation.addToFireBase(title,note);
           alertDialog.dismiss();
       });

    }

}
