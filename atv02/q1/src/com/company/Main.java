package com.company;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String Origem;
        String Destino;

        System.out.println(" Digite o nome do arquivo de origem");
        Origem = scan.next();
        File file = new File(Origem);

        long initial = System.currentTimeMillis();
        byte[] bytes = Files.readAllBytes(file.toPath());

        String textOrigin = new String(bytes, "UTF-8");
        Destino = scan.next();

        PrintWriter pw = new PrintWriter(Destino);
        pw.println(textOrigin);

        System.out.println("Tempo total de uma cópia é: " + (System.currentTimeMillis() - initial));
        pw.close();
    }
}
