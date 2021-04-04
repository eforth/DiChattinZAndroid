package com.vectorjm.dichattinzandroid;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Observer<List<ChatBout>> {

    private final String TAG = "HomeFragment";
    private List<ChatBout> list = new ArrayList<>();
    private HomeAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.feed_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new HomeAdapter(list);
        recyclerView.setAdapter(adapter);

        // viewModel will observe new chatBouts from FirebaseFirestore
        // and update the adapter via the onChanged method
        viewModel.getChatBouts().observe(getViewLifecycleOwner(), this);
    }

    @Override
    public void onChanged(List<ChatBout> chatBouts) {
        for (ChatBout chatBout : chatBouts) {
            Log.i(TAG, chatBout.toString());
            if (!list.contains(chatBout)) {
                list.add(chatBout);
                adapter.notifyDataSetChanged();
            }
        }
    }
}