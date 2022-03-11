package habibellah.ayata.firenote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import habibellah.ayata.firenote.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
ActivityLoginBinding binding ;
FireBaseAuthenticationOpera authenticationOper = FireBaseAuthenticationOpera.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progress.setVisibility(View.VISIBLE);
                authenticationOper.createNewEmail(binding.email,binding.pass,binding.progress);
            }
        });

    }
}