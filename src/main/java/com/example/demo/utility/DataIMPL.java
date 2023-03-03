package com.example.demo.utility;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Component
public class DataIMPL {
    private Logger logger = Logger.getLogger(DataIMPL.class.getName());
    @PostConstruct
    private void start(){
        createTblbook();
        createTblborrower();
        createTblborrowbook();
    }

    private void createTblbook(){
        String sql="CREATE TABLE tblbook" + "(isbn VARCHAR(255) not NULL, " + " title VARCHAR(255), " +
                " author VARCHAR(255), " + " available_copies INTEGER, " + " type VARCHAR(255), " + " PRIMARY KEY (isbn))";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.executeQuery();
            logger.log(Level.INFO,"tblbook is implemented");
        }
        catch (SQLException ex) {
            Logger.getLogger(DataIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createTblborrower(){
        String sql="CREATE TABLE tblborrower" + "(email VARCHAR(255) not NULL, " + " name VARCHAR(255), " +
                " phone VARCHAR(255), " + " PRIMARY KEY (email))";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.executeQuery();
            logger.log(Level.INFO,"tblborrower is implemented");
        }
        catch (SQLException ex) {
            Logger.getLogger(DataIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void createTblborrowbook(){
        String sql="CREATE TABLE tblborrowbook" + "(isbn VARCHAR(255) not NULL, " + " email VARCHAR(255) not NULL) ";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.executeQuery();
            logger.log(Level.INFO,"tblbookborrow is implemented");
        }
        catch (SQLException ex) {
            Logger.getLogger(DataIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
