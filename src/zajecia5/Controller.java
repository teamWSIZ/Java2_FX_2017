package zajecia5;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;


public class Controller {
    @FXML
    TextArea oknoTekstowe;

    @FXML
    Menu toolsMenu;

    @FXML
    Canvas mycanvas;

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

        //Użycie canvasu: 
        GraphicsContext gc = mycanvas.getGraphicsContext2D();
        int size = 30;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gc.fillRoundRect(5 + i * 50, 5 + j * 50, size, size, 10, 10);
            }
        }
//        Rectangle rr = new Rectangle(10, 10, 200, 100);
//        gc.


    }

    public void alertujUsera() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog!");
        alert.setHeaderText("Some header");
        //todo: wyświetla narazie tylko jedną linijkę
        alert.setContentText("Lorem ipsum dolor sit amet enim. Etiam ullamcorper. Suspendisse a pellentesque dui, non felis. Maecenas malesuada elit lectus felis, malesuada ultricies. Curabitur et ligula. Ut molestie a, ultricies porta urna. Vestibulum commodo volutpat a, convallis ac, laoreet enim. Phasellus fermentum in, dolor. Pellentesque facilisis. Nulla imperdiet sit amet magna. Vestibulum dapibus, mauris nec malesuada fames ac turpis velit, rhoncus eu, luctus et interdum adipiscing wisi. Aliquam erat ac ipsum. Integer aliquam purus. ");
        alert.showAndWait();
        //more at: http://code.makery.ch/blog/javafx-dialogs-official/
    }
}
