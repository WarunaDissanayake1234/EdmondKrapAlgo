package com.IIT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UserInterface {

    Digraph digraph;
    String fileName;

    public void welcomeMessage() {
        System.out.println("\n\n      Welcome to the NETWORK FLOW SOLVER!\n" +
                "===============================================\n" +
                "This application will allow you to calculate\n" +
                "the maximum flow of the network given,\n" +
                "which you can provide in .txt file,\n" +
                "in the ...project/samples/ folder.\n\n" +
                "Press Enter to continue...");
        try{System.in.read();}
        catch(Exception e){}
    }


    // MAIN MENU METHODS
    public String menuList() {
        System.out.println(
                "\nChoose option from the menu:\n"
                        + "-------------------------------\n"
                        + "Q:\t Quit program\n"
                        + "L:\t Load network data from .txt file\n"
                        + "F:\t Max Flow Algorithm - Show steps\n"
                        + "T:\t Pure algorithm execution time\n");
        Scanner sc = new Scanner(System.in);
        // Return option chosen by user
        return sc.nextLine().toLowerCase();
    }
    // Menu option - L
    public void loadFileOption() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type name of the file: ");
        fileName = sc.nextLine();
        try {
            loadFile(fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Menu option - S
    public void maxFlowOption() {
        try {
            computeMaxFlow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // Menu option - T
    public void maxFlowPure() {
        try {
            pureMaxFlow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // APPLICATION METHODS
    private void loadFile(String fileName) throws Exception {
        try {
            String dirPath = System.getProperty("user.dir");
            String fullPath = dirPath + File.separator + "samples" + File.separator + fileName + ".txt";
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(fullPath)));
            // Create Digraph as Adjacency List
            digraph = createDigraph(readFile);
            // Display Digraph as Adjacency List
            if (digraph == null) {
                System.out.println("The file is empty!! Try different file.");
            } else {
                digraph.printDigraph();
                System.out.println("\nThe file loaded:\n" + fullPath);
                System.out.println("... Digraph has been created!\n-----------------------------");
                System.out.println("No. of Vertices in this Digraph: " + digraph.getSize());
                System.out.println("- Source Vertex: " + digraph.getSourceVertex());
                System.out.println("- Sink Vertex: " + digraph.getSinkVertex());
            }
            readFile.close();
        }
        catch (FileNotFoundException error) {
            throw new Exception("\n[EXCEPTION ERROR]: File not found!\n");
        }
    }

    private Digraph createDigraph(Scanner readFile) {
        // Check if file not empty
        if (readFile.hasNext()) {
            final int NO_OF_VERTICES = Integer.parseInt(readFile.nextLine());
            // Initialize Digraph as Adjacency List with instances of its Vertices
            Digraph digraph = new Digraph(NO_OF_VERTICES);
            System.out.println("Creating Digraph...\n");

            while (readFile.hasNext()) {
                String line = readFile.nextLine();
                String[] lineArr = line.split(" ");
                digraph.addEdge(Integer.parseInt(lineArr[0]), Integer.parseInt(lineArr[1]), Integer.parseInt(lineArr[2]));
            }
            return digraph;
        } else {
            return null;
        }
    }

    private void computeMaxFlow() throws Exception {
        if (digraph == null) {
            throw new Exception("\n[EXCEPTION ERROR]: Load File first to create Digraph!");
        }
        System.out.println("NETWORK LOADED FROM: " + fileName + ".txt");
        digraph.resetFlows();
        EdmondsKarp maxFlow = new EdmondsKarp(digraph);
        System.out.println("\n###############################\n" +
                "MAXIMUM FLOW OF THIS NETWORK: " + maxFlow.getResult());

    }

    private void pureMaxFlow() throws Exception {
        if (digraph == null) {
            throw new Exception("\n[EXCEPTION ERROR]: Load File first to create Digraph!");
        }
        System.out.println("NETWORK LOADED FROM: " + fileName + ".txt");
        digraph.resetFlows();
        PureEdmondsKarp maxFlow = new PureEdmondsKarp(digraph);
        System.out.println("\n###############################\n" +
                "MAXIMUM FLOW OF THIS NETWORK: " + maxFlow.getResult());
    }
}
