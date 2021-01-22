package com.vignesh.employeedetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.vignesh.employeedetails.Interface.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Userdetails extends AppCompatActivity {

    private TextView empview, emailview, addressview, compview,phoneview;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        empview = (TextView) findViewById(R.id.empView);
        emailview = (TextView) findViewById(R.id.emailView1);
        addressview = (TextView) findViewById(R.id.addressview);
        compview = (TextView) findViewById(R.id.compview);
        phoneview = (TextView) findViewById(R.id.phoneview);
        Intent mIntent = getIntent();
        ID = mIntent.getIntExtra("id", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call call = api.getUserOne(ID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("TAG", "response: " + new Gson().toJson(response.body()));
                try {
                    JSONArray jsonArray = new JSONArray(new Gson().toJson(response.body()));
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    empview.setText("Employee Id: " + jsonObject.getInt("id") + "\n" + "NAME: " + jsonObject.getString("name"));
                    emailview.setText("Email: " + jsonObject.getString("email"));
                    addressview.setText("Address:\n" + jsonObject.getJSONObject("address").getString("street") + "\n" + jsonObject.getJSONObject("address").getString("suite") + "\n" + jsonObject.getJSONObject("address").getString("city") + " - " + jsonObject.getJSONObject("address").getString("zipcode"));
                    phoneview.setText("Phone Number: "+jsonObject.getString("phone"));
                    compview.setText("Company Details:\n Company Name: "+ jsonObject.getJSONObject("company").getString("name")+"\n Website: "+ jsonObject.getString("website"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });


    }
}