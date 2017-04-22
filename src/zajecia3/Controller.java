package zajecia3;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    DatePicker dateFrom;
    @FXML
    DatePicker dateTo;
    @FXML
    TextField poleWyniku;

    public void foo() {
        System.out.println("hi!");
    }


    void wait(int milisekund) {
        try {
            Thread.sleep(milisekund);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dniMiedzyDatami() {
        System.out.println(dateFrom.getValue());

        String dataJakoTekst = dateFrom.getValue().toString();
        poleWyniku.setText(dataJakoTekst);
    }

    public void pomniejszKolo() {
    }

    public void buttonInMenuClicked() {
    }
}
