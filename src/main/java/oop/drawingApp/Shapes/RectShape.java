package oop.drawingApp.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import oop.drawingApp.Editor;

public class RectShape extends Shape{
    private double x, y, x1, y1, x2, y2, width, height;
    public Paint color = null;

    public RectShape() {

    }

    public RectShape(Paint color) {
        this.color = color;
    }

    public void setCoords(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void show(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (color != null) {
            gc.setFill(color);
            gc.fillRect(this.x, this.y, width , height);
        }
        gc.strokeRect(this.x, this.y, width, height);
    }

    @Override
    public void onMousePressed(Canvas canvas) {
        canvas.setOnMousePressed(mouseEvent -> {
            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
        });
    }

    @Override
    public void onMouseDragged(Canvas canvas) {
        canvas.setOnMouseDragged(mouseEvent -> {
            Editor.clearCanvas(canvas);
            Editor.redrawCanvas(canvas);

            GraphicsContext gc = canvas.getGraphicsContext2D();
            double topLeftX;
            double topLeftY;
            this.x2 = mouseEvent.getX();
            this.y2 = mouseEvent.getY();
            this.width = Math.abs(x1 - x2);
            this.height = Math.abs(y1 - y2);

            if (this.x1 <= this.x2 && this.y1 <= this.y2) { // BOTTOM RIGHT
                topLeftX = this.x1;
                topLeftY = this.y1;
            } else if (this.x1 >= this.x2 && this.y1 <= this.y2) { // BOTTOM LEFT
                topLeftX = this.x1 - Math.abs(this.x1 - this.x2);
                topLeftY = this.y2 - Math.abs(this.y1 - this.y2);
            } else if (this.x1 <= this.x2 && this.y1 >= this.y2) { // TOP RIGHT
                topLeftX = this.x1;
                topLeftY = this.y1 - Math.abs(this.y1 - this.y2);
            } else { // TOP LEFT
                topLeftX = this.x2;
                topLeftY = this.y2;
            }

            this.x = topLeftX;
            this.y = topLeftY;
            gc.setLineDashes(5);
            gc.strokeRect(this.x, this.y, this.width, this.height);
            gc.setLineDashes(0);
        });
    }

    @Override
    public void onMouseReleased(Canvas canvas) {
        canvas.setOnMouseReleased(mouseEvent -> {
            Editor.clearCanvas(canvas);
            Editor.redrawCanvas(canvas);
            RectShape rect = new RectShape(this.color);
            rect.setCoords(this.x, this.y, this.width, this.height);
            rect.show(canvas);
            Editor.addShape(rect);
        });
    }
}
