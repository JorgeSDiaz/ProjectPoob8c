package square;


/**
 * Write a description of class Prudent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
