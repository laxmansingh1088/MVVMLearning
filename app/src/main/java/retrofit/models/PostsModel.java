package retrofit.models;

import com.google.gson.annotations.SerializedName;

public class PostsModel {

    private String userId;
    @SerializedName("id")
    private String postId;
    private String title;
    @SerializedName("body")
    private String textBody;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }
}
