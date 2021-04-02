package com.example.mealsnap_prototype_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mealsnap_prototype_2.fragments.ExploreFragment;
import com.example.mealsnap_prototype_2.fragments.GalleryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 69;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView btmHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmHome = findViewById(R.id.btmHome);

        btmHome.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_gallery:
                    Toast.makeText(MainActivity.this, "Gallery!", Toast.LENGTH_SHORT).show();
                    fragment = new GalleryFragment();
                    break;
                case R.id.action_explore:
                    Toast.makeText(MainActivity.this, "Explore?", Toast.LENGTH_SHORT).show();
                    fragment = new ExploreFragment();
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Works, should be gallery", Toast.LENGTH_SHORT).show();
                    fragment = new GalleryFragment();
                    break;
            }
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            return true;
        });

        btmHome.setSelectedItemId(R.id.action_gallery);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.user_compose){
            Toast.makeText(MainActivity.this, "LET'S COMPOSE!", Toast.LENGTH_SHORT).show();
            goToComposerActivity();
        }
        if(item.getItemId() == R.id.user_profile){
            Toast.makeText(MainActivity.this, "VIEW PROFILE!", Toast.LENGTH_LONG).show();
            goToProfileActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToComposerActivity() {
        Intent i = new Intent(this, ComposerActivity.class);
        startActivity(i);
    }

    private void goToProfileActivity() {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
}