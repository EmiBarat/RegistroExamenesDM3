/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicafechas;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emiliano Barat
 */
public class PracticaFechas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String fecha = "1985-05-17";
        
        
        
        Calendar nacimiento = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            nacimiento.setTime(sdf.parse(fecha));
        } catch (ParseException ex) {
            System.out.println("error");
        }
        

        
        Calendar actual = Calendar.getInstance();
        
        int diff = actual.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        
        if(nacimiento.get(Calendar.DAY_OF_YEAR) < actual.get(Calendar.DAY_OF_YEAR)){
            diff--;
        }
        
        System.out.println("Fecha de nac");
        System.out.println(nacimiento.getTime());
        System.out.println(nacimiento.get(Calendar.DAY_OF_YEAR));
        
        System.out.println("Actual");
        System.out.println(actual.getTime());
        System.out.println(actual.get(Calendar.DAY_OF_YEAR));
        
        System.out.println(diff);

    }

}
