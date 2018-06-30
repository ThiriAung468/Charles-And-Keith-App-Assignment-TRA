package com.padcmyanmar.charles.and.keith.assignment.tra.data.vos;

import com.google.gson.annotations.SerializedName;

public class NewProductsVO {

    @SerializedName("product-id")
    private int productId;

    @SerializedName("product-image")
    private String productImage;

    @SerializedName("product-title")
    private String productTitle;

    public int getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }


}
