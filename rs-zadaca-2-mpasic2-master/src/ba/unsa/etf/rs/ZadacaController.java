package ba.unsa.etf.rs;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;

public class ZadacaController {
public TextField fldText;
public Button B1,B2,B3,B4,B5,B6,B7,B8,B9,B0;

    public void Napisi0(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 0);
    }

    public void Napisi1(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 1);
    }

    public void Napisi2(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 2);
    }

    public void Napisi3(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 3);
    }

    public void Napisi4(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 4);
    }

    public void Napisi5(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 5);
    }

    public void Napisi6(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 6);
    }

    public void Napisi7(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 7);
    }

    public void Napisi8(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 8);
    }

    public void Napisi9(ActionEvent actionEvent) {
        fldText.setText(fldText.getText() + 9);
    }
}
