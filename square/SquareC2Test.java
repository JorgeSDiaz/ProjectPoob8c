

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SquareC2Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SquareC2Test
{
    private Square zoneA;
    private Square zoneB;
    /**
     * Default constructor for test class SquareC2Test
     */
    public SquareC2Test() throws ExceptionSquare {
        zoneA = new Square(1000, 1000, 5);
        int[] dimensions = {300, 230, 3};
        int[][] dome = {{100, 120}, {70, 90}, {150, 10}};
        int[] desiredView = {3, 1, 2};
        zoneB = new Square(dimensions, dome, desiredView);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    
    @Test
    /**
     * 
     */
    public void shouldCreateZoneDimensionsSafeDis(){
        Square zone = new Square(100, 100, 5);
        int[] dimensions = {100, 100};
        assertArrayEquals(dimensions, zone.getDimensions());
        assertEquals(5, zone.getSafetyDistance());
    }
    
    @Test
    /**
     * 
     */
    public void shouldCreateZoneMultiDoms() throws ExceptionSquare {
        int[] dimensions = {200, 200, 3};
        int[][] dome = {{100, 120}, {70, 90}, {150, 10}};
        int[] desiredView = {3, 1, 2};
        Square zone = new Square(dimensions, dome, desiredView);
        int[] dimensionsXY = {200, 200};
        assertArrayEquals(dimensionsXY, zone.getDimensions());
        assertEquals(3, zone.domes().length);
    }
    
    @Test
    /**
     * 
     */
    public void shouldDefineRequestedPhoto(){
        String[] dome = {"red", "blue", "white"};
        zoneA.defineRequestedPhoto(dome);
        assertArrayEquals(dome, zoneA.getRequestedView());
    }
    
    @Test
    /**
     * 
     */
    public void shouldAddDome() throws ExceptionSquare {
        zoneA.addDome("red", 50, 50);
        String[] dome = {"red"};
        assertArrayEquals(dome, zoneA.domes());
    }
    
    @Test
    /**
     * 
     */
    public void shouldDelDome() throws ExceptionSquare {
        zoneA.addDome("pink", 50, 50);
        zoneA.addDome("light blue", 25, 25);
        zoneA.delDome("pink");
        assertEquals(1, zoneA.domes().length);
    }
    
    @Test
    /**
     * 
     */
    public void shouldTouristArrive() throws ExceptionSquare {
        zoneA.touristArrive("pink", 25, 50);
        zoneA.touristArrive("white", 30, 30);
        zoneA.touristArrive("black", 50, 60);
        assertEquals(3, zoneA.tourists().length);
    }
    
    @Test
    /**
     * 
     */
    public void shoulTouristMove() throws ExceptionSquare {
        zoneA.touristArrive("pink", 25, 50);
        zoneA.touristArrive("white", 30, 30);
        zoneA.touristMove("pink", 50, 50, 75);
        zoneA.touristMove("white", 20, 10, 180);
        int[] pinkData = {50, 50, 75};
        int[] whiteData = {20, 10, 180};
        assertArrayEquals(pinkData, zoneA.tourist("pink"));
        assertArrayEquals(whiteData, zoneA.tourist("white"));
    }
    
    @Test
    /**
     * 
     */
    public void shouldTouristTakePhoto() throws ExceptionSquare {
        zoneA.touristArrive("pink", 100, 100);
        zoneA.touristArrive("white", 200, 200);
        zoneA.touristMove("pink", 50, 50, 270);
        zoneA.touristMove("white", 20, 10, 90);
        zoneA.addDome("pink", 50, 50);
        zoneA.addDome("blue", 110, 25);
        zoneA.addDome("violet", 10, 90);
        zoneA.addDome("light blue", 200, 200);
        zoneA.addDome("orange", 150, 200);
        String[] pinkView = {"orange", "blue", "light blue"};
        String[] whiteView = {"violet"};
        assertArrayEquals(pinkView, zoneA.touristTakePhoto("pink"));
        assertArrayEquals(whiteView, zoneA.touristTakePhoto("white"));
    }
    
    @Test
    /**
     * 
     */
    public void shouldWhoRequestedPhoto() throws ExceptionSquare {
        int[] dimensions = {300, 250, 3};
        int[][] dome = {{150, 100}, {110, 25}, {200, 100}};
        int[] desiredView = {2, 1, 3};
        Square zoneC = new Square(dimensions, dome, desiredView);
        zoneC.touristArrive("pink", 30, 30);
        zoneC.touristMove("pink", 30, 30, 270);
        String[] res = {"pink"};
        assertArrayEquals(res, zoneC.whoRequestedPhoto());
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
