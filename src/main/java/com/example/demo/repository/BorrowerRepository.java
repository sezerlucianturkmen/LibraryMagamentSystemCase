package com.example.demo.repository;

import com.example.demo.entity.Borrower;
import com.example.demo.entity.book.Book;
import com.example.demo.utility.BookFactory;
import com.example.demo.utility.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class BorrowerRepository implements ICRUD<Borrower>{
    
    private final BookFactory bookFactory;

    @Override
    public void save(Borrower borrower) {
        String sql="insert into tblborrower (email,name,phone) values( ?,?,? )";
        try {
            PreparedStatement ps= DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, borrower.getEmail());
            ps.setString(2, borrower.getName());
            ps.setString(3, borrower.getPhone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Optional<Borrower> get(String id) {
        Borrower borrower = null;
        String sql="Select * from tblborrower where email LIKE ?";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                borrower =Borrower.builder()
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .phone(rs.getString("phone"))
                        .build();
             }
        }
        catch (SQLException ex) {
            Logger.getLogger(BorrowerRepository.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Optional.ofNullable(borrower);
    }

    public Optional<List<Borrower>> getBorrowers(String isbn){
        List<Borrower> borrowers=new ArrayList<>();
        String sql="Select * from tblborrowbook where isbn like ?";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                borrowers.add(get(rs.getString("email")).get());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  Optional.ofNullable(borrowers);
    }

    @Override
    public void delete(String id) {
        String sql="Delete from tblborrower where email LIKE ?";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowerRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Optional<List<Borrower>> findAll() {
        List<Borrower> borrowers=new ArrayList<>();
        String sql="Select * from tblborrower";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                borrowers.add(Borrower.builder()
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .phone(rs.getString("phone"))
                        .build());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  Optional.ofNullable(borrowers);
    }


}
