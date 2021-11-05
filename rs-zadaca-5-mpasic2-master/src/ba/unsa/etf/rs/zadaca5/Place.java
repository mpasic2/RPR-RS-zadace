package ba.unsa.etf.rs.zadaca5;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Place {
    //id int pk, name text, postal_number text
    SimpleIntegerProperty id;
    SimpleStringProperty name;
    SimpleStringProperty postalNumber;

    public Place(int id, String name, String postalNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.postalNumber =  new SimpleStringProperty(postalNumber);
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

    public String getPostalNumber() {
        return postalNumber.get();
    }

    public SimpleStringProperty postalNumberProperty() {
        return postalNumber;
    }

    public void setPostalNumber(String postalNumber) {
        this.postalNumber.set(postalNumber);
    }
}
