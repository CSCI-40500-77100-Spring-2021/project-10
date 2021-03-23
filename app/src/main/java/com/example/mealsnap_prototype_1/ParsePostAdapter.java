////PARSE STUFF TO BE REMOVED
package com.example.mealsnap_prototype_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class ParsePostAdapter extends RecyclerView.Adapter<ParsePostAdapter.ViewHolder> {

    private Context context;
    private List<ParsePost> posts;

    public ParsePostAdapter(Context context, List<ParsePost> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_parse_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParsePost post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ParsePost> postList){
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ptvUsername;
        private TextView ptvDescription;
        private TextView ptvTitle;
        private ImageView pivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ptvDescription = itemView.findViewById(R.id.ppDesc);
            ptvTitle = itemView.findViewById(R.id.ppTitle);
            pivImage = itemView.findViewById(R.id.ppImage);
            ptvUsername = itemView.findViewById(R.id.ppUser);
        }

        public void bind(ParsePost post) {
            ptvDescription.setText(post.getDescription());
            ptvTitle.setText(post.getTitle());
            ptvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if(image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(pivImage);
            }
        }
    }
}
////PARSE STUFF TO BE REMOVED