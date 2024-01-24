package oop.drawingApp.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import oop.drawingApp.Editor;

public class PointShape extends Shape {
    private double x;
    private double y;

    private void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void show(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        double dotSize = 5;
        gc.fillOval(this.x - dotSize / 2, this.y - dotSize / 2, dotSize, dotSize);
    }

    @Override
    public void onMousePressed(Canvas canvas) {
        canvas.setOnMousePressed(mouseEvent -> {
            this.x = mouseEvent.getX();
            this.y = mouseEvent.getY();
            PointShape point = new PointShape();
            point.setCoords(this.x, this.y);
            point.show(canvas);
            Editor.addShape(point);
        });
    }

    @Override
    public void onMouseDragged(Canvas canvas) {
        canvas.setOnMouseDragged(null);
    }

    @Override
    public void onMouseReleased(Canvas canvas) {
        canvas.setOnMouseReleased(null);
    }
}