package fr.norsys.controleur;

import java.util.HashMap;
import java.util.Map;

public class TestThread implements Runnable {

    @Override
    public void run() {
        final Map<String, String> map = new HashMap<String, String>();
        for ( int i = 0; i < 1000; i++ ) {
            map.put( Integer.toString( i ), "valeur exemple " + i );
        }

        /*
         * for (Entry<String, String> entrySet : map.entrySet()){
         * entrySet.getValue(); }
         */
    }
}
