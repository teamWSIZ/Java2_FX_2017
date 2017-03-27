package zajecia1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class Controller {
    @FXML
    Button mybutton1;
    @FXML
    DatePicker picker;
    @FXML
    LineChart<Double,Double> czart;

    //Funkcja którą można podłączyć pod zdarzenia na scenie
    public void fajnaFunkcja() {
        System.out.println("hello");    //wypisujemy do konsoli by było prościej
        System.out.println(picker.getValue());
    }

    public void drugaFunkcja() {
        System.out.println("!");
    }

    /**
     * Uwaga: wykresu nie udało się zrobić na zajęciach!
     */
    public void showplot() {
        System.out.println("showing plot");

        XYChart.Series series = new XYChart.Series<>();
        ObservableList<XYChart.Data> data = series.getData();
        for (int i = 0; i < 100; i++) {
            //dodawanie danych do wykresu
            data.add(new XYChart.Data(0.1 * i, Math.sin(0.1 * i)));
        }
//        series.getData().add(new XYChart.Data<>(1, 567));
//        series.getData().add(new XYChart.Data<>(2, 567));

        czart.getData().add(series);
    }
}
