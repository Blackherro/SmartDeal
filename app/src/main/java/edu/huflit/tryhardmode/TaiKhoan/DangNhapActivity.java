package edu.huflit.tryhardmode.TaiKhoan;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.huflit.tryhardmode.MainActivity;
import edu.huflit.tryhardmode.R;

public class DangNhapActivity extends AppCompatActivity {
    EditText mEdtEmail;
    EditText mEdtPassword;
    Button btnDangNhap;
    TextView tvDangKy;

    KhachHangDatabase khdb ;
    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static SharedPreferences sharedPreferences;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_activity);

        mEdtEmail=(EditText) findViewById(R.id.Email);
        mEdtPassword=(EditText) findViewById(R.id.MK);
        btnDangNhap=(Button) findViewById(R.id.dangnhap);
        tvDangKy=(TextView) findViewById(R.id.dangky);
        khdb=new KhachHangDatabase(DangNhapActivity.this);
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(i);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEdtEmail.getText().toString();
                String password = mEdtPassword.getText().toString();

                if(khdb.Login(email, password)){
                    CheckCrededentials();
                    LaythongtinUser();

                    sharedPreferences = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(KEY_EMAIL,email);
                    editor.apply();
                    Intent i = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void CheckCrededentials(){
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();

        if(email.isEmpty() || !email.contains("@")) {
            showError(mEdtEmail, "Your email is not valid!");
        }

        if(password.isEmpty() || password.length() < 8)
            showError(mEdtPassword, "Your password should have more than 8 letters!");
    }
    @SuppressLint("Range")
    public void LaythongtinUser(){
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        Cursor cursor = khdb.GetUserLogin(email, password);

        String ten = null;
        String sdt = null;
        while(cursor == null) {
            ten = cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN));
            sdt = cursor.getString(cursor.getColumnIndex(DBHelper.COT_SDT));
        }

    }
    private void showError(EditText mEdt, String s){
        mEdt.setError(s);
    }

}
