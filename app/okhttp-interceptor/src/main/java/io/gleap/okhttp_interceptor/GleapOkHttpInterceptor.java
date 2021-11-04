package io.gleap.okhttp_interceptor;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import io.gleap.Gleap;
import io.gleap.RequestType;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

public class GleapOkHttpInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Buffer buffer = new Buffer();

        JSONObject resquestObj = new JSONObject();
        try {
            if(request.body() != null) {
                request.body().writeTo(buffer);
                String responseBody = buffer.readUtf8();

                if(isJSONValid(responseBody)){
                    resquestObj = new JSONObject(responseBody);
                }else {
                    resquestObj.put("data",buffer.readUtf8());
                }

            }
        } catch (Exception e) {

        }
        Date start = new Date();
        Response response = chain.proceed(request);
        Date end = new Date();

        long duration = start.getTime() - end.getTime();
        JSONObject responseObj = new JSONObject();
        String responseString = response.body().string();
        try {
            if(isJSONValid(responseString)){
                responseObj = new JSONObject(responseString);
            }else if(isJSONArrayValid(responseString)) {
                responseObj.put("data", new JSONArray(responseString));
            }else {
                responseObj.put("data", responseString);
            }
        } catch (JSONException e) {
        }
        RequestType requestType = RequestType.GET;
        switch (request.method()) {
            case "POST":
                requestType = RequestType.POST;
                break;
            case "PUT":
                requestType = RequestType.PUT;
                break;
            case "DELETE":
                requestType = RequestType.DELETE;
                break;
            case "PATCH":
                requestType = RequestType.PATCH;
                break;
        }
        Gleap.getInstance().logNetwork(request.url().toString(),  requestType,response.code(), (int)duration, resquestObj, responseObj);
        return response;
    }

    public boolean isJSONValid(String jsonInString ) {
        try {
            JSONObject jsonObject = new JSONObject(jsonInString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public boolean isJSONArrayValid(String jsonInString ) {
        try {
            JSONArray jsonObject = new JSONArray(jsonInString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
