package com.example.zedium;

import android.content.Intent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    public String URL;
    public WebView webView;
    int layoutFlag = 0;
    public FloatingActionButton main_fab , btn_search;
    public RelativeLayout search_layout;
    public int main_layout_id = R.layout.activity_main;
    public EditText txt_searchbox;
    public TextView appText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            this.txt_searchbox = (EditText) findViewById(R.id.txt_searchbox);
            this.search_layout = (RelativeLayout) findViewById(R.id.search_layout);
            this.main_fab = (FloatingActionButton) findViewById(R.id.fab_button);
            this.search_layout = (RelativeLayout) findViewById(R.id.search_layout);
            this.btn_search = (FloatingActionButton) findViewById(R.id.btn_search);

            this.search_layout.setVisibility(View.GONE);

            this.main_fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(layoutFlag == 1)
                    {
                        search_layout = (RelativeLayout) findViewById(R.id.search_layout);
                        search_layout.setVisibility(View.GONE);

                        main_fab.setImageResource(R.drawable.ic_icons8_search);
                        layoutFlag = 0;
                    }
                    else
                    {
                        search_layout = (RelativeLayout) findViewById(R.id.search_layout);
                        search_layout.setVisibility(View.VISIBLE);

                        main_fab.setImageResource(R.drawable.ic_arrow_back_svgrepo_com);

                        layoutFlag = 1;
                    }
                }
            });

            this.btn_search.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    URL = "https://" + txt_searchbox.getText().toString();

                    Toast.makeText(getApplicationContext(), URL, Toast.LENGTH_LONG).show();

                    appText = (TextView) findViewById(R.id.appText);
                    appText.setVisibility(View.GONE);

                    main_fab.setImageResource(R.drawable.ic_icons8_search);

                    layoutFlag = 0;

                    search_layout = (RelativeLayout) findViewById(R.id.search_layout);
                    search_layout.setVisibility(View.GONE);

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

                }
            });

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed()
    {
        if(layoutFlag == 1){
            layoutFlag = 0;
            try {
                search_layout = (RelativeLayout) findViewById(R.id.search_layout);
                search_layout.setVisibility(View.GONE);
                main_fab = (FloatingActionButton) findViewById(R.id.fab_button);
                main_fab.setImageResource(R.drawable.ic_icons8_search);

            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }
        else{
            super.onBackPressed();  // optional depending on your needs
        }
    }

}