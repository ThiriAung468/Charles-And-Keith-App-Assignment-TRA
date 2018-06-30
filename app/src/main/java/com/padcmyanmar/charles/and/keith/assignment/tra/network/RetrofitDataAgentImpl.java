package com.padcmyanmar.charles.and.keith.assignment.tra.network;

import com.padcmyanmar.charles.and.keith.assignment.tra.events.ApiErrorEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewProductsDataAgent {

    private static RetrofitDataAgentImpl sobjInstance;
    private NewProductsApi mTheApi;

    private RetrofitDataAgentImpl() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewProductsConstants.API_BASE)

                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(NewProductsApi.class);

    }

    public static RetrofitDataAgentImpl getObjInstance() {
        if (sobjInstance == null) {
            sobjInstance = new RetrofitDataAgentImpl();
        }
        return sobjInstance;
    }

    @Override
    public void loadNewProductsList(String accessToken, int page) {
        Call<GetNewProductsResponse> loadTalksCall = mTheApi.loadNewProductsList(accessToken,page);

        loadTalksCall.enqueue(new Callback<GetNewProductsResponse>() {
            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {

                GetNewProductsResponse newProductsResponse =  response.body();
                if(newProductsResponse != null && newProductsResponse.isResponseOK()){
                    SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getNewProducts());
                    EventBus.getDefault().post(event);
                }
                else {
                    if(newProductsResponse == null){
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    }else {
                        ApiErrorEvent event = new ApiErrorEvent(newProductsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });

    }
}
