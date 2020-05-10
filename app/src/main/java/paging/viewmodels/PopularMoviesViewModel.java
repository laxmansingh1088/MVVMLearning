package paging.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ebookshop_mvvm.models.Category;
import ebookshop_mvvm.repositories.CategoryRepository;
import paging.PopularMoviesRepository;
import paging.apiservice.MovieApiService;
import paging.datasources.MovieDataSource;
import paging.datasources.MovieDataSourceFactory;
import paging.models.Movies;
import paging.network.RetrofitInstance;

public class PopularMoviesViewModel extends AndroidViewModel {
    private PopularMoviesRepository popularMoviesRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movies>> moviesPagedList;


    public PopularMoviesViewModel(@NonNull Application application) {
        super(application);
        MovieApiService movieApiService = RetrofitInstance.getMovieApiService();
        popularMoviesRepository = new PopularMoviesRepository(application, movieApiService);


        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieApiService, application);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        moviesPagedList = (new LivePagedListBuilder<Long, Movies>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }


    public LiveData<List<Movies>> getAllPopularMovies() {
        return popularMoviesRepository.getMutableLiveData();
    }


    public LiveData<PagedList<Movies>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
