package com.example.demo.repository;

import com.example.demo.entity.Borrower;
import com.example.demo.entity.book.Book;
import com.example.demo.utility.BookFactory;
import com.example.demo.utility.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class BookRepository implements ICRUD<Book>{
    
    private final BookFactory bookFactory;

    @Override
    public void save(Book book) {
        String sql="insert into tblbook (isbn,title,author,available_copies,type) values( ?,?,?,?,? )";
        try {
            PreparedStatement ps= DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getAvailableCopies());
            ps.setString(5, book.getType().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Optional<Book> get(String id) {
        Book book = null;
        String sql="Select * from tblbook where isbn LIKE ?";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                book =bookFactory.getBook(rs.getString("type"), rs.getString("title"), rs.getString("author"),rs.getString("isbn"), rs.getInt("available_copies"));
               }
        }
        catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Optional.ofNullable(book);
    }



    public Optional<List<Book>> getBorrowedBook(String email){
        List<Book> books=new ArrayList<>();
        String sql="Select * from tblborrowbook where email like ?";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                books.add(get(rs.getString("isbn")).get());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  Optional.ofNullable(books);
    }


    public void update(Integer availableCopies, String id) {
        String sql= "update tblbook set available_copies=? where isbn LIKE ?";
        try {
            PreparedStatement ps =DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, availableCopies);
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        String sql="Delete from tblbook where isbn LIKE ?";
        try {
            PreparedStatement ps=DBConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Optional<List<Book>> findAll() {
        List<Book> books=new ArrayList<>();
        String sql="Select * from tblbook";
        PreparedStatement ps;
        try {
            ps = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                books.add(bookFactory.getBook(rs.getString("type"), rs.getString("title"),
                        rs.getString("author"),rs.getString("isbn"), rs.getInt("available_copies")));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BorrowBookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  Optional.ofNullable(books);
    }
}
