package habibellah.ayata.firenote;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthenticationOpera {
    private static FireBaseAuthenticationOpera authenticationOpera;
    Context context;

    private FireBaseAuthenticationOpera(Context context)
    {
        this.context = context;
    }

    public static FireBaseAuthenticationOpera getInstance(Context context)
    {
        if(authenticationOpera == null)
        {
            authenticationOpera = new FireBaseAuthenticationOpera(context);
        }
        return authenticationOpera;
    }

    public void createNewEmail(EditText email, EditText password, ProgressBar progressBar)
    {

        if(!checkIfEmpty(email,password))
        {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(context, "operation not successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }


        else {
            Toast.makeText(context, "is empty", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
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

}
