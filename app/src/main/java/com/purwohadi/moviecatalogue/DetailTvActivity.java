package com.purwohadi.moviecatalogue;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.purwohadi.moviecatalogue.model.Tv;

public class DetailTvActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "tv";
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

        Tv tv = getIntent().getParcelableExtra(EXTRA_MOVIE);

        /*Menampilkan gambar*/
        imageView = findViewById(R.id.imageView);
        String photo = tv.getPhoto();

        GlideApp.with(DetailTvActivity.this)
                .load("https://image.tmdb.org/t/p/w185/"+photo)
                .into(imageView);

        /*Menampilkan nama judul*/
        judulMovie = findViewById(R.id.judul_movie);
        judulMovie.setText(tv.getJudul());

        /*Menampilkan release*/
        releaseDate = findViewById(R.id.release_movie);
        releaseDate.setText(tv.getTanggalRilis());

        /*Menampilkan isi deskripsi*/
        descriptionMovie = findViewById(R.id.description_movie);
        descriptionMovie.setText(tv.getDescription());
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
