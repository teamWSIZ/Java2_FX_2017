package zajecia3;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Controller {
    @FXML
    DatePicker dateFrom;
    @FXML
    DatePicker dateTo;
    @FXML
    TextField poleWyniku;
    @FXML
    TextField dniRoboczych;

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


        LocalDate from = dateFrom.getValue();
        LocalDate to = dateTo.getValue();

        int x = 0;
        int roboczych = 0;
        while (to.isAfter(from)) {
            x++;
            DayOfWeek dzien = from.getDayOfWeek();
            roboczych++;
            if (dzien.equals(DayOfWeek.SUNDAY) || dzien.equals(DayOfWeek.SATURDAY))
                roboczych--;
            from = from.plusDays(1);
        }
        poleWyniku.setText("" + x);
        dniRoboczych.setText("" + roboczych);

    }

    public void pomniejszKolo() {
    }

    public void buttonInMenuClicked() {
    }
}
