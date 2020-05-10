package paging.network;

import androidx.MyApplication;

import com.example.mvvmlearning.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import paging.apiservice.MovieApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static MovieApiService movieApiService;

    public static MovieApiService getMovieApiService() {
        if (movieApiService == null) {
            Gson gson = new GsonBuilder().serializeNulls().create();
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(MyApplication.getInstance().getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            movieApiService = retrofit.create(MovieApiService.class);
        }
        return movieApiService;
    }
}
