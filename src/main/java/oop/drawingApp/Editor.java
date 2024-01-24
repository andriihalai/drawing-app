package oop.drawingApp;

import javafx.scene.canvas.Canvas;
import oop.drawingApp.Shapes.Shape;

import java.util.ArrayList;

public class Editor {
    public static final ArrayList<Shape> SHAPE_ARRAY_LIST = new ArrayList<>();
    public static void addShape(Shape shape) {
        SHAPE_ARRAY_LIST.add(shape);
    }

    public static void redrawCanvas(Canvas canvas) {
        for (Shape shape : SHAPE_ARRAY_LIST) {
            shape.show(canvas);
        }
    }

    public static void clearCanvas(Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static void draw(Shape shape, Canvas canvas) {
        shape.onMousePressed(canvas);
        shape.onMouseDragged(canvas);
        shape.onMouseReleased(canvas);
    }
}
