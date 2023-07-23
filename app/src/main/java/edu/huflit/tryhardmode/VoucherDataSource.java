package edu.huflit.tryhardmode;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.huflit.tryhardmode.Voucher.Untils;
import edu.huflit.tryhardmode.Voucher.Voucher;
import edu.huflit.tryhardmode.Voucher.VoucherHepler;

public class VoucherDataSource {
    private SQLiteDatabase database;
    private VoucherHepler dbHelper;
    private Context context;

    public VoucherDataSource(Context context) {
        this.context = context;
        dbHelper = new VoucherHepler(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertVoucher(Voucher voucher) {
        ContentValues values = new ContentValues();
        values.put(VoucherHepler.COLUMN_MA_VOUCHER, voucher.getMaVoucher());
        values.put(VoucherHepler.COLUMN_VOUCHER_NAME, voucher.getVoucherName());
        values.put(VoucherHepler.COLUMN_DISCOUNT_PRICE, voucher.getDiscountPrice());
        values.put(VoucherHepler.COLUMN_PRICE, voucher.getPrice());
        values.put(VoucherHepler.COLUMN_MOTA, voucher.getMoTa());
//        String imageBase64String = String.valueOf(Untils.decodeBase64ToImage(voucher.getImgVoucher()));
//        values.put(VoucherHepler.COLUMN_VOUCHER_IMAGE, imageBase64String);
//        values.put(VoucherHepler.COLUMN_VOUCHER_IMAGE, getBytesFromDrawable(Integer.parseInt(voucher.getImgVoucher())));

        database.insert(VoucherHepler.TABLE_VOUCHER, null, values);

    }
//    private byte[] getBytesFromDrawable(int drawableId) {
//        return Untils.getBytesFromDrawable(context, drawableId);
//    }



    public List<Voucher> getAllVouchers() {
        List<Voucher> voucherList = new ArrayList<>();
        Cursor cursor = database.query(VoucherHepler.TABLE_VOUCHER, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(VoucherHepler.COLUMN_ID));
                @SuppressLint("Range") String voucherCode = cursor.getString(cursor.getColumnIndex(VoucherHepler.COLUMN_MA_VOUCHER));
                @SuppressLint("Range") String voucherName = cursor.getString(cursor.getColumnIndex(VoucherHepler.COLUMN_VOUCHER_NAME));
                @SuppressLint("Range") double discountAmount = cursor.getDouble(cursor.getColumnIndex(VoucherHepler.COLUMN_DISCOUNT_PRICE));
                @SuppressLint("Range") double originalPrice = cursor.getDouble(cursor.getColumnIndex(VoucherHepler.COLUMN_PRICE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(VoucherHepler.COLUMN_MOTA));
//              @SuppressLint("Range") byte[] voucherImage = cursor.getBlob(cursor.getColumnIndex(VoucherHepler.COLUMN_VOUCHER_IMAGE));

//
                Voucher voucher = new Voucher(id, voucherCode, voucherName, discountAmount, originalPrice, description);
                voucherList.add(voucher);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return voucherList;
    }
}
