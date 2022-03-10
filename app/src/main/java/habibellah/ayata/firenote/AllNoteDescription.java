package habibellah.ayata.firenote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AllNoteDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_note_description);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String note = intent.getStringExtra("note");
        TextView titleView = findViewById(R.id.title3);
        TextView noteView = findViewById(R.id.note3);
        titleView.setText(title);
        noteView.setText(note);
    }
}