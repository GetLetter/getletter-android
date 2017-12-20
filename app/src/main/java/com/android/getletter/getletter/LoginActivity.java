package com.android.getletter.getletter;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.getletter.getletter.response.LoginResponse;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import butterknife.ButterKnife;

/**
 * Created by Thomas on 19/12/2017.
 */

public class LoginActivity extends AppCompatActivity{
    private TextView logo, punchline, conditions;
    private Button btnLogin;
    private RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the view
        setContentView(R.layout.activity_login);

        logo = (TextView) findViewById(R.id.logo);
        punchline = (TextView) findViewById(R.id.punchline);
        conditions = (TextView) findViewById(R.id.conditions);
        btnLogin = (Button) findViewById(R.id.btn_login);

        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(LoginActivity.this);  // requestQueue != null

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseRequest<LoginResponse> request = new BaseRequest.Builder<>(Request.Method.GET, "https://randomuser.me/api?limit=1", LoginResponse.class)
                        .listener(new RequestListener<LoginResponse>() {
                            @Override
                            public void onSuccess(Request request, NetworkResponse response, LoginResponse dummyResponse) {
                                Log.d("YOURAPP", "" + dummyResponse.getResults());
                            }

                            @Override
                            public void onFailure(Request request, NetworkResponse response, VolleyError volleyError) {
                                Log.d("YOURAPP", "Dummy error");
                            }
                        }).build();
                requestQueue.add(request);


            }
        });
    }

}
