package square;


/**
 * Tourist who keeps twice as much distance from other tourists
 *
 * @author Valentina de la Hoz y Jorge Saenz
 * @version 1.0
 */
public class Prudent extends Turist
{
    /**
     * Constructor for objects of class Prudent
     */
    public Prudent(int height, int width, int safeDistance)
    {
        super(height, width, safeDistance);
        this.safeDistance = safeDistance * 2;
    }
}
