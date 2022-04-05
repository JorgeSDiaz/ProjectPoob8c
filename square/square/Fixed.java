package square;


import shapes.Canvas;

import java.awt.geom.Ellipse2D;

/**
 * Write a description of class Fixed here.
 * 
 * @author Valentina de la Hoz y Jorge Saenz
 * @version 1.0
 */
public class Fixed extends Dome
{
    public Fixed(int size)
    {
        super(size);
        this.canBeRemoved = true;
    }

    @Override
    protected void draw(int time) {
        if (this.isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                    new Ellipse2D.Double(xPosition, yPosition,
                            diameter, diameter / 2), xPosition, yPosition, 0, false);
            canvas.wait(time);
        }
    }
}
