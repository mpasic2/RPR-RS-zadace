package ba.unsa.etf.rs.zadaca5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VehicleDAOBase implements VehicleDAO {
    private static Connection connection;
    private PreparedStatement noviUpitOwner, dodajProizvodjaca, dodajMjesto, noviUpitVehicle, noviUpitManufacturer, noviUpitPlace, dohvatiVozila, dohvatiVlasnike, dohvatiMjesto, dohvatiProizvodajca, dodajVlasnika, dodajVozilo, promijeniVozilo, promijeniVlasnika, obrisiVozilo, obrisiVlasnika;

    public VehicleDAOBase() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");
            dohvatiVozila = connection.prepareStatement("SELECT * FROM vehicle ORDER BY id;");
            dohvatiVlasnike = connection.prepareStatement("SELECT * FROM owner ORDER BY id;");
            dohvatiMjesto = connection.prepareStatement("SELECT * FROM place ORDER BY name;");
            dohvatiProizvodajca = connection.prepareStatement("SELECT * FROM manufacturer ORDER BY name;");
            noviUpitOwner = connection.prepareStatement("Select MAX(id)+1 from owner; ");
            noviUpitVehicle = connection.prepareStatement("Select MAX(id)+1 from vehicle; ");
            noviUpitManufacturer = connection.prepareStatement("Select MAX(id)+1 from manufacturer; ");
            noviUpitPlace = connection.prepareStatement("Select MAX(id)+1 from place; ");

            dodajVlasnika = connection.prepareStatement("INSERT INTO owner VALUES(?,?,?,?,?,?,?,?,?);");
            dodajVozilo = connection.prepareStatement("INSERT INTO vehicle VALUES(?,?,?,?,?,?);");
            dodajMjesto = connection.prepareStatement("INSERT INTO place VALUES(?,?,?);");
            dodajProizvodjaca = connection.prepareStatement("INSERT INTO manufacturer VALUES(?,?)");

            promijeniVlasnika = connection.prepareStatement("UPDATE owner SET name=?,surname=?,parent_name=?,date_of_birth=?,place_of_birth=?,living_address=?,living_place=?,jmbg=? WHERE id=?;");
            promijeniVozilo = connection.prepareStatement("UPDATE vehicle SET manufacturer=?,model=?,chasis_number=?,plate_number=?,owner=? WHERE id=?;");

            obrisiVlasnika = connection.prepareStatement("DELETE FROM owner WHERE id=?;");
            obrisiVozilo = connection.prepareStatement("DELETE FROM vehicle WHERE id=?;");


        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Place pronadjiMjesto(ResultSet rs) throws SQLException {

            return new Place(rs.getInt(1),rs.getString(2),rs.getString(3));

    }

    @Override
    public ObservableList<Owner> getOwners() {
        ObservableList<Owner> vlasnik = FXCollections.observableArrayList();
        try {
            ResultSet rs = dohvatiVlasnike.executeQuery();
            while (rs.next()){
                    PreparedStatement prepareStatemant = connection.prepareStatement("SELECT * FROM place WHERE id=?");
                    prepareStatemant.setInt(1,rs.getInt(6));
                    Place rodjenje = pronadjiMjesto(prepareStatemant.executeQuery());
                    prepareStatemant.setInt(1,rs.getInt(8));
                    Place zivi = pronadjiMjesto(prepareStatemant.executeQuery());

                    Owner vlasnici = new Owner(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rodjenje,rs.getString(7),zivi,rs.getString(9));
                    vlasnik.add(vlasnici);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return vlasnik;
    }

    public Manufacturer pronadjiProizvodajca(ResultSet rs) throws SQLException {

        return new Manufacturer(rs.getInt(1),rs.getString(2));

    }
    public Owner pronadjiVlasnik(ResultSet rs) throws SQLException {


        PreparedStatement prepareStatemant = connection.prepareStatement("SELECT * FROM place WHERE id=?");
        prepareStatemant.setInt(1,rs.getInt(6));
        Place rodjenje = pronadjiMjesto(prepareStatemant.executeQuery());
        prepareStatemant.setInt(1,rs.getInt(8));
        Place zivi = pronadjiMjesto(prepareStatemant.executeQuery());

        return new Owner(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rodjenje,rs.getString(7),zivi,rs.getString(9));
    }

    @Override
    public ObservableList<Vehicle> getVehicles() {
        ObservableList<Vehicle> vozila = FXCollections.observableArrayList();
        try {
            ResultSet rs = dohvatiVozila.executeQuery();
            while (rs.next()){
                PreparedStatement prepareStatemant = connection.prepareStatement("SELECT * FROM manufacturer WHERE id=?;");
                PreparedStatement vlasnik = connection.prepareStatement(("SELECT * FROM owner WHERE id=?;"));
                prepareStatemant.setInt(1,rs.getInt(2));
                Manufacturer proizvodjac = pronadjiProizvodajca(prepareStatemant.executeQuery());
                vlasnik.setInt(1,rs.getInt(6));
                Owner boss = pronadjiVlasnik(vlasnik.executeQuery());

                Vehicle vlasnici = new Vehicle(rs.getInt(1),proizvodjac,rs.getString(3),rs.getString(4),rs.getString(5),boss);
                vozila.add(vlasnici);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return vozila;
    }

    @Override
    public ObservableList<Place> getPlaces() {
        ObservableList<Place> mjesta = FXCollections.observableArrayList();

        try {
            ResultSet rs = dohvatiMjesto.executeQuery();

            while(rs.next()) {
                Place lokacije = new Place(rs.getInt(1),rs.getString(2),rs.getString(3));
                mjesta.add(lokacije);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mjesta;
    }

    @Override
    public ObservableList<Manufacturer> getManufacturers() {
        ObservableList<Manufacturer> proizvodjaci = FXCollections.observableArrayList();

        try {
            ResultSet rs = dohvatiProizvodajca.executeQuery();

            while(rs.next()) {
                Manufacturer proizovdj = new Manufacturer(rs.getInt(1),rs.getString(2));
                proizvodjaci.add(proizovdj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proizvodjaci;
    }
    //pomocna funkcija
    public boolean daLiPostojiMjesto(Place mjestoVlasnika){
        for(int i=0;i<getPlaces().size();i++){
            if(mjestoVlasnika.getId()==getPlaces().get(i).getId())
                    return true;
        }
        return false;
    }

    //pomocna funkcija
    public void ubaciMjestoNaPrazno(Place place){
        try {
            ResultSet rs = noviUpitPlace.executeQuery();

            if (rs.next())
                place.setId(rs.getInt(1));
            else
                place.setId(1);

            dodajMjesto.setInt(1,place.getId());
            dodajMjesto.setString(2,place.getName());
            dodajMjesto.setString(3,place.getPostalNumber());

            dodajMjesto.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void changeOwner(Owner owner) {
        if(daLiPostojiMjesto(owner.getPlaceOfBirth()) == false) ubaciMjestoNaPrazno(owner.getPlaceOfBirth());
        if(daLiPostojiMjesto(owner.getLivingPlace()) == false)  ubaciMjestoNaPrazno(owner.getLivingPlace());

        try {

            promijeniVlasnika.setString(1,owner.getName());
            promijeniVlasnika.setString(2,owner.getSurname());
            promijeniVlasnika.setString(3,owner.getParentName());
            promijeniVlasnika.setDate(4, Date.valueOf(owner.getDateOfBirth()));
            promijeniVlasnika.setInt(5,owner.getPlaceOfBirth().getId());
            promijeniVlasnika.setString(6,owner.getLivingAddress());
            promijeniVlasnika.setInt(7,owner.getLivingPlace().getId());
            promijeniVlasnika.setString(8,owner.getJmbg());
            promijeniVlasnika.setInt(9,owner.getId());

            promijeniVlasnika.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOwner(Owner owner) {
        if(daLiPostojiMjesto(owner.getPlaceOfBirth()) == false) ubaciMjestoNaPrazno(owner.getPlaceOfBirth());
        if(daLiPostojiMjesto(owner.getLivingPlace()) == false)  ubaciMjestoNaPrazno(owner.getLivingPlace());

        try {
            ResultSet rs = noviUpitOwner.executeQuery();

            if (rs.next())
                owner.setId(rs.getInt(1));
            else
                owner.setId(1);


            dodajVlasnika.setInt(1,owner.getId());
            dodajVlasnika.setString(2,owner.getName());
            dodajVlasnika.setString(3,owner.getSurname());
            dodajVlasnika.setString(4,owner.getParentName());
            dodajVlasnika.setDate(5, Date.valueOf(owner.getDateOfBirth()));
            dodajVlasnika.setInt(6,owner.getPlaceOfBirth().getId());
            dodajVlasnika.setString(7,owner.getLivingAddress());
            dodajVlasnika.setInt(8,owner.getLivingPlace().getId());
            dodajVlasnika.setString(9,owner.getJmbg());

            dodajVlasnika.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //pomocna funkcija
    public boolean daLiImaVozilo(Owner Vlasnikvozila){
        for(int i=0;i<getVehicles().size();i++){
            if(Vlasnikvozila.getId()==getVehicles().get(i).getOwner().getId())
                return true;
        }
        return false;
    }

    @Override
    public void deleteOwner(Owner owner) {
        if(daLiImaVozilo(owner)) throw new IllegalArgumentException("Nema vozila!");
        else{
            try {
                obrisiVlasnika.setInt(1,owner.getId());
                obrisiVlasnika.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    //pomocna funkcija
    public boolean daLiPostojiVlasnik(Owner vlasnik){
        for(int i=0;i<getOwners().size();i++){
            if(getOwners().get(i).getId() == vlasnik.getId())
                    return true;
        }
        return false;
    }

    //pomocna funkcija
    public void dodajProizovdajcaNaPrazno(Manufacturer proizovdjac){
        try {
            ResultSet rs = noviUpitManufacturer.executeQuery();

            if (rs.next())
                proizovdjac.setId(rs.getInt(1));
            else
                proizovdjac.setId(1);

            dodajProizvodjaca.setInt(1,proizovdjac.getId());
            dodajProizvodjaca.setString(2,proizovdjac.getName());


            dodajProizvodjaca.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //pomocna funkcija
    public boolean daLiPostojProizvodjac(Manufacturer proizvodjac){
        for(int i=0;i<getManufacturers().size();i++){
            if(getManufacturers().get(i).getId() == proizvodjac.getId())
                return true;
        }
        return false;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if(!daLiPostojiVlasnik(vehicle.getOwner())) throw new IllegalArgumentException("Neki tamo izuzetak!");
        if(!daLiPostojProizvodjac(vehicle.getManufacturer())) dodajProizovdajcaNaPrazno(vehicle.getManufacturer());

        try {
            ResultSet rs = noviUpitVehicle.executeQuery();

            if (rs.next())
                vehicle.setId(rs.getInt(1));
            else
                vehicle.setId(1);


            dodajVozilo.setInt(1,vehicle.getId());
            dodajVozilo.setInt(2,vehicle.getManufacturer().getId());
            dodajVozilo.setString(3,vehicle.getModel());
            dodajVozilo.setString(4,vehicle.getChasisNumber());
            dodajVozilo.setString(5, vehicle.getPlateNumber());
            dodajVozilo.setInt(6,vehicle.getOwner().getId());


            dodajVozilo.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void changeVehicle(Vehicle vehicle) {
        if(!daLiPostojiVlasnik(vehicle.getOwner())) throw new IllegalArgumentException("Neki tamo izuzetak!");
        if(!daLiPostojProizvodjac(vehicle.getManufacturer())) dodajProizovdajcaNaPrazno(vehicle.getManufacturer());

        try {



            promijeniVozilo.setInt(6,vehicle.getId());
            promijeniVozilo.setInt(1,vehicle.getManufacturer().getId());
            promijeniVozilo.setString(2,vehicle.getModel());
            promijeniVozilo.setString(3,vehicle.getChasisNumber());
            promijeniVozilo.setString(4, vehicle.getPlateNumber());
            promijeniVozilo.setInt(5,vehicle.getOwner().getId());


            promijeniVozilo.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(Vehicle vehicle) {
        try {
            obrisiVozilo.setInt(1,vehicle.getId());
            obrisiVozilo.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
