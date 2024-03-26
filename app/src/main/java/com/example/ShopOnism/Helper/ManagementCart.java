package com.example.ShopOnism.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.ShopOnism.Domain.DetailCartDomain;
import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;
import com.example.ShopOnism.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;
    private DBHelper DB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
        this.DB = new DBHelper(context);
    }

    public void insertCart(ProductDomain item,Integer id_user, Integer num){
        Boolean Checkcart = DB.checkCart(id_user);
        if(Checkcart == true){
            Boolean insertcart = DB.insertCart(id_user);
            if(insertcart == true) {
                insertProduct(item,id_user,num);
            }else {
                Toast.makeText(context,"Thất bại khi thêm sản phẩm",Toast.LENGTH_SHORT).show();
            }
        }else {
            insertProduct(item,id_user,num);
        }


    }

    public void insertProduct(ProductDomain item,Integer id_user, Integer num) {
        Integer id_Cart = getCart(id_user);
        ArrayList<DetailCartDomain> listdetailsCart = getListDetailsCart(id_Cart);
        boolean existAlready = false;
        Integer id = 0,num_Product = 0;
        for (int i = 0; i < listdetailsCart.size(); i++) {
            if (listdetailsCart.get(i).getId_product().equals(item.getId())) {
                existAlready = true;
                id = listdetailsCart.get(i).getId();
                num_Product = listdetailsCart.get(i).getNum() + num;
                break;
            }
        }

        if (existAlready == true) {
            Boolean ckeckupdate = DB.updateNumDetailsCart(id,num_Product);
            if(ckeckupdate == true){
                Toast.makeText(context, "Đã thêm sản phẩm ", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, " Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
            //listdetailsCart.get(n).setNum(item.getNumberInCart());
        } else {
            Boolean ckeckinsert = DB.insertDetailsCart(id_Cart,item.getId(),num);
            if(ckeckinsert == true){
                Toast.makeText(context, "Đã thêm sản phẩm ", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, " Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
            //listFood.add(item);
        }
        //tinyDB.putListObject("CardList", listFood);
        //Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<DetailCartDomain> getListDetailsCart(Integer id_cart) {
        return DB.getListDetailsCart(id_cart);
        //return tinyDB.getListObject("CardList");
    }

    public Integer getCart(Integer id_users){
        CartDomain mycart = DB.getCart_Type_False(id_users);
        Integer id_card = mycart.getId();
        return id_card;
    }

    public Integer getCartPay(Integer id_users){
        CartDomain mycart = DB.getCart_Type_True1(id_users);
        Integer id_card = mycart.getId();
        return id_card;
    }

    public ArrayList<ProductDomain> getlistProduct(Integer id_users){
        ArrayList<ProductDomain> productlist = new ArrayList<>();
        ArrayList<ProductDomain> allproduct = DB.getProduct();
        Integer id_cart = getCart(id_users);
        ArrayList<DetailCartDomain> alldetailscart = getListDetailsCart(id_cart);

        for(int i = 0; i < alldetailscart.size(); i++){
            for(int j = 0; j < allproduct.size(); j++){
                if(allproduct.get(j).getId().equals(alldetailscart.get(i).getId_product())){
                    productlist.add(allproduct.get(j));
                }
            }
        }

        return productlist;
    }

    public ArrayList<ProductDomain> getlistProductPay(Integer id_users){
        ArrayList<ProductDomain> productlist = new ArrayList<>();
        ArrayList<ProductDomain> allproduct = DB.getProduct();
        Integer id_cart = getCartPay(id_users);
        ArrayList<DetailCartDomain> alldetailscart = getListDetailsCart(id_cart);

        for(int i = 0; i < alldetailscart.size(); i++){
            for(int j = 0; j < allproduct.size(); j++){
                if(allproduct.get(j).getId().equals(alldetailscart.get(i).getId_product())){
                    productlist.add(allproduct.get(j));
                }
            }
        }
        return productlist;
    }

    public ArrayList<ProductDomain> getlistProductHisPay(Integer id_cart){
        ArrayList<ProductDomain> productlist = new ArrayList<>();
        ArrayList<ProductDomain> allproduct = DB.getProduct();
        ArrayList<DetailCartDomain> alldetailscart = getListDetailsCart(id_cart);
        for(int i = 0; i < alldetailscart.size(); i++){
            for(int j = 0; j < allproduct.size(); j++){
                if(allproduct.get(j).getId().equals(alldetailscart.get(i).getId_product())){
                    productlist.add(allproduct.get(j));
                }
            }
        }
        return productlist;
    }


    public void minusNumberFood(ArrayList<DetailCartDomain> listDetailsCart, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listDetailsCart.get(position).getNum() == 1) {
            Integer id_Dcart = listDetailsCart.get(position).getId();
            listDetailsCart.remove(position);
            DB.delectDetailsCartbyID(id_Dcart);
        } else {
            Integer id_Dcart = listDetailsCart.get(position).getId();
            listDetailsCart.get(position).setNum(listDetailsCart.get(position).getNum() - 1);
            Integer num = listDetailsCart.get(position).getNum();
            DB.updateNumDetailsCart(id_Dcart,num);
        }
        //tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public void plusNumberFood(ArrayList<DetailCartDomain> listDetailsCart, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        Integer id_Dcart = listDetailsCart.get(position).getId();
        listDetailsCart.get(position).setNum(listDetailsCart.get(position).getNum() + 1);
        Integer num = listDetailsCart.get(position).getNum();
        DB.updateNumDetailsCart(id_Dcart,num);
        //tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }

    public Integer getTotalFee(Integer id_user) {
        Integer id_cart = getCart(id_user);
        ArrayList<ProductDomain> listfproduct = getlistProduct(id_user);

        ArrayList<DetailCartDomain> listdetailscart = getListDetailsCart(id_cart);
        Integer fee = 0;
        for (int i = 0; i < listdetailscart.size(); i++) {
            fee = fee + (listfproduct.get(i).getFee() * listdetailscart.get(i).getNum());
        }

        return fee;
    }

    public CartDomain getCartToPay(Integer id_cart) {
        return DB.getCart(id_cart);
    }

    public Boolean updateCart(Integer id_user){
        Integer id_cart = getCart(id_user);
        Integer Total = getTotalFee(id_user);
        Boolean checkNum = updateNumProduct(id_user);
        if(checkNum == true){
            Boolean checkUpCart = DB.updateTotalCart(id_cart,Total);
            if(checkUpCart == false){
                Toast.makeText(context, "Thanh toán thất bại! Kiểm tra lại.", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                Toast.makeText(context, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                return true;
            }
        }else {
            Toast.makeText(context, "Thanh toán thất bại! Kiểm tra lại.", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public Boolean updateNumProduct(Integer id_user){
        Integer id_cart = getCart(id_user);
        ArrayList<ProductDomain> listfproduct = getlistProduct(id_user);

        ArrayList<DetailCartDomain> listdetailscart = getListDetailsCart(id_cart);
        Integer k = 0;
        for (int i = 0; i < listdetailscart.size(); i++) {
            Integer Num_;
            Num_ = listfproduct.get(i).getNum() - listdetailscart.get(i).getNum();
            if(Num_ >= 0){
//                DB.updateNumProduct(listfproduct.get(i).getId(), Num_);
                DB.updateNumProduct(listfproduct.get(i).getId(), Num_);
                k++;
            }
        }

        if(k == listdetailscart.size()){
            return true;
        }else {
            return false;
        }
    }

    public UserDomain getUserbyID(Integer id){

        return DB.getUserID(id);
    }

    public ProductDomain getIDProduct(Integer id){

        return DB.getProductById(id);
    }
}

