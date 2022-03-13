import java.util.List;
import javax.swing.*;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * We create a space with domes to admire and the tourists who come to observe.
 * 
 * @author Luisa de la Hoz y Jorge Saenz.
 * @version (3.5).
 */
public class Square
{
    private int dimensionX;
    private int dimensionY;
    private int safetyDistance;
    private boolean visible;
    private boolean ok;
    private ArrayList<String> requestedView;
    private Rectangle zone = new Rectangle();
    private HashMap<String, Dome> domes = new HashMap<String, Dome>();
    private HashMap<String, Turist> turis = new HashMap<String, Turist>();

    /**
     * Constructor for objects of class Square
     * @Param xDim x-dimension of the area
     * @Param yDim y-dimension of the area
     * @Param safeDis the minimum distance that should be between tourist and tourist
     */
    public Square(int xDim, int yDim, int safeDis)
    {
        ok = true;
        dimensionX = xDim;
        dimensionY = yDim;
        visible = false;
        safetyDistance = safeDis;
        zone.changePosition(0, 0);
        zone.changeSize(dimensionX, dimensionY);
        zone.changeColor("blue");
        requestedView = new ArrayList();
    }
    
    /**
     * constructor overload
     * @Param dimensions list of integers with the dimensions in x and y of the zone and the number of domes to add
     * @Param domes two-dimensional list that contains the pair of coordinates of each dome
     * @Param desiredView 
     */
    public Square(int[] dimensions, int[][] domes, int[] desiredView){
        ok = true;
        dimensionX = dimensions[0];
        dimensionY = dimensions[1];
        visible = false;
        safetyDistance = 1;
        zone.changePosition(0, 0);
        zone.changeSize(dimensionX, dimensionY);
        zone.changeColor("blue");
        addMultiDoms(domes, dimensions[2], desiredView);
    }
    
    // dome = ["violet", "blue", "red"]
    /**
     * allows the user to define the order in which the domes want to appear in the simulator, listing from first to last, using their color as a reference
     * @Param dome List with the color from the first to the last of the domes in the order that they should appear
     */
    public void defineRequestedPhoto(String[] dome){
        ArrayList<String> order = new ArrayList<String>(Arrays.asList(dome));
        requestedView = order;
    }
    
    public int[] getDimensions(){
        int[] dimensions = {dimensionX, dimensionY};
        return dimensions;
    }
    
    public int getSafetyDistance(){
        return safetyDistance;
    }
    
    public String[] getRequestedView(){
        String[] desiredView = new String[requestedView.size()];
        requestedView.toArray(desiredView);
        return desiredView;
    }
    
    /**
     * We add a dome at a specific point in space.
     * @Param color color with which we want to identify the dome.
     * @Param x Position x in which the dome is located.
     * @Param y Position y in which the dome is located.
     */
    public void addDome(String color, int x, int y){
        Dome dome = new Dome();
        dome.setColor(color);
        dome.setCor(x, y);
        if (!(domes.containsKey(color))){
            if (0 < x && x <=dimensionX - dome.getDiameter()){
                if (0 < y && x <=dimensionY - dome.getDiameter()){
                    ok = true;
                    eraseAmbient();
                    domes.put(color, dome);
                    drawAmbient();
                } else{
                    ok = false;
                    JOptionPane.showMessageDialog(null,"Excede el espacio en y :0");
                }
            } else{
                ok = false;
                JOptionPane.showMessageDialog(null,"Excede el espacio en x :0");
            }
        } else{
            ok = false;
            JOptionPane.showMessageDialog(null,"Este domo ya existe :0");
        }
    }
    
    /**
     * We remove a dome from space
     * @Param dome Color with which the dome that we want 
     * to erase is identified.
     */
    public void delDome(String dome){
        if (domes.containsKey(dome)){
            ok = true;
            eraseAmbient();
            domes.remove(dome);
            drawAmbient();
        } else{
         ok = false;
         JOptionPane.showMessageDialog(null,"Este domo aun no existe ^^");
        }
    }
    
    /**
     * We add a tourist in the area.
     * @Param color color with which we want to identify the turist.
     * @Param x Position x in which the turist is located.
     * @Param y Position y in which the turist is located.
     */
    public void touristArrive(String color, int x, int y){
        Turist turist = new Turist();
        turist.setColor(color);
        turist.setCor(x + (safetyDistance * (turis.size() + 1)), y + (safetyDistance * (turis.size() + 1)));
        if (!(domes.containsKey(color))){
            if (0 < x && x <=dimensionX - turist.getHeight()){
                if (0 < y && x <=dimensionY - turist.getHeight()){
                    ok = true;
                    eraseAmbient();
                    turis.put(color, turist);
                    drawAmbient();
                } else{
                    ok = false;
                    JOptionPane.showMessageDialog(null,"Excede el espacio en y :3");
                }
            } else{
                ok = false;
                JOptionPane.showMessageDialog(null,"Excede el espacio en x :3");
            }
        } else{
            ok = false;
            JOptionPane.showMessageDialog(null,"Este turista ya existe :3");
        }
    }
    
    /**
     * We change the position of an indicated tourist along with his angle of orientation.
     * @Param tourist color of the tourist we need.
     * @Param x x-axis position.
     * @Param y y axis position.
     * @Param angle angle to which it is facing.
     */
    public void touristMove(String tourist, int x, int y, int angle){
        if (turis.containsKey(tourist)){
            if (0 < x && x <=dimensionX){
                if (0 < y && x <=dimensionY){
                    ok = true;
                    eraseAmbient();
                    Turist turist = new Turist();
                    turist.setColor(tourist);
                    turist.setCor(x, y);
                    turist.setAngle(angle);
                    turis.replace(tourist, turist);
                    drawAmbient();
                } else{
                    ok = false;
                    JOptionPane.showMessageDialog(null,"Excede el espacio en y :3");
                }
            } else{
                ok = false;
                JOptionPane.showMessageDialog(null,"Excede el espacio en x :3");
            }
        } else{
            ok = false;
            JOptionPane.showMessageDialog(null,"Este turista no existe :3");
        }
    }
    
    /**
     * Take the photo and return it depending on the direction where the tourist is looking, 
     * which domes will appear at an angle of 180 degrees.
     * @Param tourist color of the tourist to be photographed.
     */
    public String[] touristTakePhoto(String tourist){
        ArrayList<String> domeViews = new ArrayList();
        Turist photographer = turis.get(tourist);
        int xCor = photographer.getXcor();
        int yCor = photographer.getYcor();
        int angleDir = photographer.getAngle();
        zone.changeColor("green");
        eraseAmbient();
     for (String e: domes()){
            if (angleDir >= 270 && angleDir < 360){
                if (angleDir == 270 && xCor < (domes.get(e).getXcor())){
                    domeViews.add(e);
                }
            } else if (angleDir >= 0 && angleDir < 90){
                if (angleDir == 0 && yCor > (domes.get(e).getYcor())){
                    domeViews.add(e);
                }
            } else if (angleDir >= 90 && angleDir < 180){
                if (angleDir == 90 && xCor > (domes.get(e).getXcor())){
                    domeViews.add(e);
                }
            } else if(angleDir >= 180 && angleDir < 270){
                if (angleDir == 180 && yCor < (domes.get(e).getYcor())){
                    domeViews.add(e);
                }
            }
        }
        zone.changeColor("blue");
        drawAmbient();
        String[] results = new String[domeViews.size()];
        domeViews.toArray(results);
        return results;
    }
    
    /**
     * Take the photo and return it depending on the direction of the suggested angle.
     * @Param tourist color of the tourist to be photographed.
     * @Param viewAngle angle of vision from which you want to know the domes that can be observed
     */
        public String[] touristTakePhoto(String tourist, int viewAngle){
        ArrayList<String> domeViews = new ArrayList();
        Turist photographer = turis.get(tourist);
        int xCor = photographer.getXcor();
        int yCor = photographer.getYcor();
        for (String e: domes()){
            if (viewAngle >= 270 && viewAngle < 360){
                if (viewAngle == 270 && xCor < (domes.get(e).getXcor())){
                    domeViews.add(e);
                }
            } else if (viewAngle >= 0 && viewAngle < 90){
                if (viewAngle == 0 && yCor > (domes.get(e).getYcor())){
                    domeViews.add(e);
                }
            } else if (viewAngle >= 90 && viewAngle < 180){
                if (viewAngle == 90 && xCor > (domes.get(e).getXcor())){
                    domeViews.add(e);
                }
            } else if(viewAngle >= 180 && viewAngle < 270){
                if (viewAngle == 180 && yCor < (domes.get(e).getYcor())){
                    domeViews.add(e);
                }
            }
        }
        String[] results = new String[domeViews.size()];
        domeViews.toArray(results);
        return results;
    }
    
    /**
     * Some tourist takes the requested photo
     */
    public void takeRequestedPhoto(){
        ArrayList<String> Possible = new ArrayList<String>(Arrays.asList(whoRequestedPhoto()));
        String choose = "";
        ArrayList<String> lossed = new ArrayList();
        int maxCant = 0;
        for (String e: Possible){
            int cant = 0;
            ArrayList<String> loss = new ArrayList();
            for (String d: touristTakePhoto(e)){
                if (requestedView.contains(d)){
                    cant++;
                } else{
                    loss.add(d);
                }
                if (cant > maxCant){
                    maxCant = cant;
                    choose = e;
                }
            }
            lossed = loss;
        }
        if (choose != "" && lossed.size() != 0){
            int difDistance = 0;
            int chooseAngle = turis.get(choose).getAngle();
            if (chooseAngle == 270 || chooseAngle == 90){
                for (String i: lossed){
                    int dif = turis.get(choose).getXcor() - domes.get(i).getXcor();
                    if (dif > 0 && dif > difDistance){
                        dif = difDistance;
                    } else if (dif < 0 && dif < difDistance){
                        dif = difDistance;
                    }
                }
                if (difDistance > 0){
                    touristMove(choose, turis.get(choose).getXcor() - difDistance - safetyDistance, turis.get(choose).getYcor(), chooseAngle);
                } else {
                    touristMove(choose, turis.get(choose).getXcor() + difDistance + safetyDistance, turis.get(choose).getYcor(), chooseAngle);
                }
            } else if(chooseAngle == 0 || chooseAngle == 180){
                for (String i: lossed){
                    int dif = turis.get(choose).getXcor() - domes.get(i).getXcor();
                    if (dif > 0 && dif > difDistance){
                        dif = difDistance;
                    } else if (dif < 0 && dif < difDistance){
                        dif = difDistance;
                    }
                }
                if (difDistance > 0){
                    touristMove(choose, turis.get(choose).getXcor(), turis.get(choose).getYcor() - difDistance - safetyDistance, chooseAngle);
                } else {
                    touristMove(choose, turis.get(choose).getXcor(), turis.get(choose).getYcor() + difDistance + safetyDistance, chooseAngle);
                }
            }
            touristTakePhoto(choose);
        } else{
            JOptionPane.showMessageDialog(null,"No se puede tomar la foto");
        }
    }
    
    /**
     * Returns a list of tourists who can take the requested photo
     */
    public String[] whoRequestedPhoto(){
        ArrayList<String> allowed = new ArrayList();
        String[] turiss = tourists();
        for(String e: turiss){
            String[] photo = touristTakePhoto(e);
            ArrayList<String> viewTouris = new ArrayList<String>(Arrays.asList(photo));
            if (viewTouris.equals(requestedView)){
                allowed.add(e);
            }
        }
        String[] result = new String[allowed.size()];
        allowed.toArray(result);
        return result;
    }
    
    /**
     * We return a list with the colors by which each dome is identified.
     */
    public String[] domes(){
        ok = true;
        Set<String> domeKeys = domes.keySet();
        String[] domes = new String[domeKeys.size()];
        domeKeys.toArray(domes);
        return domes;
    }
    
    /**
     * We return a list with the colors by which each turist is identified.
     */
    public String[] tourists(){
        ok = true;
        Set<String> turisKeys = turis.keySet();
        String[] tourists = new String[turisKeys.size()];
        turisKeys.toArray(tourists);
        return tourists;
    }
    
    /**
     * We return the coordinates of the desired dome.
     * @Param dome Color that identifies the dome.
     */
    public int[] dome(String dome){
        ok = true;
        int[] coor = new int[2];
        Dome searchDome = domes.get(dome);
        coor[0] = searchDome.getXcor();
        coor[1] = searchDome.getYcor();
        return coor;
    }
    
    /**
     * We return the coordinates of the desired turist.
     * @Param tourist Color that identifies the turist.
     */
    public int[] tourist(String tourist){
        ok = true;
        int[] coor = new int[3];
        Turist searchTourist = turis.get(tourist);
        coor[0] = searchTourist.getXcor();
        coor[1] = searchTourist.getYcor();
        coor[2] = searchTourist.getAngle();
        return coor;
    }
    
    /**
     * Makes the zone visible.
     */
    public void makeVisible(){
        ok = true;
        visible = true;
        drawAmbient();
    }
    
    /**
     * Makes the zone invisible.
     */
    public void makeInvisible(){
        ok = true;
        eraseAmbient();
        visible = false;
    }
    
     /**
      * All data that is added of tourists and domes is deleted.
      */
        public void finish(){
        ok = true;
        eraseAmbient();
        for (int d = 0; d < domes.size(); d++){
            domes.remove(d);
        }
        for (int t = 0; t < turis.size(); t++){
            turis.remove(t);
        }
    }
    
    /**
     * It is rectified that everything has been erased.
     */
    public boolean ok(){
        return ok;
    }
    
    /**
     * We illustrate the area with the domes and the tourists.
     */
    private void drawAmbient(){
        if (visible){
            zone.makeVisible();
            ArrayList<String> orderAppear = new ArrayList();
            if (requestedView.size() == 0){
                for (String e: domes()){
                    orderAppear.add(e);
                }
            } else {
                orderAppear = requestedView;
            }
            for (String d: orderAppear){
                Dome dome = domes.get(d);
                dome.makeVisible();
            }
            for (String t: tourists()){
                Turist turist = turis.get(t);
                turist.makeVisible();
            }
        }
    }
    
    /**
     * we eliminate the area with the domes and the tourists
     */
    private void eraseAmbient(){
        if (visible){
            for (String d: domes()){
            Dome dome = domes.get(d);
            dome.makeInvisible();
            }
        
            for (String t: tourists()){
                Turist turist = turis.get(t);
                turist.makeInvisible();
            }
        
            zone.makeInvisible();
        }
    }
    
    /**
     * Returns a list with the colors available to use in the domes
     */
    private ArrayList<String> colorsNuse(){
        String[] tooColors = {"green", "red", "black", "yellow", "magenta", "white", "orange", "light blue", "violet", "pink", "aquamarine"};
        ArrayList<String> colors = new ArrayList();
        ArrayList<String> exist = new ArrayList<String>(Arrays.asList(domes()));;
        for (String e: tooColors){
            if (!(exist.contains(e))){
                colors.add(e);
            }
        }
        return colors;
    }
    
    /**
     * It allows us to add several domes at the same time without repeating their reference color and organizing the view requested by the user
     * @Param domes two-dimensional list that contains the pair of coordinates of each dome
     * @Param cant number of domes
     * @Param views list with the order of how the domes should appear
     */
    private void addMultiDoms(int[][] domes, int cant, int[] views){
        // sobrecargar con el setforgroun
        String[] order = new String[cant]; // [null, null, null,...]
        ArrayList<Integer> viewInt = new ArrayList();
        for (int e: views){
            viewInt.add(e);
        }
        
        for (int i = 0; i < viewInt.size(); i++){
            ArrayList<String> colorsExist = colorsNuse();
            if (!(colorsExist.size() == 0)){
                int randomInt = (int)Math.random()*(colorsExist.size()); // 0 - 1 + n -> 0 -> n
                String color = colorsExist.get(randomInt);
                order[viewInt.indexOf(i + 1)] = color; // [3, 2, 1], index(1) = 2
                addDome(color, domes[i][0], domes[i][1]); // addDome("magenta", xi, yi)
            }
        }
        ArrayList<String> orders = new ArrayList<String>(Arrays.asList(order));
        requestedView = orders;
    }
}
