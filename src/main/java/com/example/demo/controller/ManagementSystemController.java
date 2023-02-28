package com.example.demo.controller;

import com.example.demo.dto.request.AddBookRequestDto;
import com.example.demo.dto.request.AddBorrowerRequestDto;
import com.example.demo.dto.request.BorrowBookRequestDto;
import com.example.demo.dto.request.ReturnBookRequestDto;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.dto.response.BorrowerResponseDto;
import com.example.demo.service.LibraryManagementSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.constant.Constant.*;


@RestController
@RequestMapping(LIBRARY)
@RequiredArgsConstructor
public class ManagementSystemController {

    private final LibraryManagementSystem libraryManagementSystem;

    //BOOK
    @PostMapping(ADDBOOK)
    public ResponseEntity<Boolean> addBook(@RequestBody AddBookRequestDto dto){
        return ResponseEntity.ok(libraryManagementSystem.addBook(dto));
    }

    @GetMapping(GETBOOK)
    public ResponseEntity<BookResponseDto> addBook(@PathVariable String isbn){
        return ResponseEntity.ok(libraryManagementSystem.getBook(isbn));
    }

    @DeleteMapping(DELETEBOOK)
    public ResponseEntity<Boolean> deleteBook(@PathVariable String isbn){
        return ResponseEntity.ok(libraryManagementSystem.deleteBook(isbn));
    }

    //BORROWER
    @PostMapping(ADDBORROWER)
    public ResponseEntity<Boolean> addBorrower(@RequestBody AddBorrowerRequestDto dto){
        return ResponseEntity.ok(libraryManagementSystem.addBorrower(dto));
    }

    @GetMapping(GETBORROWER)
    public ResponseEntity<BorrowerResponseDto> getBorrower(@PathVariable String email){
        return ResponseEntity.ok(libraryManagementSystem.getBorrower(email));
    }

    @DeleteMapping(DELETEBORROWER)
    public ResponseEntity<Boolean> deleteBorrower(@PathVariable String email){
        return ResponseEntity.ok(libraryManagementSystem.deleteBorrower(email));
    }
    @PutMapping(BORROWBOOK)
    public ResponseEntity<BookResponseDto> borrowBook(@RequestBody BorrowBookRequestDto dto){
        return ResponseEntity.ok(libraryManagementSystem.borrowBook(dto));
    }
    @PutMapping(RETURNBOOK)
    public ResponseEntity<BookResponseDto> returnBook(@RequestBody ReturnBookRequestDto dto){
        return ResponseEntity.ok(libraryManagementSystem.returnBook(dto));
    }
    @GetMapping(FINDALLBOOKS)
    public ResponseEntity<List<BookResponseDto>> findAllBooks(){
        return ResponseEntity.ok(libraryManagementSystem.findAllBooks());
    }
    @GetMapping(FINDALLBORROWERS)
    public ResponseEntity<List<BorrowerResponseDto>> findAllBorrowers(){
        return ResponseEntity.ok(libraryManagementSystem.findAllBorrowers());
    }


}
