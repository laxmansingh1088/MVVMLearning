package retrofit.api_service;

import androidx.room.Delete;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit.models.CommentsModel;
import retrofit.models.PostsModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

public interface ApiService {

    /*GET type of API's */

    //Simple get request
    // url:- https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<List<PostsModel>> getAllPosts();

    //Simple get request
    // url:- https://jsonplaceholder.typicode.com/posts
    @GET("{voipId}/extensions/{voipPhoneId}")
    Call<String> getIncomingRules(@Path("voipId") int voipId, @Path("voipPhoneId") int voipPhoneId, @HeaderMap Map<String, String> headers);


    //Get request with path parameter
    // url:- https://jsonplaceholder.typicode.com/posts/2/comments
    @GET("posts/{postId}/comments")
    Call<List<CommentsModel>> getComments(@Path("postId") int postId);


    //Get request with path parameter and we do not want to pass id insted we will pass end url..
    // url:- https://jsonplaceholder.typicode.com/posts/2/comments
    @GET()
    Call<List<CommentsModel>> getComments(@Url String url);


    //Get request with Query parameter
    // url:- https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("userId") int userId);


    //Get request with multiple Query parameter
    // url:- https://jsonplaceholder.typicode.com/posts?userId=1&_id=id&_sort=desc
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("userId") int userId, @Query("_sort") String id, @Query("_order") String order);

    // if we have to pass null in int type of field change int --> Integer
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("userId") Integer userId
            , @Query("postId") Integer postId
            , @Query("_sort") String id
            , @Query("_order") String order);


    // if we want to fetch data for userid 1 and 3 simultaneously
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("userId") int userId
            , @Query("userId") int userId1
            , @Query("_sort") String id
            , @Query("_order") String order);

    //  if we want to pass multiple userIds we can use List or array as query parameter
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("userId") Integer[] userIdArray
            , @Query("_sort") String id
            , @Query("_order") String order);

    // if we want to pass multiple userIds we can use List or array as query parameter
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("userId") List<Integer> userIdsList
            , @Query("_sort") String id
            , @Query("_order") String order);

    // if we want to pass multiple userIds using varargs... , use three dots and place that variable at last position
    @GET("posts")
    Call<List<PostsModel>> getPosts(@Query("_sort") String id
            , @Query("_order") String order, @Query("userId") int... userIdsVarArgs);


    // if we do not want to pass multiple parametes and we want to specify the query parameter here... then we will use
    // QueryMap and pass the map with all the parameters in the map, but we can not add multiple userId , this is the down side of the QueryMap
    @GET("posts")
    Call<List<PostsModel>> getPosts(@QueryName Map<String, String> parameters);




    /* POST Type of API's in which we can send data to servers */

    // Sending a complete body in Post request
    @POST("posts")
    Call<PostsModel> createPost(@Body PostsModel postsModel);


    // Instead of sending a complete body  there is an alternate method which is FormUrlEncoded method
    @FormUrlEncoded
    @POST("posts")
    Call<PostsModel> createPost(@Field("userId") int userId
            , @Field("title") String title
            , @Field("body") String body);


    // Instead of sending a complete body  there is an alternate method which is FormUrlEncoded method
    // if we do not want to pass multiple parametes and we want to specify the FieldMap parameter here... then we will use
    // FieldMap and pass the map with all the parameters in the map, but we can not add multiple userId , this is the down side of the FieldMap
    @FormUrlEncoded
    @POST("posts")
    Call<PostsModel> createPost(@FieldMap Map<String, String> fields);


    /* PUT Type of API's in which we can update data on servers */
    @PUT("posts/{postId}")
    Call<PostsModel> putPost(@Path("postId") String postId, @Body PostsModel postsModel);


    /* PATCH Type of API's in which we can change the values we send to servers */
    @PATCH("posts/{postId}")
    Call<PostsModel> patchPost(@Path("postId") String postId, @Body PostsModel postsModel);


    /* DELETE Type of API's is used to delete a object on server */
    @DELETE("posts/{postId}")
    Call<Void> deletePost(@Path("postId") int postId);

}
