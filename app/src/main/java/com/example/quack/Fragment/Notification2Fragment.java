package com.example.quack.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quack.Adapter.NotificationAdapter;
import com.example.quack.Model.NotificationModel;
import com.example.quack.R;

import java.util.ArrayList;

public class Notification2Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationModel> list;

    public Notification2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification2, container, false);
        recyclerView = view.findViewById(R.id.notification2RV);
        list = new ArrayList<>();
        list.add(new NotificationModel(R.drawable.profile1,"<b>Shreyanshi</b> mentioned you in a comment","Just Now"));
        list.add(new NotificationModel(R.drawable.profile1,"<b>Shreyanshi</b> Liked your picture","Just Now"));
        list.add(new NotificationModel(R.drawable.profile1,"<b>Shreyanshi</b> mentioned you in a comment","Just Now"));
        list.add(new NotificationModel(R.drawable.profile1,"<b>Shreyanshi</b> commented on your post","Just Now"));
        list.add(new NotificationModel(R.drawable.profile1,"<b>Shreyanshi</b> mentioned you in a comment","Just Now"));
        NotificationAdapter adapter = new NotificationAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}