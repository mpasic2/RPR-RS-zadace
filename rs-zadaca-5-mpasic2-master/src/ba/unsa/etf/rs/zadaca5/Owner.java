package ba.unsa.etf.rs.zadaca5;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Owner {
    //id int pk, name text, surname text, parent_name text, date_of_birth date, place_of_birth int fk, living_address text, living_place int fk, jmbg text

    SimpleIntegerProperty id;
    SimpleStringProperty name;
    SimpleStringProperty surname;
    SimpleStringProperty parentName;
    SimpleObjectProperty<LocalDate> dateOfBirth;
    SimpleObjectProperty<Place> placeOfBirth;
    SimpleObjectProperty<Place> livingPlace;
    SimpleStringProperty livingAddress;
    SimpleStringProperty jmbg;

    public Owner(int id, String name, String surname, String parentName, LocalDate dateOfBirth, Place placeOfBirth,  String livingAddress, Place livingPlace, String jmbg) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.parentName = new SimpleStringProperty(parentName);
        this.dateOfBirth = new SimpleObjectProperty<LocalDate>(dateOfBirth);
        this.placeOfBirth = new SimpleObjectProperty<Place>(placeOfBirth);
        this.livingPlace = new SimpleObjectProperty<Place>(livingPlace);
        this.livingAddress = new SimpleStringProperty(livingAddress);
        this.jmbg = new SimpleStringProperty(jmbg);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getParentName() {
        return parentName.get();
    }

    public SimpleStringProperty parentNameProperty() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName.set(parentName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public Place getPlaceOfBirth() {
        return placeOfBirth.get();
    }

    public SimpleObjectProperty<Place> placeOfBirthProperty() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(Place placeOfBirth) {
        this.placeOfBirth.set(placeOfBirth);
    }

    public Place getLivingPlace() {
        return livingPlace.get();
    }

    public SimpleObjectProperty<Place> livingPlaceProperty() {
        return livingPlace;
    }

    public void setLivingPlace(Place livingPlace) {
        this.livingPlace.set(livingPlace);
    }

    public String getLivingAddress() {
        return livingAddress.get();
    }

    public SimpleStringProperty livingAddressProperty() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress.set(livingAddress);
    }

    public String getJmbg() {
        return jmbg.get();
    }

    public SimpleStringProperty jmbgProperty() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg.set(jmbg);
    }
}
