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
import com.padcmyanmar.charles.and.keith.assignment.tra.adapters.CharlesAndKeithListTwoAdapter;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.models.NewProductsModel;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.delegates.CharlesAndKeithShowItemsDelegate;
import com.padcmyanmar.charles.and.keith.assignment.tra.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CharlesAndKeithShowTwoItemsActivity extends BaseActivity implements CharlesAndKeithShowItemsDelegate {
    private CharlesAndKeithListTwoAdapter mcharlesAndKeithListTwoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charles_and_keith_show_two_items);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvListTwo = findViewById(R.id.rv_charles_and_keith_list_two);
        mcharlesAndKeithListTwoAdapter = new CharlesAndKeithListTwoAdapter(this);
        rvListTwo.setAdapter(mcharlesAndKeithListTwoAdapter);
        rvListTwo.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        ImageView ivshowOneItem = findViewById(R.id.iv_pagination_1);
        ivshowOneItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CharlesAndKeithListActivity.class);
                startActivity(intent);
            }

        });

        NewProductsModel.getObjInstance().loadNewProductList();
    }


    @Override
    public void onTapShowItems(NewProductsVO newProducts) {
        Intent intent = new Intent(getApplicationContext(),CharlesAndKeithDetailsListActivity.class);
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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){
        Log.d("onSuccessGetNewProducts","onSuccessGetNewProducts : "+ event.getNewProductsList().size());

        mcharlesAndKeithListTwoAdapter.setNewProductsListTwo(event.getNewProductsList());
    }
}

