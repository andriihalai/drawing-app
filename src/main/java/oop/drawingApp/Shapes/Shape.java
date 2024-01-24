package oop.drawingApp.Shapes;

import javafx.scene.canvas.Canvas;

public abstract class Shape {
    public abstract void show(Canvas canvas);

    public abstract void onMousePressed(Canvas canvas);
    public abstract void onMouseDragged(Canvas canvas);
    public abstract void onMouseReleased(Canvas canvas);
}
