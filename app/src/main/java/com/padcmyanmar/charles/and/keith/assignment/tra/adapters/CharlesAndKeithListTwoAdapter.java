package com.padcmyanmar.charles.and.keith.assignment.tra.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.charles.and.keith.assignment.tra.R;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.delegates.CharlesAndKeithShowItemsDelegate;
import com.padcmyanmar.charles.and.keith.assignment.tra.viewholders.CharlesAndKeithListTwoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CharlesAndKeithListTwoAdapter extends RecyclerView.Adapter<CharlesAndKeithListTwoViewHolder> {
 private CharlesAndKeithShowItemsDelegate mcharlesAndKeithShowItemsDelegate;
    private List<NewProductsVO> mNewProductsList;

 public CharlesAndKeithListTwoAdapter(CharlesAndKeithShowItemsDelegate charlesAndKeithShowItemsDelegate){
     mcharlesAndKeithShowItemsDelegate = charlesAndKeithShowItemsDelegate;
     mNewProductsList = new ArrayList<>();
 }


    @NonNull
    @Override
    public CharlesAndKeithListTwoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_charles_and_keith_list_two,
                parent, false);
        return new CharlesAndKeithListTwoViewHolder(view,mcharlesAndKeithShowItemsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull CharlesAndKeithListTwoViewHolder holder, int position) {

        holder.setNewProductsDataTwo(mNewProductsList.get(position));
    }

    @Override
    public int getItemCount() {
          return mNewProductsList.size();
    }
    public void setNewProductsListTwo(List<NewProductsVO> newProductsList) {
        mNewProductsList = newProductsList;
        notifyDataSetChanged();
    }
}
