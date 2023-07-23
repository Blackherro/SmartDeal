package edu.huflit.tryhardmode.TaiKhoan;

public class KhachHang {

    private long _id;
    private String _email;
    private String _password;

    private String  _ten;



    //get methods
    long get_id() {return _id;}


    String get_password(){return _password;}
    String get_email(){return _email;}

    String get_ten(){return _ten;}

    //set methods
    public void set_id(long id){this._id = id;}
    public void set_email(String email){this._email = email;}

    public void set_password(String password){this._password = password;}

    public void set_ten(String ten){this._ten=ten;}
}
