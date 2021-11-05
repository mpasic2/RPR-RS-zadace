package ba.unsa.etf.rs.zadaca3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KorisnikController {
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public Slider sliderGodinaRodjenja;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;
    public PasswordField fldPasswordRepeat;
    public CheckBox cbAdmin;

    private KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());
        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);
            listKorisnici.refresh();
         });

        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(!(newKorisnik instanceof Administrator))cbAdmin.setSelected(false);
            else cbAdmin.setSelected(true);

            if (oldKorisnik != null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty() );
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty() );
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty() );
                sliderGodinaRodjenja.valueProperty().unbindBidirectional(oldKorisnik.godinaRodjenjaProperty());
                fldUsername.textProperty().unbindBidirectional(oldKorisnik.usernameProperty() );
                fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty() );


            }
            if (newKorisnik == null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                sliderGodinaRodjenja.setValue(2000);
                fldUsername.setText("");
                fldPassword.setText("");
                fldPasswordRepeat.setText("");
                cbAdmin.setSelected(false);
            }
            else {
                fldIme.textProperty().bindBidirectional( newKorisnik.imeProperty() );
                fldPrezime.textProperty().bindBidirectional( newKorisnik.prezimeProperty() );
                fldEmail.textProperty().bindBidirectional( newKorisnik.emailProperty() );
                sliderGodinaRodjenja.valueProperty().bindBidirectional(newKorisnik.godinaRodjenjaProperty());
                fldUsername.textProperty().bindBidirectional( newKorisnik.usernameProperty() );
                fldPassword.textProperty().bindBidirectional( newKorisnik.passwordProperty() );
                fldPasswordRepeat.textProperty().bindBidirectional(newKorisnik.passwordProperty());
                fldPasswordRepeat.textProperty().unbindBidirectional(newKorisnik.passwordProperty());

            }
        });

        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && newIme.matches("^[A-Za-z\\s\\-]*$") && newIme.length()>2) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            }

            else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && newIme.matches("^[A-Za-z\\s\\-]*$") && newIme.length()>2) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            }

            else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && newIme.matches("^(.+)@(.+$)")) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && newIme.matches("^[0-9A-Za-z_]*$") && newIme.length()<=16) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            if(model.getTrenutniKorisnik()!=null) {
                model.getTrenutniKorisnik().setPassword(newIme);
                if(newIme.isEmpty() || !newIme.equals(fldPasswordRepeat.getText()) || !model.getTrenutniKorisnik().checkPassword()) {
                    fldPassword.getStyleClass().removeAll("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeNijeIspravno");

                } else {
                    fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPassword.getStyleClass().add("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeIspravno");
                }
            }
        });

        fldPasswordRepeat.textProperty().addListener((obs, oldIme, newIme) -> {

            if(model.getTrenutniKorisnik()!=null) {

                if(newIme.isEmpty() || !newIme.equals(fldPassword.getText()) || !model.getTrenutniKorisnik().checkPassword()) {
                    fldPassword.getStyleClass().removeAll("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeNijeIspravno");

                } else {
                    fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPassword.getStyleClass().add("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().add("poljeIspravno");
                }
            }
        });
       // cbAdmin.selectedProperty().addListener((obs,oldVal,newVal) -> {
        /*if (model.getTrenutniKorisnik() != null){
            Korisnik novi = model.getTrenutniKorisnik();
            if (cbAdmin.isSelected()) {
                Korisnik admin = new Administrator(novi.getIme(), novi.getPrezime(), novi.getEmail(), novi.getUsername(), novi.getPassword());
                model.getKorisnici().set(model.getKorisnici().indexOf(model.getTrenutniKorisnik()),admin);
            } else {
                Korisnik stari = new Korisnik(novi.getIme(), novi.getPrezime(), novi.getEmail(), novi.getUsername(), novi.getPassword());
                model.getKorisnici().set(model.getKorisnici().indexOf(model.getTrenutniKorisnik()),stari);

            }
        }

        });*/

    }

    public void dodajAction(ActionEvent actionEvent) {
        model.getKorisnici().add(new Korisnik("", "", "", "", ""));
        listKorisnici.getSelectionModel().selectLast();
    }

    public void obrisiAction(ActionEvent actionEvent) {
        model.getKorisnici().remove(model.getTrenutniKorisnik());
        listKorisnici.getSelectionModel().selectLast();
    }

    public void krajAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public static String shuffleString(String ulaz) {
        List<String> slova = Arrays.asList(ulaz.split(""));
        Collections.shuffle(slova);
        StringBuilder bilder = new StringBuilder();
        for (String pomocni : slova) {
            bilder.append(pomocni);
        }
        return bilder.toString();
    }

    public void administratorAction(ActionEvent actionEvent){
        if (model.getTrenutniKorisnik() != null){
            Korisnik novi = model.getTrenutniKorisnik();
            if (cbAdmin.isSelected()) {
                Korisnik admin = new Administrator(novi.getIme(), novi.getPrezime(), novi.getEmail(), novi.getUsername(), novi.getPassword());
                model.getKorisnici().set(model.getKorisnici().indexOf(model.getTrenutniKorisnik()),admin);
            } else {
                Korisnik stari = new Korisnik(novi.getIme(), novi.getPrezime(), novi.getEmail(), novi.getUsername(), novi.getPassword());
                model.getKorisnici().set(model.getKorisnici().indexOf(model.getTrenutniKorisnik()),stari);

            }
        }
    }

    public void generisiAction(ActionEvent actionEvent){

        if(fldIme.getLength()<1){
            fldUsername.setText(fldPrezime.getText().toLowerCase());
            fldUsername.setText(fldUsername.getText().replaceAll("ž", "z").replaceAll("š", "s").replaceAll("đ", "d").replaceAll("ć", "c").replaceAll("č", "c"));

        }

        else if(model.getTrenutniKorisnik()==null) {
            model.getKorisnici().add(new Korisnik("","","","",""));
            model.setTrenutniKorisnik(4);
            fldUsername.setText(fldIme.getText().toLowerCase().charAt(0) + fldPrezime.getText().toLowerCase());
            fldUsername.setText(fldUsername.getText().replaceAll("ž", "z").replaceAll("š", "s").replaceAll("đ", "d").replaceAll("ć", "c").replaceAll("č", "c"));
        }
        else{
            fldUsername.setText(fldIme.getText().toLowerCase().charAt(0) + fldPrezime.getText().toLowerCase());
            fldUsername.setText(fldUsername.getText().replaceAll("ž", "z").replaceAll("š", "s").replaceAll("đ", "d").replaceAll("ć", "c").replaceAll("č", "c"));

        }

        String velikaSlova = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String brojevi = "0123456789";
        String malaSlova = velikaSlova.toLowerCase();
        String znakovi = "!#$%&/()=?*+-.;,:{}@[]|<>^";
        fldPassword.setText("");

        int brojac = 8;
        if(!cbAdmin.isSelected()) {
            while (brojac > 0) {
                int index = (int) (Math.random() * 10);
                if (brojac % 2 == 0) fldPassword.setText(fldPassword.getText() + brojevi.charAt(index));
                else if (brojac % 3 == 0) fldPassword.setText(fldPassword.getText() + velikaSlova.charAt(index));
                else fldPassword.setText(fldPassword.getText() + malaSlova.charAt(index));
                brojac--;
            }
        }
        else {
            while (brojac > 0) {
                int index = (int) (Math.random() * 10);
                if (brojac % 2 == 0) fldPassword.setText(fldPassword.getText() + brojevi.charAt(index));
                else if (brojac % 3 == 0) fldPassword.setText(fldPassword.getText() + velikaSlova.charAt(index));
                else if(brojac % 5 == 0) fldPassword.setText(fldPassword.getText() + malaSlova.charAt(index));
                else fldPassword.setText(fldPassword.getText() + znakovi.charAt(index));
                brojac--;
            }
        }
        fldPassword.setText(shuffleString(fldPassword.getText()));
        fldPasswordRepeat.setText(fldPassword.getText());
        Alert skocniProzor = new Alert(Alert.AlertType.INFORMATION);
        skocniProzor.setContentText("Vaša lozinka glasi " + fldPassword.getText());
        skocniProzor.showAndWait();

    }



}
