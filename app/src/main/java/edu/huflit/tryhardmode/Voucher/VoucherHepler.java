package edu.huflit.tryhardmode.Voucher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VoucherHepler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "voucher.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names
    public static final String TABLE_VOUCHER = "voucher";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MA_VOUCHER = "ma_voucher";
    public static final String COLUMN_VOUCHER_NAME = "voucher_name";
    public static final String COLUMN_DISCOUNT_PRICE= "discount_price";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_MOTA = "mo_ta";
    public static final String COLUMN_VOUCHER_IMAGE = "voucher_image";

    // Create table query
    private static final String CREATE_VOUCHER_TABLE = "CREATE TABLE " + TABLE_VOUCHER + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MA_VOUCHER + " TEXT,"
            + COLUMN_VOUCHER_NAME + " TEXT,"
            + COLUMN_DISCOUNT_PRICE + " INTEGER,"
            + COLUMN_PRICE + " INTEGER,"
            + COLUMN_MOTA + " TEXT"
            + ")";

    public VoucherHepler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VOUCHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOUCHER);
        onCreate(db);
    }
}
