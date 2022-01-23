package com.example.zedium;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.InetAddress;

public class ShareActivity  extends AppCompatActivity {

    public String URL;
    public FloatingActionButton main_fab;
    public TextView appText;
    public RelativeLayout search_layout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            String action = intent.getAction();
            String type = intent.getType();

            if ("android.intent.action.SEND".equals(action) && type != null && "text/plain".equals(type)) {

                URL = intent.getStringExtra("android.intent.extra.TEXT");
                Toast.makeText(getApplicationContext(), intent.getStringExtra("android.intent.extra.TEXT"), Toast.LENGTH_LONG).show();

                setContentView(R.layout.activity_main);

                this.search_layout = (RelativeLayout) findViewById(R.id.search_layout);
                search_layout.setVisibility(View.GONE);

                this.appText = (TextView) findViewById(R.id.appText);

                appText.setVisibility(View.GONE);

                WebView webView = (WebView) findViewById(R.id.webView);
                webView.clearHistory();
                webView.clearCache(true);
                webView.clearFormData();
                webView.clearSslPreferences();
                WebStorage.getInstance().deleteAllData();
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(URL);

                main_fab = (FloatingActionButton) findViewById(R.id.fab_button);
                main_fab.setImageResource(R.drawable.ic_refresh_arrows_svgrepo_com);

                this.main_fab.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), "Refreshing ... ", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), URL, Toast.LENGTH_LONG).show();

                        webView.clearHistory();
                        webView.clearCache(true);
                        webView.clearFormData();
                        webView.clearSslPreferences();
                        WebStorage.getInstance().deleteAllData();
                        CookieManager.getInstance().removeAllCookies(null);
                        CookieManager.getInstance().flush();
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(URL);

                    }
                });

            }

        }
        catch (Exception e){

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  // optional depending on your needs
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }
}


