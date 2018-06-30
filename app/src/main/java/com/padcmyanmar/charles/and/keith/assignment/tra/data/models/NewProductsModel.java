package com.padcmyanmar.charles.and.keith.assignment.tra.data.models;

import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.HttpUrlConnectionDataAgentImpl;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.NewProductsDataAgent;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.OkHttpDataAgentImpl;
import com.padcmyanmar.charles.and.keith.assignment.tra.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class NewProductsModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";


    private static NewProductsModel objInstance;

    private NewProductsDataAgent mNewProductsDataAgent;

    private int mPage ;

    private Map<Integer, NewProductsVO> mNewProductsMap;


    private NewProductsModel() {


     //   mNewProductsDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
     //     mNewProductsDataAgent = OkHttpDataAgentImpl.getInstance();
       mNewProductsDataAgent = RetrofitDataAgentImpl.getObjInstance();
        mPage = 1;

        mNewProductsMap = new HashMap<>();
        EventBus.getDefault().register(this);

    }

    public static NewProductsModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new NewProductsModel();
        }
        return objInstance;
    }

    public void loadNewProductList(){
        mNewProductsDataAgent.loadNewProductsList(DUMMY_ACCESS_TOKEN , mPage);

    }

    public NewProductsVO getNewProductsById(int productsId){
        return mNewProductsMap.get(productsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){

        for(NewProductsVO newProducts : event.getNewProductsList()){
            mNewProductsMap.put(newProducts.getProductId(), newProducts);
        }
    }
}
