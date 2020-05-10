package retrofit;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlearning.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.api_service.ApiService;
import retrofit.models.CommentsModel;
import retrofit.models.PostsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    private Context mContext;
    private TextView tv_result;
    // Base url must end with slash other wise it will crash...
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";


    /*
     *   PUT & PATCH are used for update data on server,
     *   PUT is used for complete replacement of existing objects
     *   PATCH is used to change only the values which we send
     *   DELETE is used to delete data on server
     * */


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_retrofit);
        tv_result = findViewById(R.id.tv_result);

        // If we want to serialize or put null in json and does not want to drop null values.
        // pass the gson object to the --- GsonConverterFactory.create(gson) method.
        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        /*GET type of apis */

        //   simpleGetApi(apiService);
        //   getPathParameterApi(apiService);
        //   getQueryParametersApi(apiService);


        /* POST type of apis */
        // createPostApi(apiService);


        /* PUT type of apis */
        //   createPUTApi(apiService);


        /* PATCH type of apis */
        // createPATCHApi(apiService);

        /* DELETE type of apis */
        createDeleteApi(apiService);
    }


    public void simpleGetApi(ApiService apiService) {
        Call<List<PostsModel>> call = apiService.getAllPosts();


        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    List<PostsModel> postsModelList = response.body();
                    for (PostsModel postsModel : postsModelList) {
                        String content = "";
                        content += "ID: " + postsModel.getPostId() +
                                "\nUserID: " + postsModel.getUserId() +
                                "\nTitle: " + postsModel.getTitle() +
                                "\nBody: " + postsModel.getTextBody() + "\n\n";
                        tv_result.append(content);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getPathParameterApi(ApiService apiService) {

        Call<List<CommentsModel>> call = apiService.getComments(2);

        //Get request with path parameter and we do not want to pass id insted we will pass the end url..
        // url:- https://jsonplaceholder.typicode.com/posts/2/comments
        int userId = 2;
        Call<List<CommentsModel>> call1 = apiService.getComments("posts/" + userId + "/comments");


        call.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    List<CommentsModel> commentsModelList = response.body();
                    for (CommentsModel commentsModel : commentsModelList) {
                        String content = "";
                        content += "ID: " + commentsModel.getPostId() +
                                "\nCommentID: " + commentsModel.getCommentId() +
                                "\nName: " + commentsModel.getName() +
                                "\nEmail: " + commentsModel.getEmail() +
                                "\nComment Body: " + commentsModel.getTextBody() + "\n\n";
                        tv_result.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getQueryParametersApi(ApiService apiService) {

        Call<List<PostsModel>> call = apiService.getPosts(5, "id", "desc");

        // if we pass null as arguments
        Call<List<PostsModel>> call1 = apiService.getPosts(5, null, null);

        // We cannot pass int as null so we have to change in the api interface query parameter to Integer
        Call<List<PostsModel>> call2 = apiService.getPosts(null, null, null, null);

        // if we want to query for userId 1 and 3
        Call<List<PostsModel>> call3 = apiService.getPosts(1, 3, null, null);

        // if we want to pass multiple userIds we can pass List or array of userIds as query parameter
        Call<List<PostsModel>> call4 = apiService.getPosts(new Integer[]{1, 2, 3}, null, null);
        List<Integer> userIdList = new ArrayList<>();
        userIdList.add(1);
        userIdList.add(5);
        userIdList.add(8);
        userIdList.add(10);
        Call<List<PostsModel>> call5 = apiService.getPosts(userIdList, null, null);

        // if we want to pass multiple userIds using varargs... , use three dots and place that variable at last position in api interface method
        Call<List<PostsModel>> call6 = apiService.getPosts(null, null, 1, 2, 7, 4);


        // if we do not want to pass multiple parametes and we want to specify the query parameter here... then we will use
        // QueryMap and pass the map with all the parameters in the map, but we can not add multiple userId , this is the down side of the QueryMap
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("userId", "5");
        paramsMap.put("_sort", "id");
        paramsMap.put("_order", "desc");
        Call<List<PostsModel>> call7 = apiService.getPosts(paramsMap);

        call.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    List<PostsModel> postsModelList = response.body();
                    for (PostsModel postsModel : postsModelList) {
                        String content = "";
                        content += "ID: " + postsModel.getPostId() +
                                "\nUserID: " + postsModel.getUserId() +
                                "\nTitle: " + postsModel.getTitle() +
                                "\nBody: " + postsModel.getTextBody() + "\n\n";
                        tv_result.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createPostApi(ApiService apiService) {
        PostsModel postsModel = new PostsModel("1", "laxman singh", "This post is created by laxman singh and is created with the help of Sending complete body in Post request.");
        // Sending a complete body in Post api request
        Call<PostsModel> call = apiService.createPost(postsModel);

        // Instead of sending a complete body  there is an alternate method which is FormUrlEncoded method
        Call<PostsModel> call1 = apiService.createPost(1, "Sahil Bisht", "This post is ceated with the help of FormUrlEncoded which is an alternate " +
                "method of Sending a complete body.");


        // Instead of sending a complete body  there is an alternate method which is FormUrlEncoded method
        // if we do not want to pass multiple parametes and we want to specify the FieldMap parameter here... then we will use
        // FieldMap and pass the map with all the parameters in the map, but we can not add multiple userId , this is the down side of the FieldMap
        Map<String, String> fieldsMap = new HashMap<>();
        fieldsMap.put("userId", "1");
        fieldsMap.put("title", "title");
        fieldsMap.put("body", "Posts body");
        Call<PostsModel> call2 = apiService.createPost(fieldsMap);


        call.enqueue(new Callback<PostsModel>() {
            @Override
            public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    PostsModel postsModel = response.body();
                    String content = "";
                    content += "ID: " + postsModel.getPostId() +
                            "\nUserID: " + postsModel.getUserId() +
                            "\nTitle: " + postsModel.getTitle() +
                            "\nBody: " + postsModel.getTextBody() + "\n\n";
                    tv_result.append(content);

                }
            }

            @Override
            public void onFailure(Call<PostsModel> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createPUTApi(ApiService apiService) {
        PostsModel postsModel = new PostsModel("1", "laxman singh", "This post is created by laxman singh and is created with the help of Sending complete body in PUT request.");

        // Replacing of updating a Postmodel object in PUT api request
        Call<PostsModel> call = apiService.putPost("5", postsModel);

        call.enqueue(new Callback<PostsModel>() {
            @Override
            public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    PostsModel postsModel = response.body();
                    String content = "";
                    content += "ID: " + postsModel.getPostId() +
                            "\nUserID: " + postsModel.getUserId() +
                            "\nTitle: " + postsModel.getTitle() +
                            "\nBody: " + postsModel.getTextBody() + "\n\n";
                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<PostsModel> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createPATCHApi(ApiService apiService) {
        PostsModel postsModel = new PostsModel();
        postsModel.setTextBody("Body change only through PATCH.");
        // Sending only the values we want to update -- Patch only changes the value we passed in api
        Call<PostsModel> call = apiService.patchPost("5", postsModel);

        call.enqueue(new Callback<PostsModel>() {
            @Override
            public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {
                if (!response.isSuccessful()) {
                    tv_result.setText("Code:  " + response.code());
                    return;
                } else {
                    PostsModel postsModel = response.body();
                    String content = "";
                    content += "ID: " + postsModel.getPostId() +
                            "\nUserID: " + postsModel.getUserId() +
                            "\nTitle: " + postsModel.getTitle() +
                            "\nBody: " + postsModel.getTextBody() + "\n\n";
                    tv_result.append(content);
                }
            }

            @Override
            public void onFailure(Call<PostsModel> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createDeleteApi(ApiService apiService) {
        // Delete the post
        Call<Void> call = apiService.deletePost(2);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Post deleted Successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
