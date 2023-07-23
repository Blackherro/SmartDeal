package edu.huflit.tryhardmode.TaiKhoan;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.huflit.tryhardmode.R;

public class DangKyActivity extends AppCompatActivity {
EditText  mEdtConfirmPass,mEdtEmail,mEdtPassword;
    Button mBtnDK;
    DBHelper helper;
    ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
    public static KhachHangDatabase khdb;

    public  static final  String SHARE_NAME = "sharepre";
    public static final String KEY_EMAIL = "email";
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_activity);
        khdb = new KhachHangDatabase(DangKyActivity.this);
        mEdtEmail=findViewById(R.id.Email);
        mEdtConfirmPass=findViewById(R.id.MK);
        mEdtPassword=findViewById(R.id.nhaplaiMK);
        helper=new DBHelper(DangKyActivity.this);
        mBtnDK=findViewById(R.id.dangky);
        sharedPreferences = getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        mBtnDK.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    if(!CheckCededentials()){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, mEdtEmail.getText().toString());
        editor.apply();
        them(view);
        startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));
    }}
});
    }
    private boolean CheckCededentials(){

        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        String confirmPass = mEdtConfirmPass.getText().toString();
        int flag = 0;

        if(email.isEmpty() || !email.contains("@")) {

            showError(mEdtEmail, "Your email is not valid!");
            flag++;
        }

        if(password.isEmpty()) {
            showError(mEdtPassword, "Your password can't be empty");
            flag++;
        }
        else if(password.length() < 8) {
            showError(mEdtPassword, "Password should be more than 8 letters");
            flag++;
        }
        if(!confirmPass.matches(password)) {
            showError(mEdtConfirmPass, "Your password does not match");
            flag++;
        }
        if(confirmPass.isEmpty()) {
            showError(mEdtConfirmPass, "Please, confirm your password");
            flag++;
        }
        if(CheckEmailValid(email)){
            Toast.makeText(this, "Email da Ton Tai", Toast.LENGTH_SHORT).show();
            flag++;
        }
        return flag > 0;
    }
    public boolean CheckEmailValid(String email){
        SQLiteDatabase db=helper.getReadableDatabase();
//        email=mEdtEmail.getText().toString();
        Cursor cursor= db.query(DBHelper.TEN_BANG_USER,null,DBHelper.COT_EMAIL + " = ? " ,new String[]{email},null,null,null);
        while (cursor.moveToNext()){
            String emaildb = cursor.getString(1);
            if(email.equals(emaildb)){
                return true;
            }
        }
        return false;
    }
    @SuppressLint("Range")
    public void UpdateData(){
        if(khachHangs == null){
            khachHangs = new ArrayList<KhachHang>();
        } else{
            khachHangs.removeAll(khachHangs);
        }
        Cursor cursor = khdb.GetDataUser();
        if(cursor != null){
            while(cursor.moveToNext()){
                KhachHang khachHang = new KhachHang();
                khachHang.set_id(cursor.getLong(cursor.getColumnIndex(DBHelper.COT_USERID)));
                khachHang.set_email(cursor.getString(cursor.getColumnIndex(DBHelper.COT_EMAIL)));
                khachHang.set_password(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PASS)));
                khachHangs.add(khachHang);
            }
        }
    }
    public KhachHang GetDataUser(){
        String password = mEdtPassword.getText().toString();
        String email = mEdtEmail.getText().toString();
        KhachHang khachHang = new KhachHang();
        khachHang.set_password(password);
        khachHang.set_email(email);
        return khachHang;
    }
    public void them(View view){
        KhachHang kh = GetDataUser();
        if(kh != null){
            if(khdb.them(kh) != -1){
                khachHangs.add(kh);
                UpdateData();

            }
        }
    }
    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }
}
