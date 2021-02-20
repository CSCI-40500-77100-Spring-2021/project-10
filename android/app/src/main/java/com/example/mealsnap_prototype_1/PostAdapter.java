package com.example.mealsnap_prototype_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {

    private Context context;
    private  int resource;

    public PostAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource, parent, false);

        ImageView pImage = convertView.findViewById(R.id.pImage);
        TextView pTitle = convertView.findViewById(R.id.pTitle);
        TextView pDesc = convertView.findViewById(R.id.pDesc);

        pImage.setImageResource(getItem(position).getpImage());
        pTitle.setText(getItem(position).getpTitle());
        pDesc.setText(getItem(position).getpDesc());


        return convertView;
    }
}
