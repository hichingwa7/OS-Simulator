package com.hichingwa;
/**
 *
 * @author Ichingwa
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class config {


    public static String metadata = " ";
    public static MetaDataQ[] meta;


    public static int monitor, projector, processor, keyboard;
    public static int memory, scanner, hard_drive, sysMem;
    /**
     *
     * @param files_3
     * @return
     * @throws Exception
     * The function reads a configuration file and logs its metrics on the console and logfile_1.lgf
     */

    public static void readFiles(File[] files_3) throws IOException {


        String st = " ";
        getConfigDataLOG log = new getConfigDataLOG();

        for (File file : files_3) {
            File f = new File(String.valueOf(file));
            BufferedReader br = new BufferedReader(new FileReader(f));

            for (st = br.readLine(); st != null; st = br.readLine())

                if (st.contains(":")) {
                    String[] arrOfStr = st.split(":");
                    if (arrOfStr[0].contains("File Path")) {
                        metadata = arrOfStr[1];
                    } else if (arrOfStr[0].contains("Monitor")) {
                        monitor = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("Processor") != -1) {
                        processor = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("Scanner") != -1) {
                        scanner = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("Hard drive") != -1) {
                        hard_drive = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("Keyboard") != -1) {
                        keyboard = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("Memory") != -1) {
                        memory = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("Projector") != -1) {
                        projector = Integer.parseInt(arrOfStr[1].trim());
                    } else if (arrOfStr[0].indexOf("System memory") != -1) {
                        sysMem = Integer.parseInt(arrOfStr[1].trim());
                    }
                }
        }
        log.getConfigData(metadata, monitor, processor, scanner, hard_drive, keyboard, memory, projector, sysMem);
        //log.consoleDisplayConfig();
        log.fileDisplayConfig();
    }




    /**
     *
     * @param files_1
     * @return
     * @throws Exception
     * The function reads a metadata file, gets the cycle times for each metric and logs the information on the console
     */

    public static void readMetaFile (File[] files_1) throws IOException {

        ArrayList<String> alist = new ArrayList<String>();
        ArrayList<Integer> alist_1 = new ArrayList<Integer>();
        ArrayList<String> alist_2 = new ArrayList<String>();
        ArrayList<String> cycleStringElement = new ArrayList();
        ArrayList<Integer> cycleNumElement = new ArrayList<>();
        String st_1 = " ";
        String arrOfStr_1 = " ";
        String arrOfStr_2 = " ";
        String arrOfStr_3 = " ";
        String arrOfStr_4 = " ";
        String arrOfStr_5 = " ";
        String arrOfStr_6 = " ";
        int cycle_time = 0;

        int sz = 0;
        int sz_1 = 0;

        for (File metadata : files_1) {
            File metaFile = new File(String.valueOf(metadata));
            BufferedReader meta_br = new BufferedReader(new FileReader(metaFile));

            for (st_1 = meta_br.readLine(); st_1 != null; st_1 = meta_br.readLine())

                alist.add(st_1);

            arrOfStr_1 = alist.toString();
            arrOfStr_2 = arrOfStr_1.replaceAll("[\\-\\+\\.\\^:,]","");

            arrOfStr_3= arrOfStr_2.replace("Start Program MetaData Code", "").replace("End Program MetaData Code", "");
            arrOfStr_4 = arrOfStr_3.trim();

            arrOfStr_5 = arrOfStr_4.replaceAll(("\\s+"), "").replaceAll(";", "");

            arrOfStr_6 = arrOfStr_5.replaceAll("\\[", "").replaceAll("\\]", "");

            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(arrOfStr_5);
            while (m.find()) {
                alist_1.add(Integer.parseInt(m.group()));

            }


            Pattern p_1 = Pattern.compile("(\\D+[}])");

            Matcher m_1 = p_1.matcher(arrOfStr_6);
            while (m_1.find())
                alist_2.add(m_1.group());

            sz_1 = alist_1.size();
            MetaDataQ[] meta = new MetaDataQ[sz_1];

            for (int i = 0; i < sz_1; i++) {
                meta[i] = new MetaDataQ(alist_1.get(i), alist_2.get(i));
                cycle_time = meta[i].metas * getCycle(meta[i].key);
                cycleStringElement.add(meta[i].key);
                cycleNumElement.add(cycle_time);

            }
             getCycleData data = new getCycleData();
             data.getData(cycleStringElement, cycleNumElement);

        }
    }

    /**
     *
     * @param s
     * @return
     * The function returns the cycle times of the configuration file metrics to be used for processing with
     * those of the configuration file
     **/
    public static int getCycle(String s){

        if (s.contains("I{harddrive}")) {
            return hard_drive;
        }else if (s.contains("M{allocate}")) {
            Random random =new Random();
            sysMem = random.nextInt(sysMem);
            return sysMem;
        }else if (s.contains("P{run}")){
            return processor;
        }else if (s.contains("O{projector}")){
            return projector;
        }else if (s.contains("I{keyboard}")){
            return keyboard;
        }else if (s.contains("I{scanner}") ) {
            return scanner;
        } else if (s.contains("M{block}") ){
            return memory;
        }else if (s.contains("O{monitor}") ){
            return monitor;
        }

        return 0;
    }
}