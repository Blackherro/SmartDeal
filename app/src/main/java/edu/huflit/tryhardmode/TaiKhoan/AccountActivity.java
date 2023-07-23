package edu.huflit.tryhardmode.TaiKhoan;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.huflit.tryhardmode.R;

public class AccountActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView info;
    KhachHangDatabase khachHangDatabase;
    DBHelper dbHelper;

    TextView tvten, tvemail;


    SharedPreferences sharedPreferences;
    LinearLayout layoutAdmin;

    public static final String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtincanhan);

        khachHangDatabase = new KhachHangDatabase(AccountActivity.this);
        tvten = findViewById(R.id.tvten);
        tvemail = findViewById(R.id.tvemail);
            sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
            String email = sharedPreferences.getString(KEY_EMAIL, null);
            KhachHang khachHang = khachHangDatabase.GetUserData(email);
            tvten.setText(khachHang.get_ten());
            tvemail.setText(email);

    }

    public void onclick(View view) {
        startActivity(new Intent(getApplicationContext(),DangNhapActivity.class));
    }
}

