package org.friday.model;

import org.friday.view.DrawVisitor;
import org.friday.view.Drawable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Square extends Shape implements Drawable {
    private final double width;

    public Square(double x, double y, double width) {
        super(new Point(x, y));
        this.width = width;
    }

    @Override
    public double top() {
        return origin.y();
    }

    @Override
    public double bottom() {
        return origin.y() + width;
    }

    @Override
    public double left() {
        return origin.x();
    }

    @Override
    public double right() {
        return origin.x() + width;
    }

    @Override
    boolean intersects(Shape shape) {
        return shape.intersectsWithRectangle(this);
    }

    @Override
    public boolean intersectsWithRectangle(Shape rectangle) {
        return rectangleIntersectsWithRectangle(this, rectangle);
    }

    @Override
    boolean intersectsWithCircle(Shape circle) {
        return rectangleIntersectsWithCircle(this, circle);
    }

    public static Square parse(String signature) {
        Pattern pattern = Pattern.compile("square\\((.+)\\)");
        Matcher matcher = pattern.matcher(signature);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("wrong format for square");
        }

        String[] args = matcher.group(1).split(",");

        if (args.length != 3) {
            throw new IllegalArgumentException("expected 3 parameters for square");
        }

        return new Square(
                Double.parseDouble(args[0]),
                Double.parseDouble(args[1]),
                Double.parseDouble(args[2])
        );
    }

    @Override
    public void acceptDrawing(DrawVisitor drawVisitor) {
        drawVisitor.draw(this);
    }
}
