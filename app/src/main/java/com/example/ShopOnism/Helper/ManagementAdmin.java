package com.example.ShopOnism.Helper;

import android.content.Context;

import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Domain.DetailCartDomain;
import com.example.ShopOnism.Domain.ProductDomain;

import java.util.ArrayList;

public class ManagementAdmin {
    Context context;
    DBHelper DB;

    public ManagementAdmin(Context context) {
        this.context = context;
        this.DB = new DBHelper(context);
    }
    public interface ChangeItemsListener {
        void changed();
    }
    public void delectDetailsBy_ID_Product(Integer id_product){
        ArrayList<CartDomain> cartlistFalse = DB.getALLCart_Type_False();
        Integer k=0;
        for(int i= 0; i<cartlistFalse.size();i++){
            ArrayList<DetailCartDomain> detailslistcart = DB.getListDetailsCart(cartlistFalse.get(i).getId());
            for (int j = 0; j<detailslistcart.size();j++ ){
                if(detailslistcart.get(j).getId_product().equals(id_product)){
                    DB.delectDetailsCartbyID_Product(id_product);
                    k++;
                }
            }
        }
    }
    public Boolean deleteProduct(Integer id_product){
        Boolean checkDeleteDetails;
        try {
            delectDetailsBy_ID_Product(id_product);
            checkDeleteDetails = true;
        }catch (Exception ex){
            checkDeleteDetails = false;
        }
        if(checkDeleteDetails == true){
            Boolean delete_Product = DB.delectProduct(id_product);
            if(delete_Product == true){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }


    public  void delectSP(ArrayList<ProductDomain> SP,int position, ChangeItemsListener changeItemsListener){

        SP.get(position).getId();
        Integer id = SP.get(position).getId();
        SP.remove(position);
        DB.delectProduct(id);
        changeItemsListener.changed();
    }
}
