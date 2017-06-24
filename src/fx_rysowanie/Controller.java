package fx_rysowanie;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class Controller {
    @FXML
    Canvas mycanvas;

    @FXML
    Stage stage;

    public void drawOnCanvas() {
        //Użycie canvasu:
        GraphicsContext gc = mycanvas.getGraphicsContext2D();
        gc.strokeRoundRect(0, 0, 200, 300, 10, 10);
        gc.strokeLine(0, 0, 300, 310);
    }

}
