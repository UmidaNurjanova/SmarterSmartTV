package com.ida.smarttv.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ida.smarttv.R;
import com.ida.smarttv.data.remote.model.Idea;

import java.util.ArrayList;
import java.util.List;

public class EventIdeaListAdapter extends RecyclerView.Adapter<EventIdeaListAdapter.EventListViewHolder> {
    List<Idea> ideaList = new ArrayList<>();


    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_eventideas, parent, false);
        return new EventListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        if (ideaList.get(position) != null) {
            holder.mEventIdea.setText(ideaList.get(position).getIdea());

        }

    }

    @Override
    public int getItemCount() {
        return ideaList.size();
    }

    public void setEventList(List<Idea> ideaLists) {
        this.ideaList = ideaLists;
        notifyDataSetChanged();
    }

    public Idea getEventIdeaAt(int position) {
        return ideaList.get(position);
    }

    public void removeItem(int position) {
        ideaList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }


    public static class EventListViewHolder extends RecyclerView.ViewHolder {
        TextView mEventIdea;


        public EventListViewHolder(@NonNull View itemView) {
            super(itemView);
            mEventIdea = itemView.findViewById(R.id.tv_event_idea);


        }
    }
}
