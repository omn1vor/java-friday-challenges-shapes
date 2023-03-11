package org.friday.model;

import org.friday.view.DrawVisitor;
import org.friday.view.Drawable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rectangle extends Shape implements Drawable {
    private final double width;
    private final double height;

    public Rectangle(double x, double y, double width, double height) {
        super(new Point(x, y));
        this.width = width;
        this.height = height;
    }

    @Override
    public double top() {
        return origin.y();
    }

    @Override
    public double bottom() {
        return origin.y() + height;
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
    boolean intersectsWithRectangle(Shape shape) {
        return Shape.rectangleIntersectsWithRectangle(this, shape);
    }

    @Override
    boolean intersectsWithCircle(Shape shape) {
        return Shape.rectangleIntersectsWithCircle(this, shape);
    }

    public static Rectangle parse(String signature) {
        Pattern pattern = Pattern.compile("rectangle\\((.+)\\)");
        Matcher matcher = pattern.matcher(signature);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("wrong format for rectangle");
        }

        String[] args = matcher.group(1).split(",");

        if (args.length != 4) {
            throw new IllegalArgumentException("expect 4 parameters for rectangle");
        }

        return new Rectangle(
                Double.parseDouble(args[0]),
                Double.parseDouble(args[1]),
                Double.parseDouble(args[2]),
                Double.parseDouble(args[3])
        );
    }

    @Override
    public void acceptDrawing(DrawVisitor drawVisitor) {
        drawVisitor.draw(this);
    }
}
