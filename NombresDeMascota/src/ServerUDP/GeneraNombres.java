package ServerUDP;

/*
 * TAREA 3 DAR OPCIONAL
 * JUAN MANUEL LÓPEZ TORRALBA
 * (basado en el ejemplo ORACULO UDP)
 */

import java.util.*;

/**
 *
 * @author jjramos
 */
public class GeneraNombres {
    Random aleatorio;
    
    static final String nombresMacho[]={"ShuCaNijOoh","Chechu","Jonat'an", "er Jonat'an",
        "er Maikel","Maikel","erRubEn","er Kevin", "er kristian","Yerai",
        "Yerai","er Yerai","Roko","Coco","Poco","Freyo"};
    
    static final String nombresHembra[]={"ShuCaNijaah","La Vane","La jessi", "Zulaima",
        "La yumalai","la Sarah","Zoraida","Zulema", "Lorena","Anusa",
        "Arañazos","Amedia","Ariel","Akeita","Akita","Axel"};
    

    /** Creates a new instance of Bola8 */
    public GeneraNombres() {
        aleatorio=new Random();
    }
    
    String obtenerPrediccionMacho(){
        return nombresMacho[aleatorio.nextInt(nombresMacho.length)];
    }
    String obtenerPrediccionHembra(){
        return nombresHembra[aleatorio.nextInt(nombresHembra.length)];
    }
}
