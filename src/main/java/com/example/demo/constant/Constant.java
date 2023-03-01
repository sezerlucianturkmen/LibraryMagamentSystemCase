package com.example.demo.constant;

public class Constant {

    private Constant(){
        //this class will not initiate
    }
    public static final String DBURL = "jdbc:postgresql://localhost:5432/LibraryDB";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "root";


    public static final String LIBRARY = "library";
    public static final String ADDBOOK = "addbook";
    public static final String ADDBORROWER = "addborrower";
    public static final String GETBOOK = "/getbook/{isbn}";
    public static final String GETBORROWER = "/getborrower/{email}";
    public static final String DELETEBOOK = "/getbook/{isbn}";
    public static final String DELETEBORROWER = "/getborrower/{email}";
    public static final String BORROWBOOK = "/borrowbook";
    public static final String RETURNBOOK = "/returnbook";
    public static final String FINDALLBOOKS = "/findallbooks";
    public static final String FINDALLBORROWERS = "/findallborrowers";
}
