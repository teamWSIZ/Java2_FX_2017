package zajecia5;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;


public class Controller {
    @FXML
    TextArea oknoTekstowe;

    @FXML
    Menu toolsMenu;

    public void sayIt() {
        System.out.println("It");
    }

    public void zmienNaDuze() {
        String tekst = oknoTekstowe.getText();
        tekst = tekst.toUpperCase();
        oknoTekstowe.setText(tekst);

        boolean isDisabled = toolsMenu.isDisable();
        if (isDisabled) {
            toolsMenu.setDisable(false);
        } else {
            toolsMenu.setDisable(true);
        }

    }
}
