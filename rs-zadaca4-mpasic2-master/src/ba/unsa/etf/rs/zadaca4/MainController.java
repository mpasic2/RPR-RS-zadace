package ba.unsa.etf.rs.zadaca4;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainController implements Initializable {
    public TableView<Book> tblBooks;
    public TableColumn<Book,String> colAuthor;
    public TableColumn<Book,String> colTitle;
    public TableColumn<Book, LocalDate> colPublishDate;
    public Label statusMsg;
    private LibraryDAO biblioteka;
    

    public MainController(LibraryDAO model) {
        this.biblioteka=model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colPublishDate.setCellFactory(column -> {
            return new TableCell<Book, LocalDate>() {
                @Override
                protected void updateItem(LocalDate datumSadasnji, boolean daLiJePrazno) {
                    super.updateItem(datumSadasnji, daLiJePrazno);

                    if (datumSadasnji == null || daLiJePrazno==true) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(datumSadasnji.format(Book.dateFormat));
                    }
                }
            };
        });





                    tblBooks.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2) {
                biblioteka.setCurrentBook(tblBooks.getSelectionModel().getSelectedItem());
                /*try {
                    //nesta
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });




        biblioteka.clearAll();
        biblioteka.defaultData();
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));


        tblBooks.setItems(biblioteka.getBooks());
        tblBooks.getSelectionModel().selectedItemProperty().addListener((obs, stara, nova) -> {
            biblioteka.setCurrentBook(nova);
            tblBooks.refresh();
        });




    }

    /*private void dugmeAdd(ActionEvent event){

    }
    private void dugmeChange(ActionEvent event){

    }*/

    public void dugmeDelete(javafx.event.ActionEvent actionEvent) {
        if(biblioteka.getCurrentBook()!=null){
            Alert prozorcic = new Alert(Alert.AlertType.CONFIRMATION);
            String labela = "Deleting book.";
            statusMsg.setText(labela);
            Optional<ButtonType> tipDugmenceta = prozorcic.showAndWait();
            if(tipDugmenceta.get()==ButtonType.OK){
                labela = "Book deleted.";
                statusMsg.setText(labela);
                biblioteka.deleteBook();
            }
            else {
                labela = "Book not deleted.";
                statusMsg.setText(labela);
            }
            prozorcic.close();
        }
    }

    public void izlazakIzProzora(ActionEvent actionEvent) {
        biblioteka.deleteInstance();
        Stage zatvaranjePoruka=(Stage)statusMsg.getScene().getWindow();
        zatvaranjePoruka.close();

    }

    public void otvoriProzor(ActionEvent actionEvent) throws IOException {
        //Stage scena = new Stage();
        //Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml.about.fxml"));
        Stage noviProzor = new Stage();
        Parent roditelj = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"));
        noviProzor.setTitle("Informacije o programu");
        Scene scene = new  Scene(roditelj, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        noviProzor.setScene(scene);
        noviProzor.show();
    }

    public void ispisiKnjige(ActionEvent actionEvent) {
        System.out.printf("Books are:\n");
        String knjige = biblioteka.getBookList();
        System.out.printf(knjige);
    }

    public void dugmeAdd(ActionEvent actionEvent) throws IOException {
        String labela = "Adding new book.";
        statusMsg.setText(labela);
        Book knjigica = null;
        EditController kontroler = new EditController(knjigica);
        Stage najnovijiProzor = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editbook.fxml"));
        loader.setController(kontroler);
        Parent root = loader.load();
        najnovijiProzor.setTitle("Add/Edit book");
        najnovijiProzor.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        najnovijiProzor.showAndWait();

        if(kontroler.brojacanVarijabla>0){
            labela="Book added.";
            statusMsg.setText(labela);
            Book novaKnjiga = kontroler.getBook();
            biblioteka.addBook(novaKnjiga);

        }
        else{
            labela = "Book not added.";
            statusMsg.setText(labela);
        }
    }

    public void dugmeChange(ActionEvent actionEvent) throws IOException {

        if (biblioteka.getCurrentBook() != null) {
            String labela = "Editing book.";
            statusMsg.setText(labela);

            Book knjigica = biblioteka.getCurrentBook();
            EditController kontroler = new EditController(knjigica);
            Stage najnovijiProzor = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editbook.fxml"));
            loader.setController(kontroler);
            Parent root = loader.load();
            najnovijiProzor.setTitle("Add/Edit book");
            najnovijiProzor.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            najnovijiProzor.showAndWait();
            biblioteka.setCurrentBook(knjigica);

            if (kontroler.brojacanVarijabla > 0) {

                labela = "Book edited.";
                statusMsg.setText(labela);
                Book novaKnjiga = kontroler.getBook();
                biblioteka.updateCurrentBook(novaKnjiga);

            } else {
                labela = "Book not edited.";
                statusMsg.setText(labela);
            }
        }
    }
}
