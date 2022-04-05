package square;

import shapes.Canvas;
import shapes.Circle;

import java.awt.geom.Ellipse2D;

/**
 * Write a description of class Fixed here.
 *
 * @author Valentina de la Hoz y Jorge Saenz
 * @version 1.0
 */
public class Shy extends Dome
{
    Circle subCricle = new Circle();
    public Shy(int size)
    {
        super(size);
        this.canBeRemoved = false;
        subCricle.setYposition(this.yPosition);
        subCricle.setXposition(this.xPosition);
        subCricle.changeSize(this.diameter / 2);
        subCricle.changeColor("violet");
    }

    @Override
    public void setCor(int xCor, int yCor){
        super.setCor(xCor, yCor);
        subCricle.setXposition(xCor);
        subCricle.setYposition(yCor);
    }

    @Override
    protected void draw(int time){
        if (this.isVisible){
            super.draw(time);
            if(isVisible) {
                Canvas canvas = Canvas.getCanvas();
                canvas.draw(subCricle, subCricle.getColor(),
                        new Ellipse2D.Double(xPosition, yPosition,
                                subCricle.getDiameter(), subCricle.getDiameter()),
                        xPosition, yPosition, 0, false);
                canvas.wait(time);
            }
        }
    }
}
