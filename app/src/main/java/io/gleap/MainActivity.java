package io.gleap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Gleap.getInstance().startFeedbackFlow();
                } catch (GleapNotInitialisedException e) {
                    e.printStackTrace();
                }
            }
        });
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new GleapOkHttpInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("https://6170243923781c00172898a1.mockapi.io/users")
                .header("User-Agent", "OkHttp Example")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                response.body().close();
            }
        });

        final String json = "{\"id\":1,\"name\":\"John\"}";

        final RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request2 = new Request.Builder()
                .url("https://www.google.com/users/multipart")
                .post(body)
                .build();

        client.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
    }
}