package org.example.testtask;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Controller {
    public Controller() {
    }

    @FXML
    public Pane pane;
    @FXML
    public Rectangle rectangle;
    @FXML
    public TextField textFieldX;
    @FXML
    public TextField textFieldY;

    @FXML
    public void initialize(){
        rectangle.xProperty().addListener((observableValue, oldValue, newValue) -> textFieldX.setText(newValue.toString()));
        rectangle.yProperty().addListener((observableValue, oldValue, newValue) -> textFieldY.setText(newValue.toString()));

        rectangle.setOnMousePressed(event -> {
            rectangle.setUserData(new double[]{event.getX(), event.getY()});
        });

        rectangle.setOnMouseDragged(event -> {
            double[] startCoords = (double[]) rectangle.getUserData();
            double offsetX = event.getX() - startCoords[0];
            double offsetY = event.getY() - startCoords[1];

            double newX = rectangle.getX() + offsetX;
            double newY = rectangle.getY() + offsetY;

            if (newX >= 0 && newX + rectangle.getWidth() <= pane.getWidth()) {
                rectangle.setX(newX);
            }
            if (newY >= 0 && newY + rectangle.getHeight() <= pane.getHeight()) {
                rectangle.setY(newY);
            }

            rectangle.setUserData(new double[]{event.getX(), event.getY()});
        });
    }

    @FXML
    public void buttonClicked(){
        Double X,Y;
        try {
            X = Double.valueOf(textFieldX.getText());
            Y = Double.valueOf(textFieldY.getText());
            if (X < 0 || X > pane.getWidth() - rectangle.getWidth()) return;
            if (Y < 0 || Y > pane.getWidth() - rectangle.getHeight()) return;
        } catch (NumberFormatException e){
            return;
        }

        rectangle.setX(X);
        rectangle.setY(Y);
    }

}
