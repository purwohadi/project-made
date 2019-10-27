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
import com.purwohadi.moviecatalogue.DetailTvActivity;
import com.purwohadi.moviecatalogue.R;
import com.purwohadi.moviecatalogue.model.Tv;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {

    private Context context;
    private ArrayList<Tv> tvList = new ArrayList<>();

    public TvAdapter(Context context) {

        this.context = context;
    }

    public ArrayList<Tv> getTvList() {
        return tvList;
    }
    public void setTvList(ArrayList<Tv> tvList) {
        this.tvList = tvList;
    }

    @NonNull
    @Override
    public TvAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvViewHolder movieViewHolder, int i) {
        Tv tv = getTvList().get(i);
        movieViewHolder.txtJudul.setText(tv.getJudul());
        movieViewHolder.txtDescription.setText(tv.getDescription()+" ");

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185/"+tvList.get(i).getPhoto())
                .into(movieViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getTvList().size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul;
        private TextView txtDescription;
        private ImageView imgPhoto;

        public TvViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            txtJudul = itemView.findViewById(R.id.txt_judul);
            txtDescription = itemView.findViewById(R.id.txt_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int position = getAdapterPosition();
                Intent intent = new Intent(context, DetailTvActivity.class);
                intent.putExtra("tv", tvList.get(position));
                context.startActivity(intent);
                }
            });
        }
    }
}
