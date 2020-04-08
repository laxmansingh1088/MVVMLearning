package retrofit;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlearning.R;

import java.util.List;

import retrofit.api_service.ApiService;
import retrofit.models.PostsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    private Context mContext;
    private TextView tv_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_retrofit);
        tv_result = findViewById(R.id.tv_result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

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
}
