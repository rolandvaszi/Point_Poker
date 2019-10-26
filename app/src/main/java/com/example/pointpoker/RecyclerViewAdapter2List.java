package com.example.pointpoker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter2List extends RecyclerView.Adapter<RecyclerViewAdapter2List.ViewHolder> {
    // for debugging
    private static final String TAG = "RecyclerViewAdapterList";

    private ArrayList<String> names;
    private ArrayList<String> numbers;
    private Context context;

    public RecyclerViewAdapter2List(Context context, ArrayList<String> names, ArrayList<String> numbers) {
        this.names = names;
        this.numbers = numbers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder is called");

        holder.nameText.setText(this.names.get(position));
        holder.numberText.setText(this.numbers.get(position));
    }

    @Override
    public int getItemCount() {
        return this.names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView numberText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameText);
            numberText = itemView.findViewById(R.id.numberText);
        }
    }
}
