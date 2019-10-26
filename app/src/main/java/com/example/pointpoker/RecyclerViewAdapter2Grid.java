package com.example.pointpoker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pointpoker.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.Vector;

public class RecyclerViewAdapter2Grid extends RecyclerView.Adapter<RecyclerViewAdapter2Grid.ViewHolder> {
    // for debugging
    private static final String TAG = "RecyclerViewAdapterGrid";

    private Vector<String> names;
    private Context context;

    private ClickListener listener;

    public RecyclerViewAdapter2Grid(Context context, Vector<String> names, ClickListener listener) {
        this.names = names;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_griditem, parent, false);
        return new ViewHolder(layout, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (names.get(position).equals("coffee")) {
            Log.d(TAG, "Set Coffee icon");
            holder.imageButton.setImageResource(R.drawable.icons_coffee);
        } else {
            Log.d(TAG, "Set number text icon: " + names.get(position));
            holder.textView.setText(names.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageButton imageButton;
        TextView textView;
        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

            listenerRef = new WeakReference<>(listener);

            imageButton = itemView.findViewById(R.id.gridBtn);
            textView = itemView.findViewById(R.id.gridTxt);

            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // must override later
            Utils.makeToast(v.getContext(), "Button clicked");

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
