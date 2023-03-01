package com.example.demo.repository;

import com.example.demo.utility.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class BorrowBookRepository {

    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;

    public void borrowBook(String isbn, String email) {
        String sql = "insert into tblborrowbook (isbn,email) values( ?,? )";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, isbn);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void returnbook(String isbn, String email) {
        String sql = "Delete from tblborrowbook where isbn LIKE ? and email LIKE ?";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, isbn);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * When the book is deleted, it also removes all related data.
     *
     * @param isbn
     */
    public void deleteBookInfo(String isbn) {
        String sql = "Delete from tblborrowbook where isbn LIKE ? ";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, isbn);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * When the book is deleted, it also removes all related data.
     *
     * @param email
     */
    public void deleteBorrowerInfo(String email) {
        String sql = "Delete from tblborrowbook where email LIKE ? ";
        try {
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
