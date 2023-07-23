package edu.huflit.tryhardmode.TaiKhoan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class KhachHangDatabase {
    SQLiteDatabase db;
    DBHelper helper;

    public KhachHangDatabase(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public Cursor GetDataUser(){
        String[] cot  = {
                DBHelper.COT_PASS,
                DBHelper.COT_EMAIL,
                DBHelper.COT_USERID,
        };

        Cursor cursor = null;
        cursor = db.query(DBHelper.TEN_BANG_USER, cot, null, null, null, null, DBHelper.COT_USERID + " DESC");
        return cursor;
    }

    public Cursor GetUserLogin(String email, String password){
        String[] cols = {DBHelper.COT_EMAIL, DBHelper.COT_PASS};

        String where = "_email = ? and _pass = ?";
        String[] whereArgs = new String[] {email, password};
        Cursor cursor = null;
        cursor = db.query(DBHelper.TEN_BANG_USER, cols,where,whereArgs, null, null, null, null);
        return cursor;
    }

    public long them(KhachHang user){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_PASS, user.get_password());
        values.put(DBHelper.COT_EMAIL, user.get_email());

        return db.insert(DBHelper.TEN_BANG_USER, null, values);
    }

//    public long xoa(KhachHang user){
//        return db.delete(DBHelper.TEN_BANG_USER, DBHelper.COT_USERID + " = " + "'" + user.get_id() + "'", null );
//    }

//    public long sua(KhachHang user){
//        ContentValues values = new ContentValues();
//        values.put(DBHelper.COT_TEN, user.get_ten());
//        values.put(DBHelper.COT_USERNAME, user.get_username());
//        values.put(DBHelper.COT_PASS, user.get_password());
//        values.put(DBHelper.COT_EMAIL, user.get_email());
//        values.put(DBHelper.COT_SDT, user.get_sdt());
//
//        return db.update(DBHelper.TEN_BANG_USER, values, DBHelper.COT_USERID + " = " + user.get_id(), null);
//    }

    public KhachHang GetUserData(String email){
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query(helper.TEN_BANG_USER, null, helper.COT_EMAIL + " = ?", new String[]{email}, null, null, null);
        KhachHang khachHang = new KhachHang();
        while (cursor.moveToNext()){
            khachHang.set_email(cursor.getString(1));
            khachHang.set_password(cursor.getString(7));
        }
        return khachHang;

    }

    @SuppressLint("Recycle")
    public boolean Login(String email, String password){
        String[] cols = {DBHelper.COT_EMAIL, DBHelper.COT_PASS, DBHelper.COT_USERID};

        String where = "_email = ? and _pass = ?";
        String[] whereArgs = new String[] {email, password};
        Cursor cursor = null;

        cursor = db.query(DBHelper.TEN_BANG_USER, cols, where, whereArgs, null, null, null);
        if(cursor.getCount() == 0){
            return false;
        }
        return true;
    }
}
