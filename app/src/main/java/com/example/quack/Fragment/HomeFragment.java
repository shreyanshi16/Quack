package com.example.quack.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.quack.Adapter.PostAdapter;
import com.example.quack.Adapter.StoryAdapter;
import com.example.quack.Model.Post;
import com.example.quack.Model.StoryModel;
import com.example.quack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView storyRv, dashboardRV;
    ImageView addStory;
    ArrayList<StoryModel> list;
ArrayList<Post> postList;
FirebaseDatabase database;
FirebaseAuth auth;

    public HomeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        storyRv = view.findViewById(R.id.storyRV);
        list = new ArrayList<>();
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"shreya"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"viral"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"rid"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"sunil"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"shreya"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"shreya"));
        list.add(new StoryModel(R.drawable.profile,R.drawable.profile,"shreya"));

        StoryAdapter adapter = new StoryAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        storyRv.setLayoutManager(linearLayoutManager);
        storyRv.setNestedScrollingEnabled(false);
        storyRv.setAdapter(adapter);

        dashboardRV = view.findViewById(R.id.dashboardRv);
        postList = new ArrayList<>();
        PostAdapter postAdapter = new PostAdapter(postList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        dashboardRV.setLayoutManager(layoutManager);
        dashboardRV.addItemDecoration(new DividerItemDecoration(dashboardRV.getContext(), DividerItemDecoration.VERTICAL));
        dashboardRV.setNestedScrollingEnabled(false);
        dashboardRV.setAdapter(postAdapter);

        database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Post post = dataSnapshot.getValue(Post.class);
                    post.setPostId(dataSnapshot.getKey());
                    postList.add(post);
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addStory = view.findViewById(R.id.addStory);
        addStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}