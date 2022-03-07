package habibellah.ayata.firenote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import habibellah.ayata.firenote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater(),null,false);
        setContentView(binding.getRoot());
binding.addNote.setOnClickListener(view -> {
    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    View view1 =   getLayoutInflater().inflate(R.layout.alert_note,null);
      builder.setView(view1);
  AlertDialog alertDialog = builder.create();
  alertDialog.show();
});


    }
}