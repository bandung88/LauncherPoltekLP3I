package id.ac.lp3i.politekniklp3i;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebBrowser extends AppCompatActivity {

    private String _url = null;
    private WebView wv;
    private Toolbar _toolbar;
    private String _title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        _toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _url = getIntent().getStringExtra("url");
        _title = getIntent().getStringExtra("title");

        setTitle(_title);

        wv = (WebView) findViewById(R.id.browsernya);
        wv.invalidate();
        wv.setWebViewClient(new LepeiBrowser(this));
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        wv.setLongClickable(false);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.loadUrl(_url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv.canGoBack()) {
                        wv.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(wv.canGoBack()){
                    wv.goBack();
                }else{
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browser_menu, menu);
        return true;
    }

    /********************************** CUSTOM WEB BROWSER **************************************/
    public class LepeiBrowser extends WebViewClient {
        ProgressDialog _progressDialog = null;
        Context _appContext = WebBrowser.this;

        public LepeiBrowser(Context appContext) {
            this._appContext = appContext;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (_progressDialog == null) {
                _progressDialog = new ProgressDialog(_appContext);
                _progressDialog.setMessage("Loading...");
                _progressDialog.setCanceledOnTouchOutside(false);
                _progressDialog.show();
            }
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/error_page.html");
            //Toast.makeText(WebBrowser.this, String.valueOf(errorCode), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            //Toast.makeText(WebBrowser.this, "error", Toast.LENGTH_LONG).show();
            super.onReceivedError(view, request, error);
            view.loadUrl("file:///android_asset/error_page.html");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            try {
                if (_progressDialog.isShowing()) {
                    _progressDialog.dismiss();
                    _progressDialog = null;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (_progressDialog == null) {
                _progressDialog = new ProgressDialog(_appContext);
                _progressDialog.setMessage("Loading...");
                _progressDialog.setCanceledOnTouchOutside(false);
                _progressDialog.show();
            }
        }

    }
}