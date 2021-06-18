package com.example.ecommerceapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ImageViewHolder> {

    private Context pContext;
    private List<Category> mCategory;

    public CategoryAdapter(Context pContext, List<Category> mCategory) {
        this.pContext = pContext;
        this.mCategory = mCategory;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(pContext).inflate(R.layout.category_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Category categoryCur = mCategory.get(position);
        holder.cattitle.setText(categoryCur.getCatname());
        holder.cattitle.setBackgroundColor(Color.parseColor(categoryCur.getCattitlebg()));
        holder.iconWrapper.setBackgroundColor(Color.parseColor(categoryCur.getCatbg()));
        Picasso.get().load(categoryCur.getCaticon()).placeholder(R.drawable.ic_launcher_background).fit().centerCrop().into(holder.imgicon);
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView cattitle;
        public ImageView imgicon;
        public LinearLayout iconWrapper;

        public ImageViewHolder(@NonNull View itemView){
            super(itemView);
            cattitle = itemView.findViewById(R.id.catName);
            imgicon = itemView.findViewById(R.id.catIcon);
            iconWrapper = itemView.findViewById(R.id.iconWrapper);
        }
    }
}
