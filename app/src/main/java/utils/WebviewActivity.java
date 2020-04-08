package utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlearning.R;

public class WebviewActivity extends AppCompatActivity {

  //  https://www.google.co.in/search?ei=0pGMXt7wKJn2rQGQmqTADA&q=how+to+use+rx+with+room+android+example&oq=how+to+use+rx+with+room+android+ex&gs_lcp=CgZwc3ktYWIQAxgAMgUIIRCgATIFCCEQoAEyBQghEKABOgQIABBHSg0IFxIJMThnMTItMTkxSgoIGBIGMWcxMi01UIPDJFjk-SRgloglaAFwAXgAgAGEAogBrgeSAQUxLjEuM5gBAKABAaoBB2d3cy13aXqwAQA&sclient=psy-ab#kpvalbx=_M5SMXrLONcLprQHh-7xY34

    public static final String PARAMS_TITLE = "title";
    public static final String PARAMS_URL = "url";

    private Context mContext;
    WebView mWebView;

    private String toolbarTitle = "Webview";
    private String url = "https://www.google.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mContext = this;
        mWebView = findViewById(R.id.webView);
        if (getIntent() != null) {
            if (getIntent().hasExtra(PARAMS_URL))
                url = getIntent().getStringExtra(PARAMS_URL);
            if (getIntent().hasExtra(PARAMS_TITLE))
                toolbarTitle = getIntent().getStringExtra(PARAMS_TITLE);
        }
        setTitle(toolbarTitle);
        getActionBar().setDisplayShowHomeEnabled(true);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog mProgress;

            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (mProgress == null) {
                    mProgress = new ProgressDialog(mContext);
                    mProgress.show();
                }
                mProgress.setMessage(mContext.getString(R.string.loading));
                if (progress == 100) {
                    mProgress.dismiss();
                    mProgress = null;
                }
            }
        });
        mWebView.loadUrl(url);

        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

