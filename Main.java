package com.hichingwa;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.commons.io.filefilter.RegexFileFilter;


/**
 *
 * @author Ichingwa
 */

public class Main {

    public static void main(String[] args) throws IOException {
        Runnable sim = new getCycleData();
        Thread thread = new Thread(sim);

        try {
            Scanner input = new Scanner(System.in);
            File directory = new File(".");
            String pattern = "[cC]onfig_[1-4].conf";
            System.out.println("Enter the configuration file: ");
            String input_text = input.next(pattern);
            input.close();
            FileFilter filter;
            File[] files;
            File[] files_1;
            filter = new RegexFileFilter(input_text);
            files = directory.listFiles(filter);

            config.readFiles(files);
            String pattern_1 = "[tT]est_[1-9][a-z].mdf";
            FileFilter filter_1 = new RegexFileFilter(pattern_1);
            files_1 = directory.listFiles(filter_1);
            config.readMetaFile(files_1);
        } catch (InputMismatchException exception) {
            System.out.println("No such config file");
        }
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {

        }
    }
}

