package com.example.demo.service;

import com.example.demo.dto.request.AddBookRequestDto;
import com.example.demo.dto.request.AddBorrowerRequestDto;
import com.example.demo.dto.request.BorrowBookRequestDto;
import com.example.demo.dto.request.ReturnBookRequestDto;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.dto.response.BorrowerResponseDto;
import com.example.demo.entity.Borrower;
import com.example.demo.entity.book.Book;
import com.example.demo.exception.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowBookRepository;
import com.example.demo.repository.BorrowerRepository;
import com.example.demo.utility.BookFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryManagementSystem {
    private  final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookFactory bookFactory;
    private  final BorrowBookRepository borrowBookRepository;


    public LibraryManagementSystem(BookRepository bookRepository, BorrowerRepository borrowerRepository, BookFactory bookFactory, BorrowBookRepository borrowBookRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
        this.bookFactory = bookFactory;
        this.borrowBookRepository = borrowBookRepository;
    }

    //BOOK SERVICE

    /**
     * This method creates new book
     * @param dto
     * @return
     */
    public boolean addBook(AddBookRequestDto dto) {
        Optional<Book> book = bookRepository.get(dto.getIsbn());
        if(!book.isPresent()){
            bookRepository.save(bookFactory.getBook(dto.getType(), dto.getTitle(), dto.getAuthor(), dto.getIsbn(), dto.getAvailableCopies()));
            return true;
        }else{
            throw new ManagerException(ErrorType.BOOK_IS_EXIST);
        }
    }

    /**
     * isbn is the primary key, you can get the information of requested book
     * @param isbn
     * @return
     */
    public BookResponseDto getBook(String isbn){
        Optional<Book> book = Optional.ofNullable(bookRepository.get(isbn).orElseThrow(() -> new ManagerException(ErrorType.BOOK_NOT_FOUND)));
        return BookResponseDto.builder()
                .title(book.get().getTitle())
                .author(book.get().getAuthor())
                .isbn(book.get().getIsbn())
                .availableCopies(book.get().getAvailableCopies())
                .type(book.get().getType().toString())
                .build();
    }

    /**
     * It removes book from book list
     * @param isbn
     * @return
     */
    public boolean deleteBook(String isbn){
        Optional<Book> book = bookRepository.get(isbn);
        if(book.isPresent()){
            bookRepository.delete(isbn);
            borrowBookRepository.deleteBookInfo(isbn);
            return true;
        }else{
            throw new ManagerException(ErrorType.BOOK_NOT_FOUND);
        }
    }

    //BORROWER SERVICE

    /**
     * This method creates new borrower
     * @param dto
     * @return
     */
    public boolean addBorrower(AddBorrowerRequestDto dto) {
        Optional<Borrower> borrower = borrowerRepository.get(dto.getEmail());
        if(!borrower.isPresent()){
            borrowerRepository.save(Borrower.builder().email(dto.getEmail()).name(dto.getName()).phone(dto.getPhone()).build());
            return true;
        }else{
            throw new ManagerException(ErrorType.BORROWER_IS_EXIST);
        }
    }

    /**
     * email is the primary key, you can get the information of requested borrower
     * @param email
     * @return
     */
    public BorrowerResponseDto getBorrower(String email){
        Optional<Borrower> borrower = Optional.ofNullable(borrowerRepository.get(email).orElseThrow(() -> new ManagerException(ErrorType.BORROWER_NOT_FOUND)));
        return BorrowerResponseDto.builder()
                .email(borrower.get().getEmail())
                .name(borrower.get().getName())
                .phone(borrower.get().getPhone())
                .build();
    }

    /**
     * It removes borrower from borrower list
     * @param email
     * @return
     */
    public boolean deleteBorrower(String email){
        Optional<Borrower> borrower = borrowerRepository.get(email);
        if(borrower.isPresent()){
            borrowerRepository.delete(email);
            borrowBookRepository.deleteBorrowerInfo(email);
            return true;
        }else{
            throw new ManagerException(ErrorType.BORROWER_NOT_FOUND);
        }
    }

    /**
     * this method allow to borrow a book
     * @param dto
     * @return
     */
    public BookResponseDto borrowBook(BorrowBookRequestDto dto){
        Book book = bookRepository.get(dto.getIsbn()).orElseThrow(()-> new ManagerException(ErrorType.BOOK_NOT_FOUND));
        Borrower borrower = borrowerRepository.get(dto.getEmail()).orElseThrow(()-> new ManagerException(ErrorType.BORROWER_NOT_FOUND));
        Optional<List<Book>> borrowedbooks = bookRepository.getBorrowedBook(borrower.getEmail());
            if(book.getAvailableCopies()>0 && !borrowedbooks.get().contains(book)){
                bookRepository.update(book.getAvailableCopies()-1,book.getIsbn());
                borrowBookRepository.borrowBook( book.getIsbn(),borrower.getEmail());
                return getBook(book.getIsbn());
            }else{
                throw new ManagerException(ErrorType.BOOK_NOT_AVAILABLE);
            }
    }

    /**
     * this method allow to return a book
     * @param dto
     * @return
     */
    public BookResponseDto returnBook(ReturnBookRequestDto dto){
        Book book = bookRepository.get(dto.getIsbn()).orElseThrow(()-> new ManagerException(ErrorType.BOOK_NOT_FOUND));
        Borrower borrower = borrowerRepository.get(dto.getEmail()).orElseThrow(()-> new ManagerException(ErrorType.BORROWER_NOT_FOUND));
        Optional<List<Book>> borrowedbooks = bookRepository.getBorrowedBook(borrower.getEmail());
        if(borrowedbooks.get().contains(book)){
            bookRepository.update(book.getAvailableCopies()+1,book.getIsbn());
            borrowBookRepository.returnbook( book.getIsbn(),borrower.getEmail());
            return getBook(book.getIsbn());
        }else{
            throw new ManagerException(ErrorType.NOT_BORROWED_BY);
        }
    }

    /**
     * It brings to all books in Library
     * @return
     */
    public List<BookResponseDto> findAllBooks(){
        List<Book> books = bookRepository.findAll().orElseThrow(()-> new ManagerException(ErrorType.BOOK_NOT_FOUND));
        return books.stream().map(x-> BookResponseDto.builder().isbn(x.getIsbn()).title(x.getTitle())
                .author(x.getAuthor()).availableCopies(x.getAvailableCopies()).type(x.getType().toString()).build())
                .collect(Collectors.toList());
    }

    /**
     * It brings to the list of all borrowers
     * @return
     */
    public List<BorrowerResponseDto> findAllBorrowers(){
        List<Borrower> borrowers = borrowerRepository.findAll().orElseThrow(()-> new ManagerException(ErrorType.BORROWER_NOT_FOUND));
        return borrowers.stream().map(x-> BorrowerResponseDto.builder().email(x.getEmail()).name(x.getName()).phone(x.getPhone()).build())
                .collect(Collectors.toList());
    }



}
