package com.company;

import com.company.entities.Driver;
import com.company.entities.Truck;
import com.company.service.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path WRITE_PATH = Paths.get("./truck.json");
    public static Path WRITE_PATH1 = Paths.get("./driver.json");

    public static void main(String[] args) throws Exception {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        ServiceImpl service = new ServiceImpl();

            while (true) {
                buttons();
                int b = scanner1.nextInt();

                trucks();
                System.out.println("\n--------------select a track-------------");
                int a = scanner1.nextInt();

                for (Truck s : service.getTrucks()) {
                    if (a == s.getId()) {
                        System.out.println(s + "\n");
                    }

                }

                switch (b) {
                    case 1:

                        System.out.println("\t\tDriver changed successfully\n");
                        service.changeDriver(a);
                        drivers();
                        trucks();

                        break;
                    case 2:
                        System.out.println("\t\tStart Driving\n");
                        service.startDriving(a);
                        drivers();
                        trucks();

                        break;
                    case 3:
                        System.out.println("\t\tStart Repair\n");
                        service.startRepair(a);
                        drivers();
                        trucks();
                        break;
                    case 4:
                        System.out.println("\t\tchange the condition of the truck\n");
                        service.changeTruckState();
                        drivers();
                        trucks();
                        break;
                }
            }
    }

    public static void buttons() {

        System.out.println("\n\nPress 1 to change Driver\n" +
                "Press 2 to send to the Route\n" +
                "Press 3 to send to the Repairing\n"+
                "Press 4 to change the condition of the truck\n");
    }


    public static String readTtuck() {
        return getString(WRITE_PATH);
    }

    public static String readDriver() {
        return getString(WRITE_PATH1);
    }

    private static String getString(Path writePath1) {
        StringBuilder json = new StringBuilder();
        try (FileReader fr = new FileReader(String.valueOf(writePath1))) {
            int a;
            while ((a = fr.read()) != -1) {
                json.append((char) a);
            }
            return json.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return json.toString();
    }

    public static void trucks(){
        System.out.println("""
                #   | Bus              |  Driver   |  State
                ————|——————————————————|———————————|—————————>""");
        ServiceImpl service = new ServiceImpl();
        for (Truck s : service.getTrucks()) {
            System.out.println(s);
        }
    }

    public static void drivers(){
        System.out.println("""
                #   | Driver           | Bus
                ————|——————————————————|——————————>""");
        ServiceImpl service = new ServiceImpl();
        for (Driver s : service.getDrivers()) {
            System.out.println(s);
        }
    }
}