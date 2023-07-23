package edu.huflit.tryhardmode.TaiKhoan;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
private static final String TEN_DATABASE="BanVoucher.db";
    public static final String TEN_BANG_USER = "USER";

    public static final String COT_USERID = "_userid";
    public static final String COT_EMAIL = "_email";
    public static final String COT_TEN = "_ten";

    public static final String COT_DATE="_date";
    public static final String COT_SDT = "_sdt";

    public static final String COT_GIOITINH="_gioitinh";

    public static final String COT_DIACHI="_diachi";
    public static final String COT_PASS = "_pass";




    private static final String TAO_BANG_USER = ""
            + "create table " + TEN_BANG_USER + " ( "
            + COT_USERID + " integer primary key unique, "
            + COT_EMAIL + " text not null, "
            + COT_TEN + " text , "
            + COT_DATE + " text , "
            + COT_SDT + " text, "
            + COT_GIOITINH + " text , "
            + COT_DIACHI + " text , "
            + COT_PASS + " text not null );";

    public SQLiteDatabase openDBOption1() {
        File dbFile = context.getDatabasePath(TEN_DATABASE);

        if (!dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }
    Context context;
    public DBHelper(Context context) {
        super(context, TEN_DATABASE, null, 1);
    }
    private void copyDatabase(File dbFile) throws IOException {
        InputStream is = context.getAssets().open(TEN_DATABASE);
        OutputStream os = new FileOutputStream(dbFile);

        byte[] buffer = new byte[1024];
        while (is.read(buffer) > 0) {
            os.write(buffer);
        }

        os.flush();
        os.close();
        is.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void capnhatdulieu(String email,String name,String ngaysinh,String sdt,String gioitinh,String diachi,String pass ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COT_EMAIL,email);
        contentValues.put(COT_TEN,name);
        contentValues.put(COT_DATE,ngaysinh);
        contentValues.put(COT_SDT,sdt);
        contentValues.put(COT_GIOITINH,gioitinh);
        contentValues.put(COT_DIACHI,diachi);
        contentValues.put(COT_PASS,pass );
        db.update(TEN_BANG_USER,contentValues,COT_EMAIL + " = ? ",new String[]{email});
        db.close();
    }
}
