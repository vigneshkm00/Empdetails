package com.vignesh.employeedetails;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.employeedetails.Adapter.RecyclerAdapter;
import com.vignesh.employeedetails.Interface.Api;
import com.vignesh.employeedetails.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    boolean connected = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        if (connected = true) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api api = retrofit.create(Api.class);
//        Call call = api.getUserOne(1);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.d("TAG", "response: "+new Gson().toJson(response.body()) );
//            }
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Log.d("error",t.getMessage());
//            }
//        });
            Call<List<User>> call = api.getUser();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> userList = response.body();

                    recyclerView.setAdapter(new RecyclerAdapter(MainActivity.this, userList));
                    for (User u : userList) {
//                    Log.d("id", String.valueOf(u.getId()));
                        Log.d("name", u.getName());
                        Log.d("email", u.getEmail());
//                    Log.d("address", String.valueOf(u.getAddress()));
//                    Log.d("phone",u.getPhone());
//                    Log.d("website",u.getWebsite());
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", t.getMessage());
                }
            });

        }
        else {
            Toast.makeText(getApplicationContext(),"NOT CONNECTED TO THE INTERNET",Toast.LENGTH_SHORT).show();
            Log.d("Not Internet","NOT CONNECTED");
        }

    }
}