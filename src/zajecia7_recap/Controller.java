package zajecia7_recap;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Controller {
    @FXML
    Canvas mycanvas;


    public void sayIt() {
        System.out.println("sayit");
    }

    public void showDialog() {
        System.out.println("dialog");
    }

    public void alertujUsera() {
        System.out.println("alert!");
    }

    public void showPicture() {
        Image veigar = new Image(getClass().getResourceAsStream("res/Veigar-icon.png"));
        GraphicsContext gc = mycanvas.getGraphicsContext2D();
        gc.drawImage(veigar, 10, 50, 250, 250);

    }
}
