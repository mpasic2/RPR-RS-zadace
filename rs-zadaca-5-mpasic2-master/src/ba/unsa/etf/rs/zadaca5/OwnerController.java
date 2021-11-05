package ba.unsa.etf.rs.zadaca5;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OwnerController implements Initializable {
    public Owner vlasnik;
    public VehicleDAO voziloDAO;
    public TextField nameField;
    public TextField surnameField;
    public TextField parentNameField;
    public TextField addressField;
    public TextField jmbgField;
    public TextField postalNumberField;
    public DatePicker dateField;
    public ComboBox placeOfBirth;
    public ComboBox addressPlace;
    public Button okButton;
    public Button cancelButton;
    private LocalDate trenutnoVrijeme = LocalDate.now();

    public OwnerController(VehicleDAO voziloDAO, Owner vlasnik) {
        this.vlasnik = vlasnik;
        this.voziloDAO = voziloDAO;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(vlasnik!=null) {
            nameField.setText(vlasnik.getName());
            surnameField.setText(vlasnik.getSurname());
            parentNameField.setText(vlasnik.getParentName());
            addressField.setText(vlasnik.getLivingAddress());
            jmbgField.setText(vlasnik.getJmbg());
            postalNumberField.setText(vlasnik.getLivingPlace().getPostalNumber());
            dateField.setValue(vlasnik.getDateOfBirth());
            for(int i=0;i<voziloDAO.getPlaces().size();i++){
                if(voziloDAO.getPlaces().get(i).getId() == vlasnik.getPlaceOfBirth().getId())
                    placeOfBirth.getSelectionModel().select(voziloDAO.getPlaces().get(i));
            }
            for(int i=0;i<voziloDAO.getPlaces().size();i++){
                if(voziloDAO.getPlaces().get(i).getId() == vlasnik.getLivingPlace().getId())
                    addressPlace.getSelectionModel().select(voziloDAO.getPlaces().get(i));
            }

        }
    }


    public void dugmeokaction(ActionEvent actionEvent) {
        boolean sveOk = true;

        //namefild validacija
        if (nameField.getText().isEmpty()) {
            nameField.getStyleClass().removeAll("poljeIspravno");
            nameField.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        } else {
            nameField.getStyleClass().removeAll("poljeNijeIspravno");
            nameField.getStyleClass().add("poljeIspravno");
        }


        //surnamefild validacija
        if (surnameField.getText().isEmpty()) {
            surnameField.getStyleClass().removeAll("poljeIspravno");
            surnameField.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        } else {
            surnameField.getStyleClass().removeAll("poljeNijeIspravno");
            surnameField.getStyleClass().add("poljeIspravno");
        }


        //parentnamefild validacija
        if (parentNameField.getText().isEmpty()) {
            parentNameField.getStyleClass().removeAll("poljeIspravno");
            parentNameField.getStyleClass().add("poljeNijeIspravno");
        } else {
            parentNameField.getStyleClass().removeAll("poljeNijeIspravno");
            parentNameField.getStyleClass().add("poljeIspravno");
        }


        //addresfild validacija
        if (addressField.getText().isEmpty()) {
            addressField.getStyleClass().removeAll("poljeIspravno");
            addressField.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        } else {
            addressField.getStyleClass().removeAll("poljeNijeIspravno");
            addressField.getStyleClass().add("poljeIspravno");
        }


        //mjesto rodjenja validacija
        if (placeOfBirth.getSelectionModel().getSelectedIndex()<=-1) {
            placeOfBirth.getStyleClass().removeAll("poljeIspravno");
            placeOfBirth.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        } else {
            placeOfBirth.getStyleClass().removeAll("poljeNijeIspravno");
            placeOfBirth.getStyleClass().add("poljeIspravno");
        }


        //mjesto prebivalista validacija
        if (addressPlace.getSelectionModel().getSelectedIndex()<=-1) {
            addressPlace.getStyleClass().removeAll("poljeIspravno");
            addressPlace.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        } else {
            addressPlace.getStyleClass().removeAll("poljeNijeIspravno");
            addressPlace.getStyleClass().add("poljeIspravno");
        }


        //datum validacija
        if(dateField.getValue().isBefore(trenutnoVrijeme) || dateField.getValue().isEqual(trenutnoVrijeme)){
            dateField.getStyleClass().removeAll("poljeNijeIspravno");
            dateField.getStyleClass().add("poljeIspravno");

        } else {
            dateField.getStyleClass().removeAll("poljeIspravno");
            dateField.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        }

        //pomocna funkcija koja racuna jmbg


        //jmbg validacija
        if(jmbgField.getLength()!=13){
            jmbgField.getStyleClass().removeAll("poljeIspravno");
            jmbgField.getStyleClass().add("poljeNijeIspravno");
            sveOk=false;
        } else {
            jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
            jmbgField.getStyleClass().add("poljeIspravno");
        }


        if (!sveOk) return;



    }


    public void cancelDrugmeAction(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }



}
