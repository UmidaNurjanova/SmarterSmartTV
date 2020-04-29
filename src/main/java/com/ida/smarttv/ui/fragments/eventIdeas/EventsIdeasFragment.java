package com.ida.smarttv.ui.fragments.eventIdeas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ida.smarttv.R;
import com.ida.smarttv.data.remote.model.EventIdeaResponse;
import com.ida.smarttv.data.remote.model.Idea;
import com.ida.smarttv.ui.adapters.EventIdeaListAdapter;

import org.jetbrains.annotations.NotNull;

public class EventsIdeasFragment extends Fragment {
    private RecyclerView recyclerView;
    EventIdeaListAdapter adapter;
    NavController navController;

    private EventsIdeasViewModel mViewModel;

    public static EventsIdeasFragment newInstance() {
        return new EventsIdeasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.events_ideas_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_event_ideas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new EventIdeaListAdapter();
        recyclerView.setAdapter(adapter);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventsIdeasViewModel.class);

        mViewModel.showAllIdeas().observe(getViewLifecycleOwner(), new Observer<EventIdeaResponse>() {
            @Override
            public void onChanged(EventIdeaResponse eventIdeaResponse) {
                if (eventIdeaResponse != null) {
                    adapter.setEventList(eventIdeaResponse.getIdeas());
                }

            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    // We are not implementing onMove() in this app.
                    public boolean onMove(@NotNull RecyclerView recyclerView,
                                          @NotNull RecyclerView.ViewHolder viewHolder,
                                          @NotNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    // When the use swipes a word,
                    // delete that word from the database.
                    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                        final int position = viewHolder.getAdapterPosition();
                        Idea event = adapter.getEventIdeaAt(position);
                        Toast.makeText(getContext(), "Deleted event .." + event.getIdea(), Toast.LENGTH_SHORT).show();
                        mViewModel.deleteIdea(event.getId());
                        adapter.removeItem(position);

                    }
                });
        // Attach the item touch helper to the recycler view.
        helper.attachToRecyclerView(recyclerView);

    }

}
