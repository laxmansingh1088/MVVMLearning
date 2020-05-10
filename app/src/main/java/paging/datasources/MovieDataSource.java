package paging.datasources;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.mvvmlearning.R;

import java.util.ArrayList;

import paging.apiservice.MovieApiService;
import paging.models.Movies;
import paging.models.PopularMovieModel;
import paging.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movies> {

    Application application;
    MovieApiService movieApiService;


    public MovieDataSource(Application application, MovieApiService movieApiService) {
        this.application = application;
        this.movieApiService = movieApiService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, final @NonNull LoadInitialCallback<Long, Movies> callback) {
        movieApiService = RetrofitInstance.getMovieApiService();
        Call<PopularMovieModel> call = movieApiService.getPopularMoviesWithPagination(application.getString(R.string.api_key), 1);

        call.enqueue(new Callback<PopularMovieModel>() {
            @Override
            public void onResponse(Call<PopularMovieModel> call, Response<PopularMovieModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(application, application.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (response.body() != null && response.body().getMoviesList() != null) {
                        PopularMovieModel popularMovieModel = response.body();
                        ArrayList<Movies> movies = new ArrayList<>();
                        movies = (ArrayList<Movies>) popularMovieModel.getMoviesList();
                        callback.onResult(movies, null, (long) 2);
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularMovieModel> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movies> callback) {

    }

    @Override
    public void loadAfter(final @NonNull LoadParams<Long> params, final @NonNull LoadCallback<Long, Movies> callback) {
        movieApiService = RetrofitInstance.getMovieApiService();
        Call<PopularMovieModel> call = movieApiService.getPopularMoviesWithPagination(application.getString(R.string.api_key), params.key);

        call.enqueue(new Callback<PopularMovieModel>() {
            @Override
            public void onResponse(Call<PopularMovieModel> call, Response<PopularMovieModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(application, application.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (response.body() != null && response.body().getMoviesList() != null) {
                        PopularMovieModel popularMovieModel = response.body();
                        ArrayList<Movies> movies = new ArrayList<>();
                        movies = (ArrayList<Movies>) popularMovieModel.getMoviesList();
                        callback.onResult(movies, params.key + 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularMovieModel> call, Throwable t) {
                Toast.makeText(application, application.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
