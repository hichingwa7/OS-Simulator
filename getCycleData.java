package com.hichingwa;

/**
 *
 * @author Ichingwa
 */

import javafx.util.Pair;
import java.util.*;

public class getCycleData implements Runnable {
    static ArrayList<String> cycleStringElement = new ArrayList<>();
    static ArrayList<Integer> cycleNumElement = new ArrayList<>();
    static ArrayList <Pair <String, Integer> > simData = new ArrayList <Pair <String, Integer> > ();
    public int sz_1;

    public void getData (ArrayList key, ArrayList metas) {

        cycleStringElement = key;
        cycleNumElement = metas;
        sz_1 = cycleNumElement.size();

        for (int i =0; i < sz_1; i++) {
             simData.add(new Pair(cycleStringElement.get(i), cycleNumElement.get(i)));
        }
        //displayCycleData();
    }

    public void displayCycleData () {
        System.out.println("Meta-Data Metrics");
        for(Pair<String, Integer> pair : simData) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }

    }

    public void run () {

        simTimer sim = new simTimer();

        for(Pair<String, Integer> pair : simData) {

            sim.threadSchedule(pair.getKey(), pair.getValue());


        }
    }
}

