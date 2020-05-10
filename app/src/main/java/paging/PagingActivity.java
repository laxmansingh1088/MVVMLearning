package paging;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ActivityPagingBinding;

import java.util.ArrayList;
import java.util.List;

import paging.adapters.MoviesAdapter;
import paging.models.Movies;
import paging.viewmodels.PopularMoviesViewModel;

public class PagingActivity extends AppCompatActivity {
    private Context mContext;
    private ActivityPagingBinding activityPagingBinding;
    private RecyclerView mRvMovies;
    private MoviesAdapter mMoviesAdapter;


    private PagedList<Movies> moviesList;

    PopularMoviesViewModel popularMoviesViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        activityPagingBinding = DataBindingUtil.setContentView(this, R.layout.activity_paging);

        setTitle("Paging Example");

        popularMoviesViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);

        getPopularMoviesWithPaging();
/*

        mRvMovies = activityPagingBinding.rvMovies;
        mRvMovies.setLayoutManager(new LinearLayoutManager(this));
        mMoviesAdapter = new MoviesAdapter(this);
        mRvMovies.setAdapter(mMoviesAdapter);


        popularMoviesViewModel.getAllPopularMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                moviesList.clear();
                moviesList.addAll(movies);
                mMoviesAdapter.notifyDataSetChanged();
            }
        });
*/


      /*  MovieApiService movieApiService = RetrofitInstance.getMovieApiService();

        Call<PopularMovieModel> call = movieApiService.getPopularMoviesWithPagination(getString(R.string.api_key), 1);

        call.enqueue(new Callback<PopularMovieModel>() {
            @Override
            public void onResponse(Call<PopularMovieModel> call, Response<PopularMovieModel> response) {
                if (!response.isSuccessful()) {
                    // tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    if (response.body() != null) {
                        moviesList.addAll(response.body().getMoviesList());
                        mMoviesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularMovieModel> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }


    private void getPopularMoviesWithPaging() {
        popularMoviesViewModel.getMoviesPagedList().observe(this, new Observer<PagedList<Movies>>() {
            @Override
            public void onChanged(PagedList<Movies> moviesFromLiveData) {
                moviesList = moviesFromLiveData;
                showOnRecyclerView();
            }
        });
    }


    private void showOnRecyclerView() {
        mRvMovies = activityPagingBinding.rvMovies;
        mRvMovies.setLayoutManager(new LinearLayoutManager(this));
        mMoviesAdapter = new MoviesAdapter(this);
        mMoviesAdapter.submitList(moviesList);
        mRvMovies.setAdapter(mMoviesAdapter);
    }


}
