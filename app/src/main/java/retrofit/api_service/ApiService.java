package retrofit.api_service;

import java.util.List;

import retrofit.models.PostsModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("posts")
    Call<List<PostsModel>> getAllPosts();

}
