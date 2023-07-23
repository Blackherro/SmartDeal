package edu.huflit.tryhardmode;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.tryhardmode.TaiKhoan.AccountActivity;
import edu.huflit.tryhardmode.TaiKhoan.DangNhapActivity;
import edu.huflit.tryhardmode.Voucher.Untils;
import edu.huflit.tryhardmode.Voucher.Voucher;
import edu.huflit.tryhardmode.Voucher.VoucherAdapter;

public class MainActivity extends AppCompatActivity implements VoucherAdapter.Listener  {
    private RecyclerView rvVoucherMain;
    private VoucherAdapter voucherAdapter;
    SharedPreferences sharedPreferences;
    BottomNavigationView bottomNavigationView;
    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    private List<Voucher> vouchers;
    private VoucherDataSource voucherDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvVoucherMain = findViewById(R.id.rvVoucherMain);
        rvVoucherMain.setLayoutManager(new LinearLayoutManager(this));

        vouchers = new ArrayList<>();
        voucherAdapter = new VoucherAdapter(vouchers,this);
        rvVoucherMain.setAdapter(voucherAdapter);

        //khoi tao va mo d
        voucherDataSource = new VoucherDataSource(this);
        voucherDataSource.open();

        if(voucherDataSource.getAllVouchers().isEmpty()) {
            Voucher voucher1 = new Voucher(1, "Voucher01", "buffe hai san", 150000.0, 500000, "- Ya Restaurant tọa lạc trên đường Nguyễn Đình Chiểu, quận 1 là địa chỉ chuyên phục vụ các món ăn Nhật Bản và Việt Nam.\n" +
                    "- Thực đơn đa dạng với các loại sushi nhiều màu sắc, sashimi tươi ngon, bánh xèo Nhật, cơm chiên, lẩu… và đặc biệt là bánh takoyaki thơm ngon, hấp dẫn.\n" +
                    "- Không gian nhà hàng được thiết kế theo phong cách Nhật Bản ấm cúng, sang trọng tạo cảm giác thoải mái cho thực khách.");
            Voucher voucher2 = new Voucher(2, "Voucher02", "buff ga", 100000.0, 5000000, "- Ya Restaurant tọa lạc trên đường Nguyễn Đình Chiểu, quận 1 là địa chỉ chuyên phục vụ các món ăn Nhật Bản và Việt Nam.\n" +
                    "- Thực đơn đa dạng với các loại sushi nhiều màu sắc, sashimi tươi ngon, bánh xèo Nhật, cơm chiên, lẩu… và đặc biệt là bánh takoyaki thơm ngon, hấp dẫn.\n" +
                    "- Không gian nhà hàng được thiết kế theo phong cách Nhật Bản ấm cúng, sang trọng tạo cảm giác thoải mái cho thực khách.");
            voucherDataSource.insertVoucher(voucher1);
            voucherDataSource.insertVoucher(voucher2);
        }
        loadVouchersFromDatabase();

        bottomNavigationView=findViewById(R.id.bottomnavi);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.category:

                        return true;
                    case R.id.cart:

                        return true;
                    case R.id.acc:
                        while (sharedPreferences==null) {
                            startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
                            sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
                        }
//                        sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadVouchersFromDatabase() {
        vouchers.clear();
        vouchers.addAll(voucherDataSource.getAllVouchers());
        voucherAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        voucherDataSource.close();
    }
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            }
    );
    @Override
    public void setOnInfoClick(Voucher voucher) {
        Intent intent = new Intent(this,infoVoucherActivity.class);
        intent.putExtra("voucher", voucher);
        startActivity(intent);
    }
}