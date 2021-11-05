package ba.unsa.etf.rs.zadaca4;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    public Button btnCancel;
    public Button btnOk;
    public TextField fldAuthor;
    public TextField fldTitle;
    public TextField fldIsbn;
    public Spinner spinPageCount;
    public DatePicker dpPublishDate;
    public Book book;
    public int brojacanVarijabla=0;
    private LocalDate trenutnoVrijeme = LocalDate.now();

    public EditController(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        int min = 10;
        int max = 1000;
        int step = 5;
        int brojac = 10;
        //SpinnerModel value = new SpinnerNumberModel(i, min, max, step); NE ZNAM STO NIJE RADILO I MENE ZANIMA
        this.spinPageCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min,max, brojac, step));

        if(book==null){
            fldAuthor.setText("");
            fldIsbn.setText("");
            fldTitle.setText("");
            dpPublishDate.setValue(LocalDate.now());
            spinPageCount.getValueFactory().setValue(10);
        }
        else{
            fldAuthor.setText(book.getAuthor());
            fldIsbn.setText(book.getIsbn());
            fldTitle.setText(book.getTitle());
            dpPublishDate.setValue(book.getPublishDate());
            spinPageCount.getValueFactory().setValue(book.getPageCount());
        }

        fldAuthor.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldAuthor.getStyleClass().removeAll("poljeNijeIspravno");
                fldAuthor.getStyleClass().add("poljeIspravno");
            }

            else {
                fldAuthor.getStyleClass().removeAll("poljeIspravno");
                fldAuthor.getStyleClass().add("poljeNijeIspravno");
            }
        });


        fldTitle.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldTitle.getStyleClass().removeAll("poljeNijeIspravno");
                fldTitle.getStyleClass().add("poljeIspravno");
            }

            else {
                fldTitle.getStyleClass().removeAll("poljeIspravno");
                fldTitle.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldIsbn.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldIsbn.getStyleClass().removeAll("poljeNijeIspravno");
                fldIsbn.getStyleClass().add("poljeIspravno");
            }

            else {
                fldIsbn.getStyleClass().removeAll("poljeIspravno");
                fldIsbn.getStyleClass().add("poljeNijeIspravno");
            }
        });

        dpPublishDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate danasnjiDatum) {
                if (danasnjiDatum == null) {
                    return "";
                } else {
                    return Book.dateFormat.format(danasnjiDatum);
                }
            }

            @Override
            public LocalDate fromString(String nizKnjiga) {
                if (nizKnjiga == null || nizKnjiga.isEmpty()) {
                        return null;}

                else {
                    return LocalDate.parse(nizKnjiga,Book.dateFormat);
                }
            }
        });

        dpPublishDate.valueProperty().addListener((obs, oldIme, newIme) -> {

            if (newIme.isBefore(trenutnoVrijeme) || newIme.isEqual(trenutnoVrijeme)) {
                dpPublishDate.getStyleClass().removeAll("poljeNijeIspravno");
                dpPublishDate.getStyleClass().add("poljeIspravno");
            }

            else {
                dpPublishDate.getStyleClass().removeAll("poljeIspravno");
                dpPublishDate.getStyleClass().add("poljeNijeIspravno");
            }
        });









    }

    public void okejdugme(javafx.event.ActionEvent actionEvent) {
        if(fldAuthor.getLength()>0 && fldTitle.getLength()>0 && fldIsbn.getLength()>0 && (dpPublishDate.getValue().isBefore(trenutnoVrijeme) || dpPublishDate.getValue().isEqual(trenutnoVrijeme))){
            String pisac = fldAuthor.getText();
            String naslov = fldTitle.getText();
            String isbn = fldIsbn.getText();
            int straniceKnjige = (int) spinPageCount.getValueFactory().getValue();
            LocalDate trenutnoVrijeme = dpPublishDate.getValue();
            book = new Book (pisac,naslov,isbn,straniceKnjige,trenutnoVrijeme);

            brojacanVarijabla++;
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        }
        else{
            book = null;
            if(fldAuthor.getLength()<1)
                fldAuthor.requestFocus();
            else if(fldTitle.getLength()<1)
                fldTitle.requestFocus();
            else if(fldIsbn.getLength()<1)
                fldIsbn.requestFocus();
            else if(dpPublishDate.getValue().isAfter(trenutnoVrijeme))
                dpPublishDate.requestFocus();
        }
    }

    public void zatvaranjeProzora(javafx.event.ActionEvent actionEvent) {
        brojacanVarijabla--;
        book=null;
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }
}
