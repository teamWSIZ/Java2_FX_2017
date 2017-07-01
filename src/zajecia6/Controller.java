package zajecia6;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.File;
import java.util.*;


public class Controller {
    @FXML
    TextArea oknoTekstowe;

    @FXML
    Menu toolsMenu;

    @FXML
    Canvas mycanvas;

    @FXML
    Stage stage;


    List<Image> ikony;  //kolekcja obrazków
    List<Sprite> sprites;   //kolekcja obiektów odpowiadających postaciom gry

    //selekcja postaci
    int selectedSprite = 0;    //-1: żaden nie jest wybrany
    int offsetX = 0;
    int offsetY = 0;


    //stałe w programie
    int ICON_SIZE = 120;
    int FRAMES_PER_SECOND = 30;


    class Sprite {
        int x, y;  //współrzędne lewego górnego rogu ikony
        int iconNumber;
        boolean isSelected;

        //drukuje sprite'a na `gc` z lewym górnym rogiem w (x,y)
        void printSprite(GraphicsContext gc) {
            printIconWithRectangle(gc, ikony.get(iconNumber), x, y, isSelected);
        }

        //sprawdza, czy sprite pokrywa punkt (xx,yy)
        boolean isHitAt(int xx, int yy) {
            if (xx<x) return false;
            if (xx>x+ICON_SIZE) return false;
            if (yy<y) return false;
            if (yy>y+ICON_SIZE) return false;
            return true;
        }

        //sprawdza czy obecny sprite koliduje z innym spritem `s`
        // (czyli czy prostokąty mają punkt wspólny)
        boolean collidesWith(Sprite s) {
            //
            return true;
        }

    }




    public Controller() {
        List<String> iconFiles = new ArrayList<>();
        iconFiles.add("Lulu-Dragon-Trainer-icon.png");
        iconFiles.add("Quinn-Valor-icon.png");
        iconFiles.add("Yasuo-icon.png");
        iconFiles.add("Veigar-icon.png");

        ikony = new ArrayList<>();
        for(String name : iconFiles) {
            ikony.add(loadImage(name));
        }
    }

    public void sayIt() {
        System.out.println("It");
    }

    public void zmienNaDuze() {
        String tekst = oknoTekstowe.getText();
        tekst = tekst.toUpperCase();
        oknoTekstowe.setText(tekst);
    }

    public void disableToolsMenu() {
        boolean isDisabled = toolsMenu.isDisable();
        if (isDisabled) {
            toolsMenu.setDisable(false);
        } else {
            toolsMenu.setDisable(true);
        }
    }

    private void printIconWithRectangle(GraphicsContext gc, Image i, int x, int y, boolean isSelected) {
        gc.drawImage(i, x+5, y+5, ICON_SIZE-5, ICON_SIZE-5);
        if (isSelected) {
            gc.setLineWidth(3);
            gc.setStroke(Color.YELLOWGREEN);
        } else {
            gc.setLineWidth(1);
            gc.setStroke(Color.GRAY);
        }
        gc.strokeRoundRect(x, y, ICON_SIZE, ICON_SIZE, 10, 10);
        gc.setStroke(Color.BLACK);

    }

    private void repaintScene(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 800);
        gc.setFill(Color.color(0.1, 0.7, 0.5, 0.5));
        gc.fillArc(10, 110, 300, 300, 45, 240, ArcType.OPEN);
        for(Sprite s : sprites) {
            s.printSprite(gc);
        }
    }


    //tworzenie postaci gry (sprite'ów)
    private void initializeSprintes() {
        sprites = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Sprite s = new Sprite();
            s.x = 135 * i;
            s.y = 55;
            s.iconNumber = i;
            sprites.add(s);
        }
    }

    //Tworzenie periodycznej animacji
    private void initializeGameAnimation(GraphicsContext gc) {
        //Poniższy timelinie będzie odpalał "EventHandler" wybraną liczbę razy na sekunde
        Timeline mainTimeline = new Timeline(new KeyFrame(Duration.millis(1000 / FRAMES_PER_SECOND),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                System.out.println("called: " + new Date());
                        repaintScene(gc);
                    }
                }));
        mainTimeline.setCycleCount(Timeline.INDEFINITE);
        mainTimeline.play();
    }

    private void initializeMouseEvents() {
        // Clear away portions as the user drags the mouse
        mycanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        Sprite lulu = sprites.get(selectedSprite);
                        lulu.x = (int) (t.getX() + offsetX);
                        lulu.y = (int) (t.getY() + offsetY);
//                        repaintScene(gc);
                    }
                });

        // Fill the Canvas with a Blue rectnagle when the user double-clicks
        mycanvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        //współrzędne kliknięcia
                        int xx = (int)t.getX();
                        int yy = (int)t.getY();
                        for (int spriteId = 0; spriteId < sprites.size(); spriteId++) {
                            if (sprites.get(spriteId).isHitAt(xx,yy)) {
                                System.out.println("Sprite trafiony: " + spriteId);
                                selectSpriteHitAt(spriteId, xx, yy);
                            }
                        }

                        if (t.getClickCount() >1) {
//                            reset(canvas, Color.BLUE);
                        }
                    }
                });

    }


    //Podłączenie eventów pod klawisze
    private void initializeKeyboardEvents() {
        mycanvas.getScene().setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            System.out.println(code);
            if (event.getCode()== (KeyCode.DOWN)) {
                System.out.println("Hit arrow down");
            }
            if (event.getCode()== (KeyCode.UP)) {
                System.out.println("Hit arrow up");
            }
        });
    }

    public void startGame() {
        //Użycie canvasu:
        GraphicsContext gc = mycanvas.getGraphicsContext2D();
        initializeSprintes();
        initializeMouseEvents();
        initializeKeyboardEvents();
        initializeGameAnimation(gc);

    }

    //będzie wykonywana przy selekcji sprite'a; xx,yy pozwolą ustawić za który punkt sprite ma być ciągnięty
    private void selectSpriteHitAt(int spriteId, int xx, int yy) {
        selectedSprite = spriteId;
        offsetX = sprites.get(spriteId).x - xx;
        offsetY = sprites.get(spriteId).y - yy;
        for(Sprite s : sprites) {
            s.isSelected = false;
        }
        sprites.get(selectedSprite).isSelected = true;
    }

    public void animateLulu() {
        GraphicsContext gc = mycanvas.getGraphicsContext2D();
        DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();
        DoubleProperty phi = new SimpleDoubleProperty();

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(x, 0),
                        new KeyValue(y, 0),
                        new KeyValue(phi, 0)
                ),
                new KeyFrame(Duration.millis(2500),
                        new KeyValue(x, 200),
                        new KeyValue(y, 200),
                        new KeyValue(phi, 180)
                )
        );

        timeline.setAutoReverse(true);
        timeline.setCycleCount(12);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Image i = ikony.get(0);
                gc.clearRect(0, 0, 500, 500);
                //show icon on canvas
                gc.drawImage(i, x.doubleValue(), y.doubleValue(), 300, 300);

            }
        };
        timer.start();
        timeline.play();
    }

    public void alertujUsera() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog!");
        alert.setHeaderText("Some header");
        //todo: wyświetla narazie tylko jedną linijkę
        alert.setContentText("Lorem ipsum dolor sit amet enim. Etiam ullamcorper. ");
        alert.showAndWait();
        //more at: http://code.makery.ch/blog/javafx-dialogs-official/
    }

    public void myOpenFile() {
        File f = new FileChooser().showOpenDialog(stage);
        System.out.println(f.getAbsolutePath());
    }

    public void dodajUsera() {
        Dialog<String> dialog = new Dialog<>();
        //title...
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        //GridPane   ma mieć (3, 2)
        GridPane grid = new GridPane();
        //Dostawić elementy

        dialog.getDialogPane().setContent(grid);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
        }
    }

    public void loginDialog() {
        //Tworzenie obiektu dialogowego:
        // parametrem jest wynik który dostaniemy z dialogu (tutaj parę dwóch napisów)
        // (gdybyśmy chcieli pojedynczą np. liczbę, to byłoby Dialog<Integer>)
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Log in");
        dialog.setHeaderText("Logowanie do systemu");
        //Customizacja buttonów okna
        ButtonType naszeLogowanie = new ButtonType("Zaloguj", ButtonBar.ButtonData.APPLY);
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.PREVIOUS, ButtonType.YES.NEXT, ButtonType.FINISH, ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().addAll(naszeLogowanie, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField userField = new TextField();
        userField.setPromptText("User");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Hasło");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(userField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        /*
         * Wykonanie operacji przez wątek UI (JavaFX application thread)
         * lepsze niż samo userField.requestFocus();
         *
         * See also: https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx
         */
        Platform.runLater(userField::requestFocus);

        //Te pola mają wiele "properties", które są "ObservableValue"; można zaczepiać listenery
//        userField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.equals("Abra Kadabra")) {
//                System.out.println("Powitał Kadabra!");
//            }
//        });

//        userField.borderProperty().addListener((observable, oldValue, newValue) -> {
//            Border xxx = oldValue;
//        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == naszeLogowanie) {
                return new Pair<>(userField.getText(), passField.getText());
            } else if (dialogButton==ButtonType.CANCEL) {
                System.out.println("Cancel button clicked");
            }
            return null;
        });

        //Ten kod pokazuje dialog na ekranie, i zmusza usera do reakcji
        Optional<Pair<String, String>> result = dialog.showAndWait();

        if (result.isPresent()) {
            Pair<String, String> ppp = result.get();
            System.out.println("Username:" + ppp.getKey());
            System.out.println("Password:" + ppp.getValue());
        }

        //todo: "new game dialog" or "num keyboard dialog"
        //todo: akcje pod klawiszami

    }

    //private
    Image loadImage(String name) {
        return new Image(getClass().getResourceAsStream("res/" + name));
    }

    private Image rotateImage(Image i, DoubleProperty phi) {
        ImageView iv = new ImageView(i);
        iv.setRotate(phi.getValue());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);
        return rotatedImage;
    }
}
