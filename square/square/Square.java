package square;

import shapes.Rectangle;

import java.util.*;

/**
 * We create a space with domes to admire and the tourists who come to observe.
 * 
 * @author Luisa de la Hoz y Jorge Saenz.
 * @version (3.5).
 */
public class Square
{
    private final int dimensionX;
    private final int dimensionY;
    private final int safetyDistance;
    private boolean visible;
    private boolean ok;
    private ArrayList<String> requestedView;
    private final Rectangle zone = new Rectangle();
    private final HashMap<String, Dome> domes = new HashMap<>();
    private final HashMap<String, Turist> turis = new HashMap<>();

    /**
     * Constructor for objects of class Square
     * @Param xDimension x-dimension of the area
     * @Param yDimension y-dimension of the area
     * @Param safeDistance the minimum distance that should be between tourist and tourist
     */
    public Square(int xDimension, int yDimension, int safeDistance){
        ok = true;
        dimensionX = xDimension;
        dimensionY = yDimension;
        visible = false;
        safetyDistance = safeDistance;
        zone.changePosition(0, 0);
        zone.changeSize(dimensionX, dimensionY);
        zone.changeColor("blue");
        requestedView = new ArrayList<>();
    }
    
    /**
     * constructor overload
     * @Param dimensions list of integers with the dimensions in x and y of the zone and the number of domes to add
     * @Param domes two-dimensional list that contains the pair of coordinates of each dome
     * @Param desiredView 
     */
    public Square(int[] dimensions, int[][] domes, int[] desiredView){
        this(dimensions[0], dimensions[1], 20);
        addMultiDoms(domes, dimensions[2], desiredView);
    }
    
    // dome = ["violet", "blue", "red"]
    /**
     * allows the user to define the order in which the domes want to appear in the simulator, listing from first to last, using their color as a reference
     * @Param dome List with the color from the first to the last of the domes in the order that they should appear
     */
    public void defineRequestedPhoto(String[] dome){
        requestedView = new ArrayList<>(Arrays.asList(dome));
    }

    /**
     *
     * @return
     */
    public int[] getDimensions(){
        return new int[]{dimensionX, dimensionY};
    }

    /**
     *
     * @return
     */
    public int getSafetyDistance(){
        return safetyDistance;
    }

    /**
     *
     * @return
     */
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
    public void addDome(String color, int x, int y) throws ExceptionSquare{
        if (domes.containsKey(color)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.ALREADY_ADDED);
        }
        if (!(0 < x && x <= dimensionX - dimensionX / 10) || !(0 < y && y <= dimensionY - dimensionX / 10)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.BAD_LOCATION);
        }

        Dome dome = new Dome(dimensionX);
        ok = true;
        eraseAmbient();
        dome.changeColor(color);
        dome.setCor(x, y);
        domes.put(color, dome);
        drawAmbient();
    }


    /**
     *
     * @param color
     * @param x
     * @param y
     * @param type
     * @throws ExceptionSquare
     */
    public void addDome(String color, int x, int y, String type) throws ExceptionSquare {
        if (domes.containsKey(color)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.ALREADY_ADDED);
        }
        if (!(0 < x && x <= dimensionX - dimensionX / 10) || !(0 < y && y <= dimensionY - dimensionX / 10)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.BAD_LOCATION);
        }
        String[] types = {"fixed", "shy"};
        if ((new ArrayList<>(List.of(types))).contains(type)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NO_IS_A_TYPE);
        }

        Dome dome = null;
        if (type.equals("fixed")){
            dome = new Fixed(dimensionX);
        } else if (type.equals("shy")){
            dome = new Shy(dimensionX);
        }


        ok = true;
        eraseAmbient();
        dome.changeColor(color);
        dome.setCor(x, y);
        domes.put(color, dome);
        drawAmbient();
    }
    
    /**
     * We remove a dome from space
     * @Param dome Color with which the dome that we want
     * to erase is identified.
     */
    public void delDome(String dome) throws ExceptionSquare {
        if (!(domes.containsKey(dome))){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.ALREADY_ADDED);
        }
        if (!domes.get(dome).isCanBeRemoved()){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.FIXE_NODELETE);
        }

        ok = true;
        eraseAmbient();
        domes.remove(dome);
        drawAmbient();
    }

    /**
     * We add a tourist in the area.
     * @Param color color with which we want to identify the turist.
     * @Param x Position x in which the turist is located.
     * @Param y Position y in which the turist is located.
     */
    public void touristArrive(String color, int x, int y) throws ExceptionSquare{
        if (turis.containsKey(color)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.ALREADY_ADDED);
        }
        if (!(0 < x && x <= dimensionX - dimensionX / 1000) || !(0 < y && y <= dimensionY - dimensionY / 10)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.BAD_LOCATION);
        }

        ok = true;
        eraseAmbient();
        Turist turist = new Turist(dimensionX, dimensionY, safetyDistance);
        turist.setColor(color);
        turist.setCor(x, y);
        turis.put(color, turist);
        drawAmbient();
    }

    /**
     *
     * @param color
     * @param x
     * @param y
     * @param type
     * @throws ExceptionSquare
     */
    public void touristArrive(String color, int x, int y, String type) throws ExceptionSquare {
        if (turis.containsKey(color)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.ALREADY_ADDED);
        }
        if (!(0 < x && x <= dimensionX - dimensionX / 1000) || !(0 < y && y <= dimensionY - dimensionY / 10)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.BAD_LOCATION);
        }
        String[] types = {"prudent", "perfectionist"};
        if (!(new ArrayList<>(List.of(types)).contains(type))) {
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NO_IS_A_TYPE);
        }

        ok = true;
        eraseAmbient();
        Turist turist = null;
        if (type.equals("prudent")){
            turist = new Prudent(dimensionX, dimensionY, safetyDistance);
        } else if (type.equals("perfectionist")){
            turist = new Perfectionist(dimensionX, dimensionY, safetyDistance);
        }
        turist.setColor(color);
        turist.setCor(x, y);
        turis.put(color, turist);
        drawAmbient();
    }
    
    /**
     * We change the position of an indicated tourist along with his angle of orientation.
     * @Param tourist color of the tourist we need.
     * @Param x x-axis position.
     * @Param y y axis position.
     * @Param angle angle to which it is facing.
     */
    public void touristMove(String tourist, int x, int y, int angle) throws ExceptionSquare {
        if (!(turis.containsKey(tourist))){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NOT_ADDED);
        }
        if (!(0 < x && x <= dimensionX - dimensionX / 1000) || !(0 < y && y <= dimensionY - dimensionY / 10)){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.BAD_LOCATION);
        }

        ok = true;
        eraseAmbient();
        turis.get(tourist).varyAngle(angle);
        turis.get(tourist).setXposition(x);
        turis.get(tourist).setYposition(y);
        drawAmbient();
    }
    
    /**
     * Take the photo and return it depending on the direction where the tourist is looking, 
     * which domes will appear at an angle of 180 degrees.
     * @Param tourist color of the tourist to be photographed.
     */
    public String[] touristTakePhoto(String tourist) throws ExceptionSquare {
        if (!(turis.containsKey(tourist))){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NOT_ADDED);
        }

        ok = true;
        ArrayList<String> domeViews = new ArrayList<>();
        Turist photographer = turis.get(tourist);
        int xCor = photographer.getXposition();
        int yCor = photographer.getYposition();
        int angleDir = photographer.getAngle();
        zone.changeColor("green");
        eraseAmbient();
        Dome dome;
        for (String e: domes()){
            dome = domes.get(e);
            if (!(dome instanceof Shy)){
                if (angleDir >= 270 && angleDir < 360){
                    if (angleDir == 270 && xCor < (dome.getXposition())){
                        domeViews.add(e);
                    }
                } else if (angleDir >= 0 && angleDir < 90){
                    if (angleDir == 0 && yCor > (dome.getYposition())){
                        domeViews.add(e);
                    }
                } else if (angleDir >= 90 && angleDir < 180){
                    if (angleDir == 90 && xCor > (dome.getXposition())){
                        domeViews.add(e);
                    }
                } else if(angleDir >= 180 && angleDir < 270){
                    if (angleDir == 180 && yCor < (dome.getYposition())){
                        domeViews.add(e);
                    }
                }
            } else {
                dome.setCor(xCor - 10, yCor - 10);
            }
        }
        zone.changeColor("blue");
        drawAmbient();
        String[] results = new String[domeViews.size() > 0 ? domeViews.size() : 1];
        if (photographer instanceof Perfectionist && requestedView.equals(domeViews)){
            domeViews.toArray(results);
        } else if (!(photographer instanceof Perfectionist)){
            domeViews.toArray(results);
        } else{
            results[0] = "Señor Perfeccionista, La foto no es la deseada";
        }
        return results;
    }
    
    /**
     * Take the photo and return it depending on the direction of the suggested angle.
     * @Param tourist color of the tourist to be photographed.
     * @Param viewAngle angle of vision from which you want to know the domes that can be observed
     */
    public String[] touristTakePhoto(String tourist, int viewAngle) throws ExceptionSquare {
        if (!(turis.containsKey(tourist))){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NOT_ADDED);
        }

        ok = true;
        ArrayList<String> domeViews = new ArrayList<>();
        Turist photographer = turis.get(tourist);
        int xCor = photographer.getXposition();
        int yCor = photographer.getYposition();
        ArrayList<String> domes = new ArrayList<>(Arrays.asList(domes()));
        Dome dome;
        for (String e: domes){
            dome = this.domes.get(e);
            if (!(dome instanceof Shy)){
                if (viewAngle >= 270 && viewAngle < 360){
                    if (viewAngle == 270 && xCor < (dome.getXposition())){
                        domeViews.add(e);
                    }
                } else if (viewAngle >= 0 && viewAngle < 90){
                    if (viewAngle == 0 && yCor > (dome.getYposition())){
                        domeViews.add(e);
                    }
                } else if (viewAngle >= 90 && viewAngle < 180){
                    if (viewAngle == 90 && xCor > (dome.getXposition())){
                        domeViews.add(e);
                    }
                } else if(viewAngle >= 180 && viewAngle < 270){
                    if (viewAngle == 180 && yCor < (dome.getYposition())){
                        domeViews.add(e);
                    }
                }
            } else {
                dome.setCor(xCor - 10, yCor - 10);
            }
        }
        zone.changeColor("blue");
        drawAmbient();
        String[] results = new String[domeViews.size() > 0 ? domeViews.size() : 1];
        if (photographer instanceof Perfectionist && requestedView.equals(domeViews)){
            domeViews.toArray(results);
        } else if (!(photographer instanceof Perfectionist)){
            domeViews.toArray(results);
        } else{
            results[0] = "Señor Perfeccionista, La foto no es la deseada";
        }
        return results;
    }
    
    /**
     * Some tourist takes the requested photo
     */
    public void takeRequestedPhoto(){
        try {
            ArrayList<String> Possible = new ArrayList<>(Arrays.asList(whoRequestedPhoto()));
            String choose = "";
            ArrayList<String> lossed = new ArrayList<>();
            int maxCant = 0;
            for (String e: Possible){
                int cant = 0;
                ArrayList<String> loss = new ArrayList<>();
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
            if (!Objects.equals(choose, "") && lossed.size() != 0){
                int difDistance = 0;
                int chooseAngle = turis.get(choose).getAngle();
                if (chooseAngle == 270 || chooseAngle == 90){
                    for (String i: lossed){
                        int dif = turis.get(choose).getXposition() - domes.get(i).getXposition();
                        if (dif > 0 && dif > difDistance){
                            dif = difDistance;
                        } else if (dif < 0 && dif < difDistance){
                            dif = difDistance;
                        }
                    }
                    if (difDistance > 0){
                        touristMove(choose, turis.get(choose).getXposition() - difDistance - safetyDistance,
                                turis.get(choose).getYposition(), chooseAngle);
                    } else {
                        touristMove(choose, turis.get(choose).getXposition() + difDistance + safetyDistance,
                                turis.get(choose).getYposition(), chooseAngle);
                    }
                } else if(chooseAngle == 0 || chooseAngle == 180){
                    for (String i: lossed){
                        int dif = turis.get(choose).getXposition() - domes.get(i).getXposition();
                        if (dif > 0 && dif > difDistance){
                            dif = difDistance;
                        } else if (dif < 0 && dif < difDistance){
                            dif = difDistance;
                        }
                    }
                    if (difDistance > 0){
                        touristMove(choose, turis.get(choose).getXposition(),
                                turis.get(choose).getYposition() - difDistance - safetyDistance, chooseAngle);
                    } else {
                        touristMove(choose, turis.get(choose).getXposition(),
                                turis.get(choose).getYposition() + difDistance + safetyDistance, chooseAngle);
                    }
                }
                touristTakePhoto(choose);
            } else{
                throw new ExceptionSquare(ExceptionSquare.NO_PHOTO);
            }
        } catch (ExceptionSquare a){
            System.out.println(a.getMessage());
        }
    }
    
    /**
     * Returns a list of tourists who can take the requested photo
     */
    public String[] whoRequestedPhoto() {
        ArrayList<String> allowed = new ArrayList<>();
        try {
            ok = true;
            String[] turiss = tourists();
            for (String e : turiss) {
                String[] photo = touristTakePhoto(e);
                ArrayList<String> viewTouris = new ArrayList<>(Arrays.asList(photo));
                if (requestedView.equals(viewTouris)) {
                    allowed.add(e);
                }
            }
        } catch (ExceptionSquare b) {
            ok = false;
            System.out.println(b.getMessage());
        } finally {
            String[] result = new String[allowed.size()];
            allowed.toArray(result);
            return result;
        }
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
    public int[] dome(String dome) throws ExceptionSquare {
        if (!(domes.containsKey(dome))){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NOT_ADDED);
        }

        ok = true;
        int[] coor = new int[2];
        Dome searchDome = domes.get(dome);
        coor[0] = searchDome.getXposition();
        coor[1] = searchDome.getYposition();
        return coor;
    }
    
    /**
     * We return the coordinates of the desired turist.
     * @Param tourist Color that identifies the turist.
     */
    public int[] tourist(String tourist) throws ExceptionSquare {
        if (!(turis.containsKey(tourist))){
            ok = false;
            throw new ExceptionSquare(ExceptionSquare.NOT_ADDED);
        }

        ok = true;
        int[] coor = new int[3];
        Turist searchTourist = turis.get(tourist);
        coor[0] = searchTourist.getXposition();
        coor[1] = searchTourist.getYposition();
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
            System.exit(0);
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
            ArrayList<String> orderAppear;
            if (requestedView.size() == 0){
                orderAppear = new ArrayList<>(Arrays.asList(domes()));
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
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> exist = new ArrayList<>(Arrays.asList(domes()));;
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
        try {
            // sobrecargar con el setforgroun
            String[] order = new String[cant]; // [null, null, null,...]
            ArrayList<Integer> viewInt = new ArrayList<>();
            for (int e: views){
                viewInt.add(e);
            }

            for (int i = 0; i < viewInt.size(); i++){
                ArrayList<String> colorsExist = colorsNuse();
                if (!(colorsExist.size() == 0)){
                    String color = colorsExist.get(0);
                    int positionView = viewInt.indexOf(i + 1);
                    order[positionView] = color; // [3, 1, 2], index(1) = 2
                    addDome(color, domes[i][0], domes[i][1]); // addDome("magenta", xi, yi)
                }
            }
            requestedView = new ArrayList<>(Arrays.asList(order));
            for (int i = 0; i < requestedView.size(); i++){
                this.domes.get(order[i]).setPosition(i);
            }
        } catch (ExceptionSquare c){
            System.out.println(c.getMessage());
        }
    }
}
