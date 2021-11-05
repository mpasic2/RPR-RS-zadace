package ba.unsa.etf.rs.zadaca4;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Book {

    public static Date parseDateStr(final String dateStr) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.parse(dateStr);
    }

    private int id;
    private SimpleStringProperty author;
    private SimpleStringProperty title;
    private SimpleStringProperty isbn;
    private SimpleIntegerProperty pageCount;
    private SimpleObjectProperty<LocalDate> publishDate;
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd. MM. yyyy");



    public Book(int id, String author, String title, String isbn, int pageCount, LocalDate publishDate) {
        this.id = id;
        this.author = new SimpleStringProperty(author);
        this.title = new SimpleStringProperty(title);
        this.isbn = new SimpleStringProperty(isbn);
        this.pageCount = new SimpleIntegerProperty(pageCount);
        this.publishDate = new SimpleObjectProperty<LocalDate>(publishDate);
    }

    public Book(String author, String title, String isbn, int pageCount, LocalDate publishDate) {
        this.id = 0;
        this.author = new SimpleStringProperty(author);
        this.title = new SimpleStringProperty(title);
        this.isbn = new SimpleStringProperty(isbn);
        this.pageCount = new SimpleIntegerProperty(pageCount);
        this.publishDate = new SimpleObjectProperty<LocalDate>(publishDate);
    }

    @Override
    public String toString() {
        return  author.get() + ", " + title.get() + ", " + isbn.get() +", " + pageCount.get() + ", " + getPublishDate().format(dateFormat);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getPageCount() {
        return pageCount.get();
    }

    public SimpleIntegerProperty pageCountProperty() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount.set(pageCount);
    }

    public LocalDate getPublishDate() {
        return publishDate.get();
    }

    public SimpleObjectProperty<LocalDate> publishDateProperty() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate.set(publishDate);
    }



}


