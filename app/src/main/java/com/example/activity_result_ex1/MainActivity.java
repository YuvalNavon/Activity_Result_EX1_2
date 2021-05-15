package com.example.activity_result_ex1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText A, B, C;
    TextView x1, x2;

    Random rand;

    int a, b, c;
    double ans1, ans2;
    int max = 100;
    int min = -100;
    int n = max - min + 1;
    boolean neg_shoresh1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rand = new Random();
        A = findViewById(R.id.p1);
        B = findViewById(R.id.p2);
        C = findViewById(R.id.p3);
        x1 = findViewById(R.id.ans1);
        x2 = findViewById(R.id.ans2);


    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        if (data_back != null) {
            ans2 = data_back.getDoubleExtra("x2", 1);
            ans1 = data_back.getDoubleExtra("x1", 1);
            neg_shoresh1 = data_back.getBooleanExtra("x3", false);
            if (neg_shoresh1 == true) {
                x1.setText("X1 = " + ans1 + " + " + ans2 + "i");
                x2.setText("X2 = " + ans1 + " - " + ans2 + "i");
            }
            else {
                x1.setText("X1: " + ans1);
                x2.setText("X2: " + ans2);
            }


        }
    }

    public void randbtn(View view) {
        a = rand.nextInt() % n;
        b = rand.nextInt() % n;
        c = rand.nextInt() % n;
        A.setText(Integer.toString(a));
        B.setText(Integer.toString(b));
        C.setText(Integer.toString(c));
    }

    public void resultbtn(View view) {
        if (A.getText().toString().isEmpty() || B.getText().toString().isEmpty() || C.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input all pararmeters!", Toast.LENGTH_SHORT).show();
        }
        else {
            a = Integer.parseInt(A.getText().toString());
            b = Integer.parseInt(B.getText().toString());
            c = Integer.parseInt(C.getText().toString());
            Intent si = new Intent(this, results.class);
            si.putExtra("n", a);
            si.putExtra("nn", b);
            si.putExtra("nnn", c);
            startActivityForResult(si, 1);
        }


    }


}