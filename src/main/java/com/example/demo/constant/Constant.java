package com.example.demo.constant;

public class Constant {
    public final static String DBURL = "jdbc:postgresql://localhost:5432/LibraryDB";
    public final static String USERNAME = "postgres";
    public final static String PASSWORD = "root";

    public final static String LIBRARY = "library";
    public final static String ADDBOOK = "addbook";
    public final static String ADDBORROWER = "addborrower";
    public final static String GETBOOK = "/getbook/{isbn}";
    public final static String GETBORROWER = "/getborrower/{email}";
    public final static String DELETEBOOK = "/getbook/{isbn}";
    public final static String DELETEBORROWER = "/getborrower/{email}";
    public final static String BORROWBOOK = "/borrowbook";
    public final static String RETURNBOOK = "/returnbook";
    public final static String FINDALLBOOKS = "/findallbooks";
    public final static String FINDALLBORROWERS = "/findallborrowers";
}
