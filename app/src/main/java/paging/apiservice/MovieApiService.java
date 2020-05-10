package paging.apiservice;

import java.util.List;

import paging.models.PopularMovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    //Get request with multiple Query parameter
    // url:- ttps://api.themoviedb.org/3/movie/popular?api_key=28a6e8342d06b1e48285ddb34f8a860c&language=en-US&page=1
    @GET("movie/popular")
    Call<PopularMovieModel> getPopularMoviesWithPagination(@Query("api_key") String apiKey, @Query("page") long page);
}
