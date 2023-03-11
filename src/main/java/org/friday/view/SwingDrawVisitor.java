package org.friday.view;

import org.friday.model.Circle;
import org.friday.model.Shape;
import org.friday.model.Square;

import java.awt.*;

public class SwingDrawVisitor implements DrawVisitor {
    private final Graphics graphics;
    private final int scalingFactor;
    private final int border;

    public SwingDrawVisitor(Graphics graphics, int scalingFactor, int border) {
        this.graphics = graphics;
        this.scalingFactor = scalingFactor;
        this.border = border;
    }

    @Override
    public void visitToDraw(Shape shape) {
        shape.acceptDrawing(this);
    }

    @Override
    public void draw(Square square) {
        graphics.setColor(Color.RED);
        drawRectangle(square);
    }

    @Override
    public void draw(org.friday.model.Rectangle rectangle) {
        graphics.setColor(Color.BLUE);
        drawRectangle(rectangle);
    }

    @Override
    public void draw(Circle circle) {
        graphics.setColor(Color.GREEN);
        drawOval(circle);
    }

    private void drawRectangle(Shape shape) {
        DrawingArguments args = computeArguments(shape);
        graphics.fillRect(border + args.x, border + args.y, args.width, args.height);
        graphics.setColor(Color.GRAY);
        graphics.drawRect(border + args.x, border + args.y, args.width, args.height);
    }

    private void drawOval(Shape shape) {
        DrawingArguments args = computeArguments(shape);
        graphics.fillOval(border + args.x, border + args.y, args.width, args.height);
        graphics.setColor(Color.GRAY);
        graphics.drawOval(border + args.x, border + args.y, args.width, args.height);
    }

    private DrawingArguments computeArguments(Shape shape) {
        int x = (int) (shape.left() * scalingFactor);
        int y = (int) (shape.top() * scalingFactor);
        int width = (int) ((shape.right() - shape.left()) * scalingFactor);
        int height = (int) ((shape.bottom() - shape.top()) * scalingFactor);
        return new DrawingArguments(x, y, width, height);

    }

    record DrawingArguments(int x, int y, int width, int height) {}
}
