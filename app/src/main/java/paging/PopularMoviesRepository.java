package paging;

import android.app.Application;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlearning.R;

import java.util.ArrayList;
import java.util.List;

import ebookshop_mvvm.database.BooksDatabase;
import ebookshop_mvvm.models.Books;
import paging.apiservice.MovieApiService;
import paging.models.Movies;
import paging.models.PopularMovieModel;
import paging.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMoviesRepository {
    Application application;
    MovieApiService movieApiService;
    ArrayList<Movies> movies = new ArrayList<>();
    private MutableLiveData<List<Movies>> mutableLiveData = new MutableLiveData<>();

    public PopularMoviesRepository(Application application, MovieApiService movieApiService) {
        this.application = application;
        this.movieApiService = movieApiService;
    }

    public MutableLiveData<List<Movies>> getMutableLiveData() {
        // MovieApiService movieApiService = RetrofitInstance.getMovieApiService();
        Call<PopularMovieModel> call = movieApiService.getPopularMoviesWithPagination(application.getString(R.string.api_key), 1);

        call.enqueue(new Callback<PopularMovieModel>() {
            @Override
            public void onResponse(Call<PopularMovieModel> call, Response<PopularMovieModel> response) {
                if (!response.isSuccessful()) {
                    // tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    if (response.body() != null) {
                        movies = (ArrayList<Movies>) response.body().getMoviesList();
                        mutableLiveData.setValue(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularMovieModel> call, Throwable t) {
                //Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return mutableLiveData;
    }

}
