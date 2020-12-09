package com.example.baithi1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.baithi1.Model.Animals;
import com.example.baithi1.Network.APIclient;
import com.example.baithi1.Network.APIinterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    CustomAdapter customAdapter;
    public static List<Animals> animalsList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        swipeRefreshLayout = findViewById(R.id.sr);

        animalsList = new ArrayList<>();

        Refresh();
        callAPI();
    }

    // refresh
    private void Refresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                callAPI();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    public void callAPI(){
        Call<List<Animals>> call = APIclient.apIinterface().getAnimals();

        call.enqueue(new Callback<List<Animals>>() {
            @Override
            public void onResponse(Call<List<Animals>> call, Response<List<Animals>> response) {
            if (response.isSuccessful()){

                animalsList = response.body();
                customAdapter = new CustomAdapter(response.body(),MainActivity.this);
                gridView.setAdapter(customAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                        Intent intent = new Intent();
                        intent.putExtra("author",animalsList.get(i).getAuthor());
                        intent.putExtra("width",animalsList.get(i).getWidth());
                        intent.putExtra("height",animalsList.get(i).getHeight());

                        intent.putExtra("download_url",animalsList.get(i).getDownloadUrl());

                        startActivity(new Intent(getApplicationContext(),Item_MainActivity2.class).putExtra("author",animalsList.get(i).getAuthor()).putExtra("width",animalsList.get(i).getWidth()).putExtra("height",animalsList.get(i).getHeight()).putExtra("download_url",animalsList.get(i).getDownloadUrl()));
                    }
                });

            }else {
                Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
            }
            }

            @Override
            public void onFailure(Call<List<Animals>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public class CustomAdapter extends BaseAdapter{

        public List<Animals> animalsList;
        public Context context;

        public CustomAdapter(List<Animals> animalsList, Context context) {
            this.animalsList = animalsList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return animalsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_data,null);

            TextView author = view.findViewById(R.id.tvauthor);
            TextView width = view.findViewById(R.id.tvwidth);
            TextView height = view.findViewById(R.id.tvheight);
            ImageView image = view.findViewById(R.id.img);

            author.setText(animalsList.get(position).getAuthor());
            width.setText(animalsList.get(position).getWidth());
            height.setText(animalsList.get(position).getHeight());

            Glide.with(context).load(animalsList.get(position).getDownloadUrl()).into(image);

            return view;
        }
    }
}