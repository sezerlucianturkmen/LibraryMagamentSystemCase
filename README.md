# LIBRARY MANAGEMENT SYSTEM
Library Management System that allows users to manage books and borrowers.
<br><br>
<h4> <strong>PRODUCT URL:</strong> http://localhost/swagger-ui/index.html#/</h4>
<h4> <strong>DATABASE NAME:</strong> LibraryDB (postgresSQL)/h4>
<br><br>
Factory Design Pattern is used to create BOOK object. BookFactory class is allow to create sub-class instance accourding to the type of book.

Singleton Design Pattern is used to create an instance of LibraryManagementSystem service class, and also to create DBConnection class.

For Database connection is made by using JDBC and also dataIMPL class has @PostConstruction annotation which allows to create database table when the application is started.

Book Class and Borrower Class have ManyToMany relation between.

Application have following methods:
· addBook: Adds a new book to the list of books.Check if the book is already exist.

· removeBook: Removes a book from the list of books

· getBook: Returns a book based on its ISBN

· borrowBook: Updates the available copies of a book when it is borrowed by a borrower. Check also if this borrower is already borrowed or not.

· returnBook: Updates the available copies of a book when it is returned by a borrower. Check also if this borrower is already borrowed or not.

· addBorrower: Adds a new borrower to the list of borrowers.Check if the borrower is already exist.

· removeBorrower: Removes a borrower from the list of borrowers

· getBorrower: Returns a borrower based on their email

· findAllBooks: Returns book list in library.

· findAllBooks: Returns borrower list in library.

<br><br>
