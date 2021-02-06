package com.example.mvvmex04.views.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmex04.BR;
import com.example.mvvmex04.R;
import com.example.mvvmex04.databinding.CardBinding;
import com.example.mvvmex04.models.model.VentilationTimeModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<VentilationTimeModel> arrayList;

    public RecyclerViewAdapter(Activity context, ArrayList<VentilationTimeModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardBinding cardBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.card, parent, false);
        return new RecyclerViewViewHolder(cardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VentilationTimeModel ventilationTimeModel = arrayList.get(0);
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.bind(ventilationTimeModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        private CardBinding cardBinding;

        public RecyclerViewViewHolder(@NonNull CardBinding cardBinding) {
            super(cardBinding.getRoot());
            this.cardBinding = cardBinding;
        }

        public void bind(VentilationTimeModel ventilationTimeModel) {
            this.cardBinding.setVariable(BR.mainViewModel, ventilationTimeModel);
            cardBinding.executePendingBindings();
        }

        public CardBinding getCardBinding() {
            return cardBinding;
        }
    }
}
