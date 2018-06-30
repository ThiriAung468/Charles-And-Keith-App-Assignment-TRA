package com.padcmyanmar.charles.and.keith.assignment.tra.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.charles.and.keith.assignment.tra.R;
import com.padcmyanmar.charles.and.keith.assignment.tra.data.vos.NewProductsVO;
import com.padcmyanmar.charles.and.keith.assignment.tra.delegates.CharlesAndKeithShowItemsDelegate;
import com.padcmyanmar.charles.and.keith.assignment.tra.viewholders.CharlesAndKeithListOneViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CharlesAndKeithListOneAdapter extends RecyclerView.Adapter<CharlesAndKeithListOneViewHolder> {
    private CharlesAndKeithShowItemsDelegate mcharlesAndKeithShowItemsDelegate;
    private List<NewProductsVO> mNewProductsList;

    public CharlesAndKeithListOneAdapter(CharlesAndKeithShowItemsDelegate charlesAndKeithShowItemsDelegate){
        mcharlesAndKeithShowItemsDelegate = charlesAndKeithShowItemsDelegate;
        mNewProductsList = new ArrayList<>();
    }
    @NonNull
    @Override
    public CharlesAndKeithListOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_charles_and_keith_list_one,
                parent,false);
        return new CharlesAndKeithListOneViewHolder(view,mcharlesAndKeithShowItemsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull CharlesAndKeithListOneViewHolder holder, int position) {

        holder.setNewProductsData(mNewProductsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewProductsList.size();
    }

    public void setNewProductsList(List<NewProductsVO> newProductsList) {
        mNewProductsList = newProductsList;
        notifyDataSetChanged();
    }
}
