package paging.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.mvvmlearning.BR;
import com.example.mvvmlearning.R;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

import paging.adapters.MoviesAdapter;

public class Movies extends BaseObservable {

    private double popularity;
    private long vote_count;
    private boolean video;
    @SerializedName("poster_path")
    private String imageUrl;
    private Integer id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private List<Long> genre_ids;
    private String title;
    private String vote_average;
    private String overview;
    private String release_date;


    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getVote_count() {
        return vote_count;
    }

    public void setVote_count(long vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    @Bindable
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Long> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Long> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public static final DiffUtil.ItemCallback<Movies> CALLBACK = new DiffUtil.ItemCallback<Movies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.popularity == newItem.popularity
                    && oldItem.vote_count == newItem.vote_count
                    && oldItem.video == newItem.video
                    && oldItem.imageUrl.equals(newItem.imageUrl)
                    && oldItem.id.equals(newItem.id)
                    && oldItem.adult == newItem.adult
                    && oldItem.backdrop_path.equals(newItem.backdrop_path)
                    && oldItem.original_language.equals(newItem.popularity)
                    && oldItem.original_title.equals(newItem.original_title)
                    && oldItem.genre_ids.equals(newItem.genre_ids)
                    && oldItem.title.equals(newItem.title)
                    && oldItem.vote_average.equals(newItem.vote_average)
                    && oldItem.overview.equals(newItem.overview)
                    && oldItem.release_date.equals(newItem.release_date);
        }
    };
}
