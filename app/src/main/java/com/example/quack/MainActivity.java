package com.example.quack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.quack.Fragment.AddFragment;
import com.example.quack.Fragment.AddPostFragment;
import com.example.quack.Fragment.HomeFragment;
import com.example.quack.Fragment.NotificationFragment;
import com.example.quack.Fragment.ProfileFragment;
import com.example.quack.Fragment.SearchFragment;
import com.example.quack.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        MainActivity.this.setTitle("My Profile");
        FirebaseAuth auth;
        binding.toolbar.setVisibility(View.GONE);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                    switch(menuItem.getItemId()){
                        case R.id.nav_add:
                            binding.toolbar.setVisibility(View.GONE);
                            selectedFragment = new AddPostFragment();
                            break;
                        case R.id.nav_home:
                            binding.toolbar.setVisibility(View.GONE);
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            binding.toolbar.setVisibility(View.GONE);
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_notification:
                            binding.toolbar.setVisibility(View.GONE);
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.nav_profile:
                            //SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            //editor.putString("profield", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            //editor.apply();
                            binding.toolbar.setVisibility(View.VISIBLE);
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    if(selectedFragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    }
                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}