package com.purwohadi.moviecatalogue.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.purwohadi.moviecatalogue.R;
import com.purwohadi.moviecatalogue.model.TVShow;
import com.purwohadi.moviecatalogue.model.viewModel.TVShowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TVShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TVShowFragment extends Fragment {
    private TVShowAdapter adapter;
    private ProgressBar progressBar;
    private TVShowViewModel tvShowViewModel;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new TVShowAdapter();
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tvShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);


        progressBar = view.findViewById(R.id.progressBar);

        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
        tvShowViewModel.getTvShow().observe(this, getTvShow);
        tvShowViewModel.setTvShow("EXTRA_TV_SHOW");

        showLoading(true);

        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    private Observer<ArrayList<TVShow>>getTvShow = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvShows) {
            if (tvShows != null) {
                adapter.setTvData(tvShows);
            }

            showLoading(false);
        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
