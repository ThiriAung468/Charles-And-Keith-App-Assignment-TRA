package com.padcmyanmar.charles.and.keith.assignment.tra.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;

import java.util.List;

public class GetNewProductsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("newProducts")
    private List<NewProductsVO> newProducts;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewProductsVO> getNewProducts() {
        return newProducts;
    }

    public boolean isResponseOK() {
        return (code == 200 && newProducts != null);
    }
}
