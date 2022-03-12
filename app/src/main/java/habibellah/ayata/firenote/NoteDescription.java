package habibellah.ayata.firenote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import habibellah.ayata.firenote.databinding.FragmentNoteDescriptionBinding;


public class NoteDescription extends Fragment {
FragmentNoteDescriptionBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteDescriptionBinding.inflate(getLayoutInflater(),container,false);
        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String note = bundle.getString("note");
        TextView titleView = binding.title3;
        TextView noteView = binding.note3;
        titleView.setText(title);
        noteView.setText(note);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}