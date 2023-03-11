package org.friday.view;

import org.friday.model.Circle;
import org.friday.model.Rectangle;
import org.friday.model.Shape;
import org.friday.model.Square;

public interface DrawVisitor {
    void visitToDraw(Shape shape);

    void draw(Square square);

    void draw(Rectangle rectangle);

    void draw(Circle circle);
}
