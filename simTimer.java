package com.hichingwa;
/**
 *
 * @author Ichingwa
 */

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class simTimer {

    Timer timer;
    static double arrivalTime = 0.000;  //Time at which the process operation arrives in ready queue
    static double completionTime; //Time at which process operation completes its execution
    static int burstTime; // Time required by a process operation for CPU execution
    static double turnArroundTime; //Time difference between completion time and arrival time
    static double waitingTime; //Time difference between turn around time and burst time
    static String opData = " ";
    static int process = 1;
    static long memoryAllocation;
    Object lock = new Object();
    NumberFormat nf = NumberFormat.getInstance(new Locale("EN", "USA"));

    public void arrivalSimTim (double arrTime) {

        arrivalTime = arrTime;

    }

    public void setProcess () {
        if (opData.contains("A{finish}")) {
            process = process + 1;
        }
    }

    public void setMemory () {
        Random random =new Random();
        config.sysMem = random.nextInt(config.sysMem);
        memoryAllocation = config.sysMem;
    }

    public void threadSchedule (String simulatorData, int seconds) {


        opData = simulatorData;
        timer = new Timer();
        burstTime = seconds;
        timer.schedule(new scheduleTask(), burstTime);

        try {
            synchronized (lock) {

                lock.wait(burstTime);

            }
        } catch (InterruptedException e){

        }
    }


    class scheduleTask extends TimerTask {

            public void run() {

                synchronized (lock) {

                    nf.setMaximumFractionDigits(3);
                    waitingTime = 0.001;
                    completionTime = arrivalTime + ((float)(burstTime)/1000);
                    turnArroundTime = completionTime + waitingTime;

                    if (opData.contains("S{begin}")) {
                        System.out.println(nf.format(arrivalTime) + " - Simulator Program Starting" + "\n" + nf.format(completionTime) + " - OS: preparing process 1");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("A{begin}")) {
                        System.out.println(nf.format(arrivalTime) + " - OS: starting process " + process);
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("P{run}")) {
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": start processing action" + "\n" + nf.format(completionTime) + " - Process " + process + ": end processing action");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("M{allocate}")) {
                        setMemory();
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": Allocating Memory" + "\n" + nf.format(completionTime) + " - Process " + process + ": memory allocated at: " +String.format("0x%08X", memoryAllocation));
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("O{monitor}")) {
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": start monitor output" + "\n" + nf.format(completionTime) + " - Process " + process + ": end monitor output");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("O{projector}")) {
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": start projector output" + "\n" + nf.format(completionTime) + " - Process " + process + ": end projector output");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("I{harddrive}")) {
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": start harddrive input" + "\n" + nf.format(completionTime) + " - Process " + process + ": end harddrive input");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("I{keyboard}")) {
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": start keyboard input" + "\n" + nf.format(completionTime) + " - Process " + process + ": end keyboard input");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("M{block}")) {
                        System.out.println(nf.format(arrivalTime) + " - Process " + process + ": start memory blocking" + "\n" + nf.format(completionTime) + " - Process " + process + ": end memory blocking");
                        arrivalSimTim(turnArroundTime);
                    } else if (opData.contains("A{finish}")) {
                        arrivalSimTim(turnArroundTime);
                        System.out.println(nf.format(arrivalTime) + " - OS: removing process " + process);
                        setProcess();
                    } else if (opData.contains("S{finish}")) {
                        System.out.println(nf.format(completionTime) + " - Simulator Program ending");
                    }
                    lock.notify();
                }
            }
    }
}
