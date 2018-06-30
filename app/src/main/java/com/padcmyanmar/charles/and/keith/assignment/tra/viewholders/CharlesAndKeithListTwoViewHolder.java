package com.padcmyanmar.charles.and.keith.assignment.tra.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.charles.and.keith.assignment.tra.R;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.delegates.CharlesAndKeithShowItemsDelegate;
import com.padcmyanmar.charles.and.keith.assignment.tra.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharlesAndKeithListTwoViewHolder extends RecyclerView.ViewHolder {

    private CharlesAndKeithShowItemsDelegate mcharlesAndKeithShowItemsDelegate;
    private NewProductsVO mNewProducts;

    @BindView(R.id.iv_shoe_show_one)
    ImageView ivShoeShowOne;

    @BindView(R.id.tv_shoe_name)
    TextView tvShoeName;

    @BindView(R.id.iv_shoe_show_two)
    ImageView ivShoeShowTwo;

    @BindView(R.id.tv_shoe_name1)
    TextView tvShoeName1;

    public CharlesAndKeithListTwoViewHolder(View itemView,
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

    public void setNewProductsDataTwo(NewProductsVO newProducts) {

        mNewProducts = newProducts;
        tvShoeName.setText(newProducts.getProductTitle());

        GlideApp.with(ivShoeShowOne.getContext())
                .load(newProducts.getProductImage())
                .into(ivShoeShowOne);

        tvShoeName1.setText(newProducts.getProductTitle());

        GlideApp.with(ivShoeShowTwo.getContext())
                .load(newProducts.getProductImage())
                .into(ivShoeShowTwo);

      /* for(int i=0;i<newProducts.getProductImage().length();i++){
           Glide.with(ivShoeShowOne.getContext())
                   .load(newProducts.getProductImage().charAt(i))
                   .into(ivShoeShowOne);

           i = i+1;

           Glide.with(ivShoeShowTwo.getContext())
                   .load(newProducts.getProductImage().charAt(i))
                   .into(ivShoeShowTwo);

        }*/

    }
}
