package square;

import javax.swing.*;
import java.util.Arrays;

public class AcceptanceTesting {

    private void valentinAccepted(){
        try {
            int finish = 0;
            while (finish != 1){
                // Me llamo Valentina y acabo de llegar a Berlin
                // Dos chicos muy amables me pasaron esta app para que pueda tomar las mejores fotos durante mi viaje
                // Primero me dijeron que introdujera las dimensiones de la zona y la distancia de seguridad que quiero tener con los otros turistas
                Square valentinaZone = new Square(250, 300, 20);

                //Ya cree la zona, pero no me aparece aún nada, creo que debo de hacerla visible
                valentinaZone.makeVisible();

                // Ay que bien!! Bueno, el chico me dijo que cuando lo creara pusiera las estructuras que estaba viendo
                // Por suerte me dio un mapa aero con casillas, así me puedo ubicar de en que coordenadas está el monumento
                // valentinaZone.addDome(castillo, 150, 200);

                // Ay, se me olvido, verdad que la chica dijo que los describiera con el color que veía en sus tejados
                valentinaZone.addDome("red", 150, 200, 'n');
                valentinaZone.addDome("violet", 110, 25, 'n');
                valentinaZone.addDome("orange", 200, 200, 'n');
                valentinaZone.addDome("white", 20, 40, 'n');

                // Bueno, ahora creo que solo falta agregarme a mi misa... me asignaré un color rosa, que es mi favorito
                // Creo que según el mapa que me dieron, estoy más o menos por aquí
                valentinaZone.touristArrive("pink", 100, 100, 'n');

                // Creo que debería ubicarme con otro ángulo en la aplicación
                valentinaZone.touristMove("pink", 100, 100, 270);

                // Wow, que bonito como quedan los objetos, revisare que todo se haya ejecutado
                valentinaZone.ok();

                // Parece que todo esta correcto, tomare la foto a ver que puedo tomar desde aquí
                String[] photo = valentinaZone.touristTakePhoto("pink");
                JOptionPane.showMessageDialog(null, "En la foto se ve: " + Arrays.toString(photo));

                // Parece que desde aquí puedo ver esos 3, veamos en otro ángulo que foto tomaría
                String[] anotherPhoto = valentinaZone.touristTakePhoto("pink", 90);
                JOptionPane.showMessageDialog(null, "En la foto se ve: " + Arrays.toString(anotherPhoto));

                //¡Oh! Ahi solo vería ese
                // Terminamos?
                finish = Integer.parseInt(JOptionPane.showInputDialog("Desea terminas?\n" + "1.Si\n" + "0.No\n"));
            }
        } catch (ExceptionSquare tv){
            JOptionPane.showMessageDialog(null, tv.getMessage());
        }
    }

    private void companyAccepted(){
        try {
            int finish = 0;
            while (finish != 1){
                // Buen día, somos una empresa de turismo, nos recomendaron adquirir esta aplicación para poder sugerir a nuestros turistas las mejores posiciones
                // Para sus fotografías de recuerdo, primero adjuntaremos la zona a evaluar, sus monumentos y el orden de aparición en la foto
                int[] dimensions = {300, 250, 4};
                int[][] domesUbications = {{110, 25}, {150, 200}, {200, 200}, {200, 100}};
                int[] desiredView = {3, 1, 2, 4};
                Square companyZone = new Square(dimensions, domesUbications, desiredView);

                // Creo que deberemos de darle aquí para poder ver si esta correcto todo
                companyZone.makeVisible();

                // Vaya me falto un domo, voy a agregarlo
                companyZone.addDome("black", 19, 37, 'n');

                // Veamos como quedo la vista deseada
                String[] desired = companyZone.getRequestedView();
                JOptionPane.showMessageDialog(null, "LA vista deseada es:" + Arrays.toString(desired));

                // Parece que me equivoque en la vista deseada, la voy a cambiar
                int[] newDesiredView = {3, 1, 2};
                String[] newDesired = {desired[newDesiredView[0] - 1], desired[newDesiredView[1] - 1], desired[newDesiredView[2] - 1]};
                companyZone.defineRequestedPhoto(newDesired);

                // Okey, parece que todo esta definido, ahora solo será agregar algunos turistas para probar
                companyZone.touristArrive("pink", 100, 100, 'n');
                companyZone.touristArrive("violet", 79, 14, 'n');

                // Voy a revisar con esto si los domos y los turistas se agregaron correctamente
                String[] domes = companyZone.domes();
                String[] tourist = companyZone.tourists();
                JOptionPane.showMessageDialog(null, "Los domos: " + Arrays.toString(domes) + "\n"
                        + "Los turistas: " + Arrays.toString(tourist));

                // Para probar posibles resultados, voy a cambiar los ángulos de vision de los turistas teóricos
                companyZone.touristMove("pink", 100, 100, 270);
                companyZone.touristMove("violet", 79, 14, 90);

                // Bueno, veamos quien me puede tomar la foto deseada
                String[] whoCan = companyZone.whoRequestedPhoto();
                JOptionPane.showMessageDialog(null, "Los posibles fotógrafos son:" + Arrays.toString(whoCan));

                // Terminamos?

                finish = Integer.parseInt(JOptionPane.showInputDialog("Desea terminas?\n" + "1.Si\n" + "0.No\n"));
            }
        } catch (ExceptionSquare tc){

        }
    }

    public void main(String[] args){
        valentinAccepted();
        companyAccepted();
    }
}
