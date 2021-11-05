package ba.unsa.etf.rs.zadaca4;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

public class LibraryDAO {
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private SimpleObjectProperty<Book> currentBook = new SimpleObjectProperty<>();

    private static LibraryDAO instance = null;
    private static Connection connection;
    private PreparedStatement preparedStatement, noviUpit, dodavanjeUpita, brisanjeUpit, izmjenaUpit, obrisiSveUpit;

    private LibraryDAO(){
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:library.db");
            preparedStatement=connection.prepareStatement("SELECT * FROM books;");


            noviUpit=connection.prepareStatement("Select MAX(id)+1 from books; ");
            dodavanjeUpita=connection.prepareStatement("INSERT into books VALUES(?,?,?,?,?,?);");
            brisanjeUpit=connection.prepareStatement("DELETE from books where id=?;");
            izmjenaUpit=connection.prepareStatement("UPDATE books Set author=?,title=?,isbn=?,pagecount=?,publishdate=? where id=?;");
            obrisiSveUpit=connection.prepareStatement("DELETE from books;");
            try {
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Book k=new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),(rs.getDate(6)).toLocalDate());
                    books.add(k);

                    if (currentBook == null) currentBook = new SimpleObjectProperty<>(k);
                }

            } catch (SQLException m) {
                m.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            ulaz.close();
        } catch (FileNotFoundException e) {
                System.out.println("Ne postoji SQL datoteka… nastavljam sa praznom bazom");
                }
        }

    public static LibraryDAO getInstance() {
        if (instance == null) instance = new LibraryDAO();
        instance.setCurrentBook(null);
        return instance;
    }
    
    public static void deleteInstance() {
        if(instance!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = null;
        }

    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void setBooks(ObservableList<Book> books) {
        this.books = books;
    }

    public Book getCurrentBook() {
        return currentBook.get();
    }

    public SimpleObjectProperty<Book> currentBookProperty() {
        return currentBook;
    }

    public void setCurrentBook(Book currentBook) {
        this.currentBook.set(currentBook);
    }
    //za printanje
    public void printBooks() {
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book k = new Book (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate());
                System.out.println(k);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }


    }

    public void updateCurrentBook(Book b4) {
        Book teka = new Book(getCurrentBook().getId(),b4.getAuthor(),b4.getTitle(),b4.getIsbn(),b4.getPageCount(),b4.getPublishDate());
        int index = getBooks().indexOf(getCurrentBook());
        books.set(index,teka);
        try {

            izmjenaUpit.setInt(6, getCurrentBook().getId());
            //PADA TEST TE IZBACUJE NULL POINTER U LINIJI IZNAD NE ZNAM ZASTO(test iz maincontrollera)
            izmjenaUpit.setString(1, b4.getAuthor());
            izmjenaUpit.setString(2, b4.getTitle());
            izmjenaUpit.setString(3, b4.getIsbn());
            izmjenaUpit.setInt(4, b4.getPageCount());
            izmjenaUpit.setDate(5, Date.valueOf(b4.getPublishDate()));

            izmjenaUpit.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addBook(Book b1) {
        books.add(b1);

        try {
            ResultSet rs = noviUpit.executeQuery();

            if (rs.next())
                b1.setId(rs.getInt(1));
            else
                b1.setId(1);

            dodavanjeUpita.setInt(1,b1.getId());
            dodavanjeUpita.setString(2,b1.getAuthor());
            dodavanjeUpita.setString(3,b1.getTitle());
            dodavanjeUpita.setString(4,b1.getIsbn());
            dodavanjeUpita.setInt(5,b1.getPageCount());
            dodavanjeUpita.setDate(6,Date.valueOf(b1.getPublishDate()));

            dodavanjeUpita.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        books.clear();

        try {
            obrisiSveUpit.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void defaultData() {
        addBook(new Book("Meša Selimović", "Tvrđava", "abcd", 500, LocalDate.now()));
        addBook(new Book("Ivo Andrić", "Travnička hronika", "abcd", 500, LocalDate.now()));
        addBook(new Book("J. K. Rowling", "Harry Potter", "abcd", 500, LocalDate.now()));
    }



    public String getBookList() {
        String recenica = "";
        for(Book bb : books){
            recenica += bb;
            recenica += "\n";
        }
        return recenica;
    }

    public void deleteBook() {
        if(getCurrentBook()!=null) {

            try {
                brisanjeUpit.setInt(1, getCurrentBook().getId());
                brisanjeUpit.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
         books.remove(getCurrentBook());
         setCurrentBook(null);
        }
        else
            return;
    }
}
