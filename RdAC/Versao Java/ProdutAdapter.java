import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProdutAdapter extends RecyclerView.Adapter<ProdutAdapter.ProdutViewHolder> {
    private ArrayList<UserProdAz> userProdAzs;

    public static class ProdutViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ProdutViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imgPhotoVerde);
            mTextView1 = itemView.findViewById(R.id.tv_nomeprodVerde);
            mTextView2 = itemView.findViewById(R.id.tv_origemVerde);
            mTextView3 = itemView.findViewById(R.id.tv_areaVerde);
        }
    }

    public ProdutAdapter(ArrayList<UserProdAz> userProdAz) {
        userProdAzs = userProdAz;
    }

    @Override
    public ProdutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_green, parent, false);
        ProdutViewHolder pvh = new ProdutViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutAdapter.ProdutViewHolder holder, int position) {
        UserProdAz userProdAz = userProdAzs.get(position);

        // holder.mImageView.setImageResource(currentItem.getProfileUrl());
        holder.mTextView1.setText(userProdAz.getUsername());
        holder.mTextView2.setText(userProdAz.getLocalidade());
        holder.mTextView3.setText(userProdAz.getZone());
        holder.mImageView = holder.itemView.findViewById(R.id.imgPhotoVerde);

        Picasso.get()
                .load(userProdAz.getProfileUrl())
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return userProdAzs.size();
    }
}