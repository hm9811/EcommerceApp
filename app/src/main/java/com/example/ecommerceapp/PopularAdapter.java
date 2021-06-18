package com.example.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Popular> mPopulars;

    public PopularAdapter(Context context, List<Popular> populars){
        mContext = context;
        mPopulars = populars;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popular_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Popular popularCur = mPopulars.get(position);
        holder.prod_name.setText(popularCur.getProduct_title());
        holder.prod_price.setText(popularCur.getProduct_price());
        Picasso.get().load(popularCur.getProduct_image()).placeholder(R.drawable.ic_launcher_background).fit().centerCrop().into( holder.prod_img);
    }

    @Override
    public int getItemCount() {
        int i;
        if(mPopulars != null && !mPopulars.isEmpty()){
            i = mPopulars.size();
        }
        else{
            i = 0;
        }
        return i;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView prod_name, prod_price;
        public ImageView prod_img;

        public ImageViewHolder(@NonNull View itemView){
            super(itemView);
            prod_name = itemView.findViewById(R.id.prodName);
            prod_price = (TextView) itemView.findViewById(R.id.prodPrice);
            prod_img = itemView.findViewById(R.id.prodImage);
        }
    }
}
