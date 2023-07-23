package edu.huflit.tryhardmode.Voucher;

import java.io.Serializable;

public class Voucher implements Serializable {

        private int id;
        private String maVoucher;
        private String voucherName;
        private double discountPrice;
        private double Price;
        private String moTa;



        public Voucher(int id, String maVoucher, String voucherName, double discountPrice, double price, String moTa) {
                this.id = id;
                this.maVoucher = maVoucher;
                this.voucherName = voucherName;
                this.discountPrice = discountPrice;
                Price = price;
                this.moTa = moTa;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getMaVoucher() {
                return maVoucher;
        }

        public void setMaVoucher(String maVoucher) {
                this.maVoucher = maVoucher;
        }

        public String getVoucherName() {
                return voucherName;
        }

        public void setVoucherName(String voucherName) {
                this.voucherName = voucherName;
        }

        public double getDiscountPrice() {
                return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
                this.discountPrice = discountPrice;
        }

        public double getPrice() {
                return Price;
        }

        public void setPrice(double price) {
                Price = price;
        }

        public String getMoTa() {
                return moTa;
        }

        public void setMoTa(String moTa) {
                this.moTa = moTa;
        }


}
