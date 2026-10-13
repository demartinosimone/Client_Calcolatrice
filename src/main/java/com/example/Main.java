package com.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Connessione al server...");
        Socket socket = new Socket("localhost", 3000);
        System.out.println("Connesso!");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner tastiera = new Scanner(System.in);

        while (true) {
            System.out.print("Inserisci il primo numero (o 'exit' per uscire): ");
            String num1 = tastiera.nextLine();
            
            // Invia il primo inserimento al server
            out.println(num1);

            if (num1.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Inserisci l'operatore (+, -, *, /): ");
            String op = tastiera.nextLine();
            out.println(op);

            System.out.print("Inserisci il secondo numero: ");
            String num2 = tastiera.nextLine();
            out.println(num2);

            // Legge e stampa il risultato finale
            String risposta = in.readLine();
            System.out.println("RISULTATO DAL SERVER: " + risposta + "\n");
        }

        // Chiude tutto
        tastiera.close();
        in.close();
        out.close();
        socket.close();
        System.out.println("Client chiuso.");
    }
}