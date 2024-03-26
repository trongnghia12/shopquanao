package com.example.ShopOnism.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.ShopOnism.Domain.CartDomain;
import com.example.ShopOnism.Domain.CategoryDomain;
import com.example.ShopOnism.Domain.DetailCartDomain;
import com.example.ShopOnism.Domain.ProductDomain;
import com.example.ShopOnism.Domain.UserDomain;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper (Context context ) {

        super(context, "AppData.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        //user
        myDB.execSQL("create Table users(id integer primary key autoincrement  , username Text ,name nvachar(250) ,address nvarchar(100),phone text, password Text,type INTEGER,gmail TEXT)");
        myDB.execSQL("insert into users(id,username,name,address,phone,password,type,gmail) VALUES (null, 'admin',null,null,null,'admin','1','admin123@gmail.com')");
        myDB.execSQL("insert into users(id,username,name,address,phone,password,type,gmail) VALUES (null, 'Ngoc','Thuy Ngoc','TP HCM','0346676839','ngoc123','2',null)");
        myDB.execSQL("insert into users(id,username,name,address,phone,password,type,gmail) VALUES (null, 'Nghia','Trong Nghian','Da Lat','0972022799','nghia123','2','ltn@gmail.com')");

        // tạo và thêm table category
        myDB.execSQL("create table category(id Text, title NVARCHAR(100), pic TEXT)");
        myDB.execSQL("insert into category(id,title,pic) VALUES ('A1', 'Áo thun', null)");
        myDB.execSQL("insert into category(id,title,pic) VALUES ('A2', 'Quần', null)");
        myDB.execSQL("insert into category(id,title,pic) VALUES ('A3', 'Hoodie', null)");
        myDB.execSQL("insert into category(id,title,pic) VALUES ('A4', 'Chân váy', null)");


        //Tao bang san pham
        myDB.execSQL("create table product(id INTEGER PRIMARY KEY AUTOINCREMENT, title NVARCHAR(100), pic TEXT, description NVARCHAR(100), fee INTEGER, id_category Text, num INTERGER, FOREIGN KEY(id_category) REFERENCES category(id))");

        //Them san pham
        myDB.execSQL("insert into product(id,title,pic,description,fee,id_category,num) VALUES (null,'Áo thun tay lỡ Basic Onism garment - Basic tee ONISM', 'https://down-vn.img.susercontent.com/file/sg-11134201-23020-grh4xntr03mv87','GỢI Ý CHỌN SIZE: \n" +
                "\n" +
                "- Size 1: Chiều cao gợi ý 1m5 - 1m65/ cân nặng dưới 55kg\n" +
                "\n" +
                "- Size 2: Chiều cao gợi ý 1m65 - 1m72/ cân nặng dưới 65kg\n" +
                "\n" +
                "- Size 3: Chiều cao gợi ý 1m7 - 1m77/ cân nặng dưới 80kg ( Dành cho bạn nam ) \n" +
                "\n" +
                "*Lưu ý: Đây là bảng size và kích thước mang tính chất tư vấn và có thể thay đổi một chút ở thực tế. Nếu bạn cần hỗ trợ chọn size chi tiết vui lòng liên hệ shop nhé','295000','A1','10')");
        myDB.execSQL("insert into product(id,title,pic,description,fee,id_category,num) VALUES (null,'Váy ngắn nữ Lure Mini Skirt màu blue wax - Onism','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lm75owfdfrhb48', 'GỢI Ý CHỌN SIZE: \n" +
                "\n" +
                "- Size 1: Chiều cao gợi ý 1m5 - 1m65/ cân nặng dưới 55kg\n" +
                "\n" +
                "- Size 2: Chiều cao gợi ý 1m65 - 1m72/ cân nặng dưới 65kg\n" +
                "\n" +
                "*Lưu ý: Đây là bảng thông số sản phẩm tiêu chuẩn, vui lòng nhắn tin nếu cần hỗ trợ tư vấn lựa chọn size phù hợp','190000','A2','10')");
        myDB.execSQL("insert into product(id,title,pic,description,fee,id_category,num) VALUES(null,'Váy ngắn nữ xếp li 2000 jean mini skirt màu xanh - Onism','https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-ley0iyaaugk71e','GỢI Ý CHỌN SIZE: \n" +
                "\n" +
                "- Size 1: Chiều cao gợi ý 1m5 - 1m65/ cân nặng dưới 55kg\n" +
                "\n" +
                "- Size 2: Chiều cao gợi ý 1m65 - 1m72/ cân nặng dưới 65kg\n" +
                "\n" +
                "*Lưu ý: Đây là bảng thông số sản phẩm tiêu chuẩn, vui lòng nhắn tin nếu cần hỗ trợ tư vấn lựa chọn size phù hợp','350000','A4','10')");
        myDB.execSQL("insert into product(id,title,pic,description,fee,id_category,num) VALUES (null,'Áo thun unisex Sadder màu nude - Basic tee Onism', 'https://down-vn.img.susercontent.com/file/sg-11134201-22110-bjmudgv5kvjv14','GỢI Ý CHỌN SIZE: \n" +
                "\n" +
                "- Size 1: Chiều cao gợi ý 1m5 - 1m65/ cân nặng dưới 55kg\n" +
                "\n" +
                "- Size 2: Chiều cao gợi ý 1m65 - 1m72/ cân nặng dưới 65kg\n" +
                "\n" +
                "- Size 3: Chiều cao gợi ý 1m7 - 1m77/ cân nặng dưới 80kg ( Dành cho bạn nam ) \n" +
                "\n" +
                "*Lưu ý: Đây là bảng size và kích thước mang tính chất tư vấn và có thể thay đổi một chút ở thực tế. Nếu bạn cần hỗ trợ chọn size chi tiết vui lòng liên hệ shop nhé','220000','A1','10')");
        myDB.execSQL("insert into product(id,title,pic,description,fee,id_category,num) VALUES (null,'Áo HOODIE unisex Naughty Honey màu trắng- Hoodie Onism', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lq4b5ebxbcgi46','GỢI Ý CHỌN SIZE: \n" +
                "\n" +
                "- Size 1: Chiều cao gợi ý 1m65 - 1m72/ cân nặng dưới 65kg\n" +
                "\n" +
                "- Size 2: Chiều cao gợi ý 1m7 - 1m77/ cân nặng dưới 80kg ( Dành cho bạn nam ) \n" +
                "\n" +
                "*Lưu ý: Đây là bảng size và kích thước mang tính chất tư vấn và có thể thay đổi một chút ở thực tế. Nếu bạn cần hỗ trợ chọn size chi tiết vui lòng liên hệ shop nhé','450000','A3','10')");
        myDB.execSQL("insert into product(id,title,pic,description,fee,id_category,num) VALUES (null,'Áo thun unisex I Heart U màu trắng- Charm tee Onism', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-low84csnw40edc','GỢI Ý CHỌN SIZE: \n" +
                "\n" +
                "- Size 1: Chiều cao gợi ý 1m5 - 1m65/ cân nặng dưới 55kg\n" +
                "\n" +
                "- Size 2: Chiều cao gợi ý 1m65 - 1m72/ cân nặng dưới 65kg\n" +
                "\n" +
                "- Size 3: Chiều cao gợi ý 1m7 - 1m77/ cân nặng dưới 80kg ( Dành cho bạn nam ) \n" +
                "\n" +
                "*Lưu ý: Đây là bảng size và kích thước mang tính chất tư vấn và có thể thay đổi một chút ở thực tế. Nếu bạn cần hỗ trợ chọn size chi tiết vui lòng liên hệ shop nhé','245000','A1','10')");

        https://hoasap.vn/wp-content/uploads/2018/11/hoa-hong-sap-thom-hop-21-bong-cao-cap-2.jpg
        //Tao bảng bình luận
        myDB.execSQL("create table comment(id INTEGER PRIMARY KEY AUTOINCREMENT,id_user Integer, comment NVARCHAR(100), username Text,date TEXT,FOREIGN KEY(id_user) REFERENCES users(id) )");

        //Them bình luận
        myDB.execSQL("insert into comment(id,id_user,comment,username,date) VALUES (null, null, '10 diem không điểm nào để chê','nguoi la',null)");
        myDB.execSQL("insert into comment(id,id_user,comment,username,date) VALUES (null, null, 'Trên cả tuyệt vời','Người lạ',null)");

        // Create Table Cart
        myDB.execSQL("create table cart(id INTEGER PRIMARY KEY AUTOINCREMENT, id_users INTEGER, total INTEGER, type TEXT,date TEXT ,FOREIGN KEY(id_users) REFERENCES users(id))");

        // Create Table DetailsCart
        myDB.execSQL("create table detailscart(id INTEGER PRIMARY KEY AUTOINCREMENT,id_cart INTEGER, id_product INTEGER, num INTEGER, FOREIGN KEY(id_product) REFERENCES product(id), FOREIGN KEY(id_cart) REFERENCES cart(id))");

    }
    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
        myDB.execSQL("drop Table if exists product");
        myDB.execSQL("drop Table if exists category");
        myDB.execSQL("drop Table if exists cart");
        myDB.execSQL("drop Table if exists detailscart");
        myDB.execSQL("drop Table if exists comment");
        onCreate(myDB);

    }

    public Boolean updateUserType(String username, int newType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", newType);
        long result = db.update("users", values, "username = ?", new String[]{username});

        return result != -1;
    }

    public void queryData(String sql){
        SQLiteDatabase myDB = getWritableDatabase();
        myDB.execSQL(sql);
    }
    //add user
    public Boolean insertData(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("type",2);
        long result = myDB.insert("users", null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    //kiểm tra user
    public boolean checkusername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select *from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
        {
            return true;

        }else
        {
            return false;
        }
    }

    //kiểm tra mật khẩu
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select *from users where username = ? and password =?",new String[]{username,password});
        if(cursor.getCount()>0)
        {  System.out.println("here");
            return true;

        }else
        {
            return false;
        }

    }


    //user
    public UserDomain getUser(String username){
        UserDomain user = new UserDomain();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        try{
            if(cursor != null){
                cursor.moveToFirst();
                user = new UserDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getInt(6),cursor.getString(7));

            }
        }catch (Exception e){
            user = new UserDomain(0,"nam",null,null,null,null,0,null);

        }


        return user;
    }

    public UserDomain getUserID(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where id=?", new String[] {String.valueOf(id)});
        if(cursor != null)
            cursor.moveToFirst();
        UserDomain user = new UserDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getInt(6), cursor.getString(7));
        return user;
    }
    public ArrayList<UserDomain> getAllUser(){
        ArrayList<UserDomain> userlist = new ArrayList<>();
        String query = "SELECT * FROM users WHERE type=" + "2";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            UserDomain user = new UserDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getInt(6),cursor.getString(7));
            userlist.add(user);
            cursor.moveToNext();
        }
        return userlist;
    }

    public Integer set_admin(String username,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserDomain> kh = new ArrayList<>();
        if (!username.isEmpty() || !password.isEmpty()) {
            Cursor cursor = db.rawQuery("Select * from users where username =? and password =? and type=1 ", new String[]{username, password});
            while (cursor.moveToNext()) {
                UserDomain user = new UserDomain(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                        cursor.getString(7));

                kh.add(user);
            }
        }
        if (kh.size() > 0) {
            return 1;
        }
        return 0;
    }

    public Boolean updateUserProfile(String username,  String name,String gmail, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("gmail", gmail);
        values.put("phone", phone);
        values.put("address", address);
        long result = db.update("users", values, "username = " + "'" + username + "'", null);
        if (result == -1) {

            return false;
        } else {

            return true;
        }

    }

    public Boolean updateUserPic(String username,String pic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pic", pic);
        long result = db.update("users", values, "username = " + "'" + username + "'", null);
        if (result == -1) {
            return false;
        } else {

            return true;
        }
    }

    //Category
    public ArrayList<CategoryDomain> getCategory() {
        ArrayList<CategoryDomain> categorylist = new ArrayList<>();
        String query = "SELECT * FROM category";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            CategoryDomain category = new CategoryDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            categorylist.add(category);
            cursor.moveToNext();
        }
        return categorylist;
    }

    public CategoryDomain getCategory(String id) {
        CategoryDomain category = new CategoryDomain();
        String query = "SELECT * FROM category where id ='"+id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            category = new CategoryDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        return category;
    }

    //Product
    public ArrayList<ProductDomain> getProduct() {
        ArrayList<ProductDomain> productlist = new ArrayList<>();
        String query = "SELECT * FROM product";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            ProductDomain product = new ProductDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6) );
            productlist.add(product);
            cursor.moveToNext();
        }
        return productlist;
    }
    //Product
    public ArrayList<ProductDomain> getTopProduct() {
        ArrayList<ProductDomain> productoptlist = new ArrayList<>();
        String query = "SELECT * FROM product limit 3";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            ProductDomain producttop = new ProductDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6) );
            productoptlist.add(producttop);
            cursor.moveToNext();
        }
        return productoptlist;
    }

    //Product
    public ArrayList<ProductDomain> getTopSale() {
        ArrayList<ProductDomain> salelist = new ArrayList<>();
        String query = "SELECT * FROM saleproduct";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            ProductDomain topsale = new ProductDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6) );
            salelist.add(topsale);
            cursor.moveToNext();
        }
        return salelist;
    }

    public void updateNumProduct(Integer id_, Integer num_){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("num", num_);

        db.update("product",values,"id = " + "'"+id_+"'", null);
    }

    //ProductCategory
    public ArrayList<ProductDomain> getProductCategory(String ID_category) {
        ArrayList<ProductDomain> produccategorytlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product where id_category=?" ,new String[] {ID_category});
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            ProductDomain productcategory = new ProductDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6) );
            produccategorytlist.add(productcategory);
            cursor.moveToNext();
        }
        return produccategorytlist;
    }
    public ProductDomain getProductById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product where id=" + "'"+id+"'",null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        ProductDomain product = new ProductDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6) );
        return product;
    }


    public ArrayList<ProductDomain> getSearch() {
        ArrayList<ProductDomain> searchlist = new ArrayList<>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product ", null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            ProductDomain search = new ProductDomain(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6) );
            searchlist.add(search);
            cursor.moveToNext();
        }
        return searchlist;
    }
    //Cart
    public Boolean insertCart(Integer id_users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_users", id_users);
        values.put("total",0);
        values.put("type", "0");
        values.put("date", "0");

        long result= db.insert("cart",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkCart(Integer id_users){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart where id_users=" + "'"+id_users+"'" + " and type=" +"'"+ 0 + "'", null);
        cursor.moveToFirst();

        if(cursor.isFirst() == false) return true;
        else
            return false;
    }

    public CartDomain getCart_Type_True1(Integer id_users){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart where id_users=" + "'"+id_users+"'" + " and type=" + "1" + " ORDER BY id DESC limit 1",null);
        cursor.moveToFirst();
        if (cursor !=null)
            cursor.moveToFirst();
        CartDomain cart = new CartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4));
        return cart;
    }
    public CartDomain getCart_Type_False(Integer id_users){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cart where id_users=" + "'"+id_users+"'" + " and type=" +"0" , null);
        if(cursor != null)
            cursor.moveToFirst();
        CartDomain cart = new CartDomain(cursor.getInt(0),
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getString(4));////
        return cart;
    }

    public CartDomain getCart(Integer id_cart){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cart where id=" + "'"+id_cart+"'", null);
        if(cursor != null)
            cursor.moveToFirst();
        CartDomain cart = new CartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
        return cart;
    }

    public ArrayList<CartDomain> getALL_Cart_Type_True(Integer id_users){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CartDomain> listAllCart = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM cart where id_users=" + "'"+id_users+"'" + " and type!=" + "0" ,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            CartDomain cart = new CartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4));
            listAllCart.add(cart);
            cursor.moveToNext();
        }
        return listAllCart;
    }
    public ArrayList<CartDomain> getALL_Cart_Admin(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CartDomain> listAllCart = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM cart where  type !=" + "0" ,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            CartDomain cart = new CartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4));
            listAllCart.add(cart);
            cursor.moveToNext();
        }
        return listAllCart;
    }
    public ArrayList<CartDomain> getALLCart_Type_False(){
        ArrayList<CartDomain> listallcart = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cart where type=" + "0", null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            CartDomain cart = new CartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2), cursor.getInt(3),cursor.getString(4));
            listallcart.add(cart);
            cursor.moveToNext();
        }
        return listallcart;
    }
    public Boolean updateTotalCart(Integer id_cart, Integer total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("total", total);
        values.put("type", 1);
        long result= db.update("cart",values,"id=" + "'"+id_cart+"'", null);
        if(result==-1) return false;
        else
            return true;
    }
    public void updateDateCart(Integer id_cart, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("date", date);

        db.update("cart",values,"id=" + "'"+id_cart+"'", null);
    }
    public void updateDateCartType(Integer id_cart, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type);
        db.update("cart",values,"id=" + "'"+id_cart+"'", null);
    }
    public ArrayList<CartDomain> get_AllCart(){
        ArrayList<CartDomain> cartlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart" ,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            CartDomain cart = new CartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4));
            cartlist.add(cart);
            cursor.moveToNext();
        }
        return cartlist;
    }

    public Boolean delectCate(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long reuslt = db.delete("category","id ='"+id+"'",null);
        if(reuslt == -1) return false;
        else
            return true;
    }

    //Chi tiết giỏ hàng
    public ArrayList<DetailCartDomain> getListDetailsCart(Integer id_cart){
        ArrayList<DetailCartDomain> listdetailscart = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM detailscart where id_cart=" + "'"+id_cart+"'", null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            DetailCartDomain Dcart = new DetailCartDomain(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3));
            listdetailscart.add(Dcart);
            cursor.moveToNext();
        }
        return listdetailscart;
    }
    public Boolean insertDetailsCart(Integer id_cart, Integer id_product, Integer num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_cart", id_cart);
        values.put("id_product", id_product);
        values.put("num", num);

        long result= db.insert("detailscart",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertCate(String id,String tile, String pic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("title", tile);
        values.put("pic", pic);
        long result= db.insert("category",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean updateCate(String id, String title, String pic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("pic", pic);
        long result= db.update("category",values,"id = " + "'"+id+"'", null);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean updateNumDetailsCart(Integer id, Integer num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("num", num);

        long result= db.update("detailscart",values,"id = " + "'"+id+"'", null);
        if(result==-1) return false;
        else
            return true;
    }

    public void delectDetailsCartbyID(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("detailscart","id = " + "'"+id+"'",null);
        db.close();
    }

    //Admin

    public Boolean updateUserData( String name,String phone, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("phone", phone);
        values.put("address", address);
        Cursor cursor = db.rawQuery("SELECT * FROM users where name =?", new String[]{name});
        if(cursor.getCount()>0){

            long result= db.update("users",values,"username =? " , new String[]{name});
            if(result==-1) {
                return false;
            } else{
                return true;
            }
        }else {
            return  false;
        }
    }

    //Admin delete user
    public Boolean deleteUserData(String username,  String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users where name =?", new String[]{name});
        if(cursor.getCount()>0){

            long result= db.delete("users","username = " + "'"+username+"'", null);
            if(result==-1) {
                return false;
            } else{
                return true;
            }
        }else {
            return  false;
        }
    }

    //admin insert
    //Add Product
    public void delectDetailsCartbyID_Product(Integer id_product){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("detailscart","id_product = " + "'"+id_product+"'",null);

    }
    public void delectComment(Integer id_comment){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("comment","id = " + "'"+id_comment+"'",null);

    }
    public boolean insertProduct(String title, String link, String description, String fee, String id_category, Integer num){

        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("pic", link);
        contentValues.put("description",description);
        contentValues.put("fee",fee);
        contentValues.put("id_category",id_category);
        contentValues.put("num",num);
        long result = DB.insert("product", null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateProduct(Integer id_,String title_,String link_, String description_, String fee_, String id_category_, Integer num_){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title_);
        contentValues.put("pic", link_);
        contentValues.put("description",description_);
        contentValues.put("fee",fee_);
        contentValues.put("id_category",id_category_);
        contentValues.put("num",num_);

        long re = db.update("product",contentValues,"id = "+ "'"+id_+"'" , null);
        if(re == -1) return false;
        else
            return true;
    }
    public Bitmap getPic(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM product where name = ?",new String[]{name});
        cursor.moveToFirst();
        byte[] bitmap = cursor.getBlob(1);
        Bitmap image = BitmapFactory.decodeByteArray(bitmap,0, bitmap.length);
        return image;
    }
    public Boolean delectProduct(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        long re = db.delete("product","id = " + "'"+id+"'",null);
        if(re == -1) return false;
        else
            return true;
    }

    public Boolean delectProductOFCategory(Integer id_category){
        SQLiteDatabase db = this.getWritableDatabase();
        long re = db.delete("product","id_category = " + "'"+id_category+"'",null);
        if(re == -1) return false;
        else
            return true;
    }
    public Boolean Check_Mail_User(String username, String gmail){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and gmail=?", new String[] {username,gmail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean updatePasswordUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("password", password);

        long result= db.update("users",values,"username = " + "'"+username.trim()+"'", null);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean ChangePasswordUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("password", password);

        long result= db.update("users",values,"username = " + "'"+username.trim()+"'", null);
        if(result==-1) return false;
        else
            return true;
    }

    //Comment

}
