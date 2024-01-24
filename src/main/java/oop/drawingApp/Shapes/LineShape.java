package oop.drawingApp.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import oop.drawingApp.Editor;

public class LineShape extends Shape{
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public void setCoords(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void show(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.strokeLine(this.x1, this.y1, this.x2, this.y2);
    }

    @Override
    public void onMousePressed(Canvas canvas) {
        canvas.setOnMousePressed(mouseEvent -> {
            this.x1 = mouseEvent.getX();
            this.y1 = mouseEvent.getY();
        });
    }

    @Override
    public void onMouseDragged(Canvas canvas) {
        canvas.setOnMouseDragged(mouseEvent -> {
            Editor.clearCanvas(canvas);
            Editor.redrawCanvas(canvas);
            this.x2 = mouseEvent.getX();
            this.y2 = mouseEvent.getY();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.setLineDashes(5);
            gc.strokeLine(this.x1, this.y1, this.x2, this.y2);
            gc.setLineDashes(0);
        });
    }

    @Override
    public void onMouseReleased(Canvas canvas) {
        canvas.setOnMouseReleased(mouseEvent -> {
            Editor.clearCanvas(canvas);
            Editor.redrawCanvas(canvas);
            this.x2 = mouseEvent.getX();
            this.y2 = mouseEvent.getY();
            LineShape line= new LineShape();
            line.setCoords(this.x1, this.y1, this.x2, this.y2);
            line.show(canvas);
            Editor.addShape(line);
        });
    }
}
