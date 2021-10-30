package com.company;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        String getarq;
        String getarq1;

        System.out.println("Digite aqui o nome do arquivo");
        Scanner scan = new Scanner(System.in);
        getarq = scan.nextLine();
        System.out.println("Digite o nome da string que deseja procurar");
        getarq1 = scan.nextLine();


        InputStream is = new FileInputStream(getarq);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String A = br.readLine();

        if(A != null && A.contains(getarq1)){
            System.out.println(A);
        }
        while(A != null){
            A = br.readLine();
            if(A != null && A.contains(getarq1)){
                System.out.println(A);
            }
        }


         br.close();


    }

}
