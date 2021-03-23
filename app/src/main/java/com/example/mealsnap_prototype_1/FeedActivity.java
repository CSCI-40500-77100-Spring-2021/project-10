package com.example.mealsnap_prototype_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private static final String TAG = "FeedActivity";
    public final int REQUEST_CODE = 20;

    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;

    ////PARSE STUFF TO BE REMOVED
    private ParsePostAdapter adapter;
    private List<ParsePost> allPosts;
    ////PARSE STUFF TO BE REMOVED

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "getting new data");
                queryPosts();   ////PARSE STUFF TO BE REMOVED
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        ////PARSE STUFF TO BE REMOVED
        allPosts = new ArrayList<>();
        adapter = new ParsePostAdapter(this, allPosts);
        ////PARSE STUFF TO BE REMOVED

        //Dummy Data
        ArrayList<Post> arrayList = new ArrayList<>();
        arrayList.add(new Post(R.drawable.img1, "Biryani","Biryani is a mixed rice dish originating among the Muslims of the Indian subcontinent. It is made with Indian spices, rice, and meat, and sometimes, in addition, eggs and/or vegetables such as potatoes in certain regional varieties."));
        arrayList.add(new Post(R.drawable.img2, "Bulgogi","Bulgogi, literally \"fire meat\", is a gui made of thin, marinated slices of beef or pork grilled on a barbecue or on a stove-top griddle. It is also often stir-fried in a pan in home cooking. Sirloin, rib eye or brisket are frequently used cuts of beef for the dish."));
        arrayList.add(new Post(R.drawable.img3, "Ramen","Ramen is a Japanese noodle soup. It consists of Chinese wheat noodles served in a meat or fish-based broth, often flavored with soy sauce or miso, and uses toppings such as sliced pork, nori, menma, and scallions."));

        //Adapter
        PostAdapter postAdapter = new PostAdapter(this, R.layout.post_view, arrayList);
        //recyclerView.setAdapter(postAdapter);

        ////PARSE STUFF TO BE REMOVED
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        queryPosts();
        ////PARSE STUFF TO BE REMOVED
    }

    ////PARSE STUFF TO BE REMOVED
    private void queryPosts() {
        ParseQuery<ParsePost> query = ParseQuery.getQuery(ParsePost.class);
        query.include(ParsePost.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(ParsePost.KEY_CREATED_AT);

        query.findInBackground(new FindCallback<ParsePost>() {
            @Override
            public void done(List<ParsePost> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue posting");
                    return;
                }
                for(ParsePost post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                adapter.clear();
                adapter.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }
    ////PARSE STUFF TO BE REMOVED

    //compose button linked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu, adds items to action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //when compose post is clicked transition to compose page
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.compose){
            Toast.makeText(FeedActivity.this, "Moving to compose!", Toast.LENGTH_SHORT).show();
            //here compose icon has been tapped
            //navigate to the compose activity
            Intent intent = new Intent(this, PostActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}