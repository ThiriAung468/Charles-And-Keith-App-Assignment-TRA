package com.padcmyanmar.charles.and.keith.assignment.tra.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padcmyanmar.charles.and.keith.assignment.tra.R;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.delegates.CharlesAndKeithShowItemsDelegate;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.GlideApp;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.NewProductsGlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharlesAndKeithListOneViewHolder extends RecyclerView.ViewHolder {

    private CharlesAndKeithShowItemsDelegate mcharlesAndKeithShowItemsDelegate;
    private NewProductsVO mNewProducts;

    @BindView(R.id.iv_shoe_show_one)
    ImageView ivShoeShowOne;

    @BindView(R.id.tv_shoe_name)
    TextView tvShoeName;

    public CharlesAndKeithListOneViewHolder(View itemView,
                                            CharlesAndKeithShowItemsDelegate charlesAndKeithShowItemsDelegate) {

        super(itemView);

        ButterKnife.bind(this,itemView);

        mcharlesAndKeithShowItemsDelegate = charlesAndKeithShowItemsDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcharlesAndKeithShowItemsDelegate.onTapShowItems(mNewProducts);
            }
        });
    }

    public void setNewProductsData(NewProductsVO newProducts){

        mNewProducts = newProducts;

        tvShoeName.setText(newProducts.getProductTitle());

        GlideApp.with(ivShoeShowOne.getContext())
                .load(newProducts.getProductImage())
                .placeholder(R.drawable.img_news_placeholder)
                .error(R.drawable.error_img)
                .into(ivShoeShowOne);

    }
}
