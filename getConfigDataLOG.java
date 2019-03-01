package com.hichingwa;
/**
 *
 * @author Ichingwa
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.*;


public class getConfigDataLOG {
    public static int monitor, projector, processor, keyboard;
    public static int memory, scanner, hard_drive, sysMem;
    String metadata;

    public int getConfigData(String m_data, int m, int p, int pr, int k, int me, int sc, int hd, int sy) {

        metadata = m_data;
        monitor = m;
        processor = pr;
        scanner = sc;
        hard_drive = hd;
        keyboard = k;
        memory = me;
        projector = p;
        sysMem = sy;

        return 0;
    }

    public void consoleDisplayConfig() {
        System.out.println("Configuration File Data");
        System.out.println("Monitor = " + monitor + " ms/cycle");
        System.out.println("Processor = " + processor + " ms/cycle");
        System.out.println("Scanner = " + scanner + " ms/cycle");
        System.out.println("Hard Drive = " + hard_drive + " ms/cycle");
        System.out.println("Keyboard = " + keyboard + " ms/cycle");
        System.out.println("Memory = " + memory + " ms/cycle");
        System.out.println("Projector = " + projector + " ms/cycle");
        System.out.println("Logged to: monitor and logfile_1.lgf");
    }

    public void fileDisplayConfig () {
        try {
            PrintWriter pw = new PrintWriter("logfile_1.lgf");
            pw.println("Configuration File Data");
            pw.println("Monitor = " + monitor + " ms/cycle");
            pw.println("Processor = " + processor + " ms/cycle");
            pw.println("Scanner = " + scanner + " ms/cycle");
            pw.println("Hard Drive = " + hard_drive + " ms/cycle");
            pw.println("Keyboard = " + keyboard + " ms/cycle");
            pw.println("Memory = " + memory + " ms/cycle");
            pw.println("Projector = " + projector + " ms/cycle");
            pw.println("Logged to: monitor and logfile_1.lgf");
            pw.close();
        } catch (IOException e) {

        }
    }

}
