package oop.drawingApp.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import oop.drawingApp.Editor;

public class Cube extends Shape {
    private double x1, y1, x2, y2;
    private double x3, y3, x4, y4;
    private double width, height;

    private RectShape rect = new RectShape();
    private LineShape line = new LineShape();
    @Override
    public void show(Canvas canvas) {
        rect.setCoords(x1, y1, width ,height);
        rect.show(canvas);
        rect.setCoords(x3, y3, width, height);
        rect.show(canvas);
        line.setCoords(x1, y1, x3, y3);
        line.show(canvas);
        line.setCoords(x2, y2, x4, y4);
        line.show(canvas);
        line.setCoords(x3 + width, y3, x2, y2 - height);
        line.show(canvas);
        line.setCoords(x4 - width, y4, x2 - width, y2);
        line.show(canvas);
    }

    public void setCoords(double mousePressedX, double mousePressedY, double mouseReleasedX, double mouseReleasedY) {
        double wConst = 0.25;
        double hConst = 0.25;
        this.width = Math.abs(mouseReleasedX - mousePressedX) * (1 - wConst);
        this.height = Math.abs(mouseReleasedY - mousePressedY) * (1 - hConst);

        if (mousePressedX <= mouseReleasedX && mousePressedY <= mouseReleasedY) {
            this.x1 = mousePressedX;
            this.y1 = mousePressedY;
            this.x4 = mouseReleasedX;
            this.y4 = mouseReleasedY;
        } else if (mouseReleasedX <= mousePressedX && mousePressedY <= mouseReleasedY) {
            this.x1 = mousePressedX - width;
            this.y1 = mousePressedY;
            this.x4 = mouseReleasedX + width;
            this.y4 = mouseReleasedY;
        } else if (mousePressedX <= mouseReleasedX && mouseReleasedY <= mousePressedY) {
            this.x1 = mouseReleasedX - width;
            this.y1 = mouseReleasedY;
            this.x4 = mousePressedX + width;
            this.y4 = mousePressedY;
        } else {
            this.x1 = mouseReleasedX;
            this.y1 = mouseReleasedY;
            this.x4 = mousePressedX;
            this.y4 = mousePressedY;
        }

        this.x3 = x4 - width;
        this.y3 = y4 - height;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
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
            this.x4 = mouseEvent.getX();
            this.y4 = mouseEvent.getY();

            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setLineDashes(5);
            Cube cube = new Cube();
            cube.setCoords(x1, y1, x4, y4);
            cube.show(canvas);
            gc.setLineDashes(0);
        });
    }

    @Override
    public void onMouseReleased(Canvas canvas) {
        canvas.setOnMouseReleased(mouseEvent -> {
            Editor.clearCanvas(canvas);
            Editor.redrawCanvas(canvas);
            this.x4 = mouseEvent.getX();
            this.y4 = mouseEvent.getY();
            Cube cube = new Cube();
            cube.setCoords(x1, y1, x4, y4);
            cube.show(canvas);
            Editor.addShape(cube);
        });
    }
}
