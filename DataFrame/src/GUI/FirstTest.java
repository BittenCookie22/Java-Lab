package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FirstTest extends Application {
    public Text actionStatus;
    public Stage savedStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("/GUI/firstFXML.fxml"));
//        StackPane stackPane = loader.load();
//
//        Scene scene = new Scene(stackPane);
//        primaryStage.setScene(scene);

//        StackPane stackPane = new StackPane();
//        Button button = new Button("Przycisk");
//        stackPane.getChildren().add(button);
//
//        Scene scene = new Scene(stackPane, 400, 600);
//
//
//        primaryStage.setScene(scene);
//        primaryStage.setHeight(400);
//        primaryStage.setWidth(400);
        // primaryStage.setResizable(false); // blokuje zmianę rozmiaru
       // primaryStage.setX(0); //określa wspólrzedne połozenia okienka
       // primaryStage.setY(0);//określa wspólrzedne połozenia okienka

        primaryStage.setOpacity(0.95);
        primaryStage.setTitle("Pierwsze okienko");
        primaryStage.show();



    Button open = new Button("open");
    open.setOnAction(new SingleFcButtonListener());
    HBox open1 = new HBox(10);
    open1.getChildren().addAll(open);
    Button save = new Button("Save");
    HBox save1 = new HBox(10);
    save1.getChildren().addAll(save);

    actionStatus = new Text();



    VBox vbox = new VBox(30);
    vbox.getChildren().addAll( open1,save1,  actionStatus);
    Scene scene = new Scene(vbox, 500, 300);
    primaryStage.setScene(scene);
    primaryStage.show();
    savedStage = primaryStage;
}

private class SingleFcButtonListener implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

        showSingleFileChooser();
    }
}
    private void showSingleFileChooser() {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            actionStatus.setText("File selected: " + selectedFile.getName());
        }

    }
}

