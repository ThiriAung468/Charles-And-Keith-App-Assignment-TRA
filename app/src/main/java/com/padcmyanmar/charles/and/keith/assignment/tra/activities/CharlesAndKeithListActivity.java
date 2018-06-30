package com.padcmyanmar.charles.and.keith.assignment.tra.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.padcmyanmar.charles.and.keith.assignment.tra.R;
import com.padcmyanmar.charles.and.keith.assignment.tra.adapters.CharlesAndKeithListOneAdapter;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.models.NewProductsModel;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.delegates.CharlesAndKeithShowItemsDelegate;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CharlesAndKeithListActivity extends BaseActivity implements CharlesAndKeithShowItemsDelegate {

    private CharlesAndKeithListOneAdapter mcharlesAndKeithListOneAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charles_and_keith_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvListOne = findViewById(R.id.rv_charles_and_keith_list_one);
        mcharlesAndKeithListOneAdapter = new CharlesAndKeithListOneAdapter(this);
        rvListOne.setAdapter(mcharlesAndKeithListOneAdapter);
        rvListOne.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        ImageView ivShowTwoItem = findViewById(R.id.iv_pagination_2);
        ivShowTwoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CharlesAndKeithShowTwoItemsActivity.class);
                startActivity(intent);
            }
        });

        NewProductsModel.getObjInstance().loadNewProductList();

    }

    @Override
    public void onTapShowItems(NewProductsVO newProducts) {
        Intent intent = new Intent(getApplicationContext(), CharlesAndKeithDetailsListActivity.class);
        intent.putExtra(NewProductsConstants.PRODUCT_ID, newProducts.getProductId() );
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts : " + event.getNewProductsList().size());

        mcharlesAndKeithListOneAdapter.setNewProductsList(event.getNewProductsList());
    }
}
