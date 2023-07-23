package edu.huflit.tryhardmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

import edu.huflit.tryhardmode.R;
import edu.huflit.tryhardmode.Voucher.Voucher;

public class infoVoucherActivity extends AppCompatActivity {
    private ImageView ivVoucherImage;
    private TextView tvVoucherName, tvDiscountPrice, tvPrice, tvMota;
    private Button btBuy, btAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_voucher);
        ivVoucherImage = findViewById(R.id.ivVoucherImage);

        tvVoucherName = findViewById(R.id.tvIFVoucherName);
        tvDiscountPrice = findViewById(R.id.tvIFDiscountPrice);
        tvPrice = findViewById(R.id.tvIFPrice);
        tvMota = findViewById(R.id.tvIFMota);
        btBuy = findViewById(R.id.btMuaHang);
        btAddToCart = findViewById(R.id.btThemVaoGioHang);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("voucher")) {
            Voucher voucher  = (Voucher) intent.getSerializableExtra("voucher");
            if (voucher != null) {
                // Display the voucher details
//                Glide.with(this).load(voucher.getImgVoucher()).into(ivVoucherImage);
                tvVoucherName.setText(voucher.getVoucherName());
                tvDiscountPrice.setText(String.format(Locale.getDefault(), "%.2f", voucher.getDiscountPrice()));
                tvPrice.setPaintFlags(tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvPrice.setText(String.format(Locale.getDefault(), "%.2f", voucher.getPrice()));
                tvMota.setText(voucher.getMoTa());
            }
        }
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}