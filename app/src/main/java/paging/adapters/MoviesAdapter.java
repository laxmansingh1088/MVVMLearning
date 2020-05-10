package paging.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.MoviesListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import paging.models.Movies;

public class MoviesAdapter extends PagedListAdapter<Movies, RecyclerView.ViewHolder> {

    private Context context;
    //  private List<Movies> moviesList;

    public MoviesAdapter(Context context) {
        super(Movies.CALLBACK);
        this.context = context;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        MoviesListItemBinding moviesListItemBinding;

        public MoviesViewHolder(MoviesListItemBinding moviesListItemBinding) {
            super(moviesListItemBinding.getRoot());
            this.moviesListItemBinding = moviesListItemBinding;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesListItemBinding moviesListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movies_list_item, parent, false);
        return new MoviesViewHolder(moviesListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Movies movies = getItem(position);
        if (holder instanceof MoviesViewHolder) {
            ((MoviesViewHolder) holder).moviesListItemBinding.setMovie(movies);
         //   Picasso.get().load(context.getString(R.string.image_base_url) + movies.getPoster_path()).into(((MoviesViewHolder) holder).moviesListItemBinding.imgMoviePoster);
        }
    }

}
