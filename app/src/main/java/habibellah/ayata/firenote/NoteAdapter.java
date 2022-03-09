package habibellah.ayata.firenote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.HolderView> {
   RecyclerClickListener listener;
    ArrayList<Note> mNotes;
   // setOnRecyclerListener onRecyclerListener;
    Context context;

    //constructor
    public NoteAdapter(Context context,ArrayList<Note> notesP,RecyclerClickListener listener) {
        this.mNotes = notesP;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v=   LayoutInflater.from(parent.getContext()).inflate(R.layout.costum_adapter,null,false);
        return new HolderView(v);
    }

    //this function to bind other blocks one you scroll and do not inflate every time new one
    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        holder.bind(position);
    }

    //return size of the array to adapting
    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    //this function to use features of recycler view is do not inflate new blocks just get information and put it in old blocks when scroll
    class HolderView extends RecyclerView.ViewHolder {
        TextView titleNote;
        TextView  note;

        public HolderView(@NonNull View itemView) {
            super(itemView);
            titleNote = itemView.findViewById(R.id.title_adapter);
            note = itemView.findViewById(R.id.note_adapter);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onRecyclerClick(getAbsoluteAdapterPosition());
                    return false;
                }
            });
        }

        //this function is bind information when you scroll we use it in on bind view holder
        void bind(int position)
        {
            Note fill = mNotes.get(position);
            titleNote.setText(fill.getTitle());
            note.setText(fill.getNote());

        }


    }
}
