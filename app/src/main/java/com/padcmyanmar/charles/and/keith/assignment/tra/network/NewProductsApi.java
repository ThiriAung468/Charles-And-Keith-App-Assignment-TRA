package com.padcmyanmar.charles.and.keith.assignment.tra.network;

import com.padcmyanmar.charles.and.keith.assignment.tra.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewProductsApi {
    @FormUrlEncoded
    @POST(NewProductsConstants.GET_NEW_PRODUCTS)
    Call<GetNewProductsResponse> loadNewProductsList(
            @Field(NewProductsConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(NewProductsConstants.PARAM_PAGE) int page);
}
