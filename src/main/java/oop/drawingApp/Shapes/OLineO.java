package oop.drawingApp.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import oop.drawingApp.Editor;

public class OLineO extends Shape {
    private double x1, y1, x2, y2;
    private final double pointSize = 20;
    private final LineShape line = new LineShape();
    private final EllipseShape ellipse = new EllipseShape();
    @Override
    public void show(Canvas canvas) {
        drawShapes(canvas);
    }

    private void drawShapes(Canvas canvas) {
        line.setCoords(x1, y1, x2, y2);
        line.show(canvas);
        ellipse.setCoords(x1 - pointSize / 2, y1 - pointSize / 2, pointSize, pointSize);
        ellipse.show(canvas);
        ellipse.setCoords(x2 - pointSize / 2, y2 - pointSize / 2, pointSize, pointSize);
        ellipse.show(canvas);
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
            gc.setLineDashes(5);
            drawShapes(canvas);
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
            OLineO oLineo = new OLineO();
            oLineo.setCoords(this.x1, this.y1, this.x2, this.y2);
            oLineo.show(canvas);
            Editor.addShape(oLineo);
        });
    }

    public void setCoords(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
