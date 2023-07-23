package edu.huflit.tryhardmode.Voucher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.huflit.tryhardmode.R;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherVH> {
    private List<Voucher> vouchers;

    Listener listener;

    public VoucherAdapter(List<Voucher> vouchers, Listener listener) {
        this.vouchers = vouchers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VoucherVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_voucher_row, parent, false);
        return new VoucherVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherVH holder, @SuppressLint("RecyclerView") int position) {

        Voucher voucher = vouchers.get(position);

        holder.tvVoucherName.setText(voucher.getVoucherName());
        holder.tvDiscountPrice.setText(String.valueOf(voucher.getDiscountPrice()+ "đ"));

       holder.tvPrice.setText(String.valueOf(voucher.getPrice()+"đ"));
//        Glide.with(holder.itemView.getContext())
//                .load(voucher.getImgVoucher())
//                .into(holder.ivVoucherImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.setOnInfoClick(vouchers.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }

    static class VoucherVH extends RecyclerView.ViewHolder {
        TextView tvVoucherName, tvDiscountPrice, tvPrice, tvMota;
        ImageView ivVoucherImage;

        public VoucherVH(View itemView) {
            super(itemView);
            tvVoucherName = itemView.findViewById(R.id.tvVoucherName);
            tvDiscountPrice = itemView.findViewById(R.id.tvDiscountPrice);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvMota = itemView.findViewById(R.id.tvMota);
            ivVoucherImage = itemView.findViewById(R.id.ivVoucherImage);
            tvPrice.setPaintFlags(tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
    }
    public interface Listener {
        void setOnInfoClick(Voucher voucher);
    }
}
