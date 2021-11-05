package ba.unsa.etf.rs.zadaca5;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {

    //id int pk, manufactrurer int fk, model text, chasis_number text, plate_number text, owner int fk
    SimpleIntegerProperty id;
    SimpleObjectProperty<Manufacturer> manufacturer;
    SimpleStringProperty model;
    SimpleStringProperty chasisNumber;
    SimpleStringProperty plateNumber;
    SimpleObjectProperty<Owner> owner;

    public Vehicle(int id, Manufacturer manufactrurer, String text, String chasisNumber, String plateNumber, Owner owner) {
        this.id = new SimpleIntegerProperty(id);
        this.manufacturer = new SimpleObjectProperty<Manufacturer>(manufactrurer);
        this.model = new SimpleStringProperty(text);
        this.chasisNumber = new SimpleStringProperty(chasisNumber);
        this.plateNumber = new SimpleStringProperty(plateNumber);
        this.owner = new SimpleObjectProperty<Owner>(owner);
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


    public String getModel() { return model.get(); }

    public SimpleStringProperty modelProperty() {return model; }

    public void setModel(String model) {this.model.set(model); }

    public String getChasisNumber() {
        return chasisNumber.get();
    }

    public SimpleStringProperty chasisNumberProperty() {
        return chasisNumber;
    }

    public void setChasisNumber(String chasisNumber) {
        this.chasisNumber.set(chasisNumber);
    }

    public String getPlateNumber() {
        return plateNumber.get();
    }

    public SimpleStringProperty plateNumberProperty() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber.set(plateNumber);
    }

    public Owner getOwner() {
        return owner.get();
    }

    public SimpleObjectProperty<Owner> ownerProperty() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner.set(owner);
    }

    public Manufacturer getManufacturer() { return manufacturer.get(); }

    public SimpleObjectProperty<Manufacturer> manufacturerProperty() { return manufacturer; }

    public void setManufacturer(Manufacturer manufacturer) { this.manufacturer.set(manufacturer); }
}
