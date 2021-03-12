package com.example.mealsnap_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = findViewById(R.id.listView);

        //Dummy Data
        ArrayList<Post> arrayList = new ArrayList<>();
        arrayList.add(new Post(R.drawable.img1, "Biryani","Biryani is a mixed rice dish originating among the Muslims of the Indian subcontinent. It is made with Indian spices, rice, and meat, and sometimes, in addition, eggs and/or vegetables such as potatoes in certain regional varieties."));
        arrayList.add(new Post(R.drawable.img2, "Bulgogi","Bulgogi, literally \"fire meat\", is a gui made of thin, marinated slices of beef or pork grilled on a barbecue or on a stove-top griddle. It is also often stir-fried in a pan in home cooking. Sirloin, rib eye or brisket are frequently used cuts of beef for the dish."));
        arrayList.add(new Post(R.drawable.img3, "Ramen","Ramen is a Japanese noodle soup. It consists of Chinese wheat noodles served in a meat or fish-based broth, often flavored with soy sauce or miso, and uses toppings such as sliced pork, nori, menma, and scallions."));

        //Adapter
        PostAdapter postAdapter = new PostAdapter(this, R.layout.post_view, arrayList);

        listView.setAdapter(postAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu, adds items to action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}