package com.purwohadi.moviecatalogue.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.purwohadi.moviecatalogue.R;
import com.purwohadi.moviecatalogue.adapter.MovieAdapter;
import com.purwohadi.moviecatalogue.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    RecyclerView rv_movie;
    MovieAdapter movieAdapter;

    private ProgressBar progressBar;
    private Button button;

    private ArrayList<Movie> movieList = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        button = view.findViewById(R.id.retry);
        progressBar = view.findViewById(R.id.pb_movie);

        rv_movie = view.findViewById(R.id.rv_movie);
        rv_movie.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();

        return view;
    }

    private void getData() {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=f3284c069160e4cfd76c15db9f344441&language=en-US";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    String response = new String(responseBody);
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray results = responseObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject object = results.getJSONObject(i);
                        Movie movie = new Movie(object);
                        movieList.add(movie);
                    }
                    movieAdapter = new MovieAdapter(getActivity());
                    movieAdapter.setMovieList(movieList);
                    rv_movie.setAdapter(movieAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressBar.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getFragmentManager() != null) {
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.detach(MovieFragment.this).attach(MovieFragment.this).commit();
                        }
                    }
                });
                Toast.makeText(getContext(),R.string.check_connection, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
