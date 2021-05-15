package com.example.activity_result_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class results extends AppCompatActivity {
    WebView show;
    TextView x3, x4;
    String url;

    int a, b, c;
    double root1, root2;
    double shoresh;


    boolean neg_shoresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent gi = getIntent();
        a = gi.getIntExtra("n", 1);
        b = gi.getIntExtra("nn", 1);
        c = gi.getIntExtra("nnn", 1);
        x3 = findViewById(R.id.finans1);
        x4 = findViewById(R.id.finans2);
        show = findViewById(R.id.web);

        url = "https://www.google.com/search?q=" + a + "x%5E2%2B" + b + "x%2B" + c + "&oq=x%5E2&aqs=chrome.0.69i59l3j69i57j0l2j69i61l2.3824j0j7&sourceid=chrome&ie=UTF-8";

        show.getSettings().setJavaScriptEnabled(true);
        show.setWebViewClient(new MyWebViewClient());

        shoresh = b^2 - 4 * a * c;

        if (shoresh > 0) {
            root1 = (-b + Math.sqrt(shoresh)) / (2 * a);
            root2 = (-b - Math.sqrt(shoresh)) / (2 * a);

        }
        else if (shoresh == 0) {
            root1 = root2 = -b / (2 * a);

        }
        else {
            root1 = -b / (2 * a);
            root2 = Math.sqrt(-shoresh) / (2 * a);
            neg_shoresh = true;
        }

        if (neg_shoresh == true){
            x3.setText("X1 = " + root1 + " + "+root2+"i");
            x4.setText("X2 = " + root1 + " - "+root2+"i");
        }
        else {
            x3.setText("X1: " + root1);
            x4.setText("X2: " + root2);
        }

        show.loadUrl(url);

    }

    public void backbtn(View view) {
        Intent wi = getIntent();
        wi.putExtra("x2", root2);
        wi.putExtra("x1",root1);
        wi.putExtra("x3",neg_shoresh);
        setResult(RESULT_OK,wi);
        finish();
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}