package zajecia2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.util.UUID;

public class Controller {
    @FXML
    Circle kolo1;
    @FXML
    Label labelka;

    public void powiekszKolo() {
        double prom = kolo1.getRadius();
        kolo1.setRadius(prom * 1.1);
    }

    public void pomniejszKolo() {
        kolo1.setRadius(0.9 * kolo1.getRadius());
    }

    public void buttonInMenuClicked() {
        labelka.setText(UUID.randomUUID().toString().substring(0,5));
    }
}
