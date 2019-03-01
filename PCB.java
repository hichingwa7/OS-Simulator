package com.hichingwa;
/**
 *
 * @author Ichingwa
 */

public class PCB {
    private int processState;
    private int start = 0;
    private int ready = 1;
    private int running= 2;
    private int wait = 3;
    private int exit = 4;


    public int getStart() {

        processState = start;
        return processState;
    }

    public int getReady() {
        processState = ready;
        return processState;
    }

    public int getRunning() {
        processState = running;
        return processState;
    }

    public int getWait() {
        processState = wait;
        return processState;
    }

    public int getExit() {
        processState = exit;
        return processState;
    }
}

