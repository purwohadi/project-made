package com.purwohadi.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.purwohadi.moviecatalogue.DetailActivity;
import com.purwohadi.moviecatalogue.R;
import com.purwohadi.moviecatalogue.model.Movie;
import com.purwohadi.moviecatalogue.model.MovieModel;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<MovieModel> movieList = new ArrayList<>();

    public MovieAdapter(Context context) {

        this.context = context;
    }

    public ArrayList<MovieModel> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<MovieModel> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder movieViewHolder, int i) {
        MovieModel movie = getMovieList().get(i);
        movieViewHolder.txtJudul.setText(movie.getJudul());
        movieViewHolder.txtDescription.setText(movie.getDescription());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185/"+movieList.get(i).getPhoto())
                .into(movieViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getMovieList().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul;
        private TextView txtDescription;
        private ImageView imgPhoto;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            txtJudul = itemView.findViewById(R.id.txt_judul);
            txtDescription = itemView.findViewById(R.id.txt_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int position = getAdapterPosition();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movies", movieList.get(position));
                context.startActivity(intent);
                }
            });
        }
    }
}
