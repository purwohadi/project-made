package com.purwohadi.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.purwohadi.moviecatalogue.model.Movie;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movies";
    TextView descriptionMovie, judulMovie, releaseDate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        /*Menampilkan gambar*/
        imageView = findViewById(R.id.imageView);
        String photo = movie.getPhoto();

        GlideApp.with(DetailActivity.this)
                .load("https://image.tmdb.org/t/p/w185/"+photo)
                .into(imageView);

        /*Menampilkan nama judul*/
        judulMovie = findViewById(R.id.judul_movie);
        judulMovie.setText(movie.getJudul());

        /*Menampilkan release*/
        releaseDate = findViewById(R.id.release_movie);
        releaseDate.setText(movie.getTanggalRilis());

        /*Menampilkan isi deskripsi*/
        descriptionMovie = findViewById(R.id.description_movie);
        descriptionMovie.setText(movie.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
