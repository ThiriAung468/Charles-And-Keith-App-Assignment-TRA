package com.padcmyanmar.charles.and.keith.assignment.tra.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.ApiErrorEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements NewProductsDataAgent {

    private static OkHttpDataAgentImpl sObjInstance;

    private OkHttpClient mHttpClient;

    private OkHttpDataAgentImpl() {
        mHttpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getInstance() {
        if (sObjInstance == null) {
            sObjInstance = new OkHttpDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadNewProductsList(final String accessToken, final int page) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                RequestBody formBody = new FormBody.Builder() //2.
                        .add(NewProductsConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(NewProductsConstants.PARAM_PAGE, String.valueOf(page))
                        .build();

                Request request = new Request.Builder() //3
                        .url(NewProductsConstants.API_BASE + NewProductsConstants.GET_NEW_PRODUCTS)
                        .post(formBody)
                        .build();

                try {
                    Response response = mHttpClient.newCall(request).execute(); //4.
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();

                        return responseString;
                    }
                } catch (IOException e) {
                    Log.e("loadTalksList", e.getMessage());

                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetNewProductsResponse newProductsResponse = gson.fromJson(responseString, GetNewProductsResponse.class);
                Log.d("onPostExecute","Talks List Size : "+newProductsResponse.getNewProducts().size());

                if(newProductsResponse.isResponseOK()){
                    SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getNewProducts());
                    EventBus.getDefault().post(event);
                }else{
                    ApiErrorEvent event = new ApiErrorEvent(newProductsResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }
        }.execute();

    }
}
