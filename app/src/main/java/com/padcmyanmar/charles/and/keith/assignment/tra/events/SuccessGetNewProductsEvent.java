package com.padcmyanmar.charles.and.keith.assignment.tra.events;

import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;

import java.util.List;

public class SuccessGetNewProductsEvent {

    private List<NewProductsVO> newProductsList;

    public SuccessGetNewProductsEvent(List<NewProductsVO> newProductsList) {
        this.newProductsList = newProductsList;
    }

    public List<NewProductsVO> getNewProductsList() {
        return newProductsList;
    }
}
