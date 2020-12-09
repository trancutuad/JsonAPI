package com.example.baithi1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Item_MainActivity2 extends AppCompatActivity {

    TextView author1;
    TextView width2;
    TextView height3;
    ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__main2);

        author1 = findViewById(R.id.author);
        width2 = findViewById(R.id.width);
        height3 = findViewById(R.id.height);
        img4 = findViewById(R.id.image);

        Intent intent = getIntent();

        String author = intent.getStringExtra("author");
        String width = intent.getStringExtra("width");
        String height = intent.getStringExtra("height");

      String image = intent.getStringExtra("download_url");

        author1.setText(author);
        width2.setText(width);
        height3.setText(height);

        //img
     Glide.with(this)
              .load(image)
              .into(img4);

    }
}