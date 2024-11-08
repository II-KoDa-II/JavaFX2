package org.example.javafx2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class HelloController {
    Node draggedElement;
    public Pane canvasPane;

    @FXML
    void onRectangleButtonClick() {
        Random rng = new Random();
        geometry2d.Rectangle rec = new geometry2d.Rectangle(rng.nextInt(50) + 50, rng.nextInt(50) + 50);

        Rectangle rectangle = new Rectangle(rec.getLength(), rec.getHeight());
        rectangle.setLayoutX(rng.nextInt((int) (canvasPane.getWidth() - rectangle.getWidth())));
        rectangle.setLayoutY(rng.nextInt((int) (canvasPane.getHeight() - rectangle.getHeight())));
        rectangle.setFill(randomColor());

        rectangle.setOnMouseClicked(e -> {
            if(e.getButton()== MouseButton.SECONDARY){
                rectangle.setFill(randomColor());
            }
        });

        canvasPane.getChildren().add(rectangle);
    }

    @FXML
    void onCircleButtonClick() {

        Random random = new Random();
        geometry2d.Circle cir = new geometry2d.Circle(random.nextInt(25) + 25);

        Circle circle = new Circle(cir.getRadius());
        circle.setCenterX(random.nextInt((int) (canvasPane.getWidth() - (2 * cir.getRadius()))) + cir.getRadius());
        circle.setLayoutY(random.nextInt((int) (canvasPane.getHeight() - (2 * cir.getRadius()))) + cir.getRadius());
        circle.setFill(randomColor());

        circle.setOnMouseClicked(e->{
            if(e.getButton()== MouseButton.SECONDARY){
                circle.setFill(randomColor());
            }
        });

        canvasPane.getChildren().add(circle);
    }

    @FXML
    void canvasMousePress() {
        canvasPane.getChildren().forEach((c) -> {
            if(c.isPressed()){
                draggedElement = c;
            }
        });

        if(draggedElement != null) {
            canvasPane.getChildren().remove(draggedElement);
            canvasPane.getChildren().add(draggedElement);
        }
    }

    @FXML
    void canvasMouseUp() {
        draggedElement = null;
    }

    @FXML
    void canvasMouseMove(MouseEvent mouseEvent) {
        if(draggedElement != null){
            if(mouseEvent.getX() > canvasPane.getWidth() || mouseEvent.getX() < 0
                    || mouseEvent.getY() < 0 || mouseEvent.getY() > canvasPane.getHeight()){
                canvasMouseUp();
            }

            if(draggedElement instanceof Rectangle){
                draggedElement.setLayoutX(mouseEvent.getX()-(((Rectangle) draggedElement).getWidth()/2));
                draggedElement.setLayoutY(mouseEvent.getY()-(((Rectangle) draggedElement).getHeight()/2));
            }
            if(draggedElement instanceof Circle){
                draggedElement.setLayoutX(mouseEvent.getX()-(((Circle) draggedElement).getCenterX()));
                draggedElement.setLayoutY(mouseEvent.getY()-(((Circle) draggedElement).getCenterY()));
            }
        }
    }

    public Color randomColor(){
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}