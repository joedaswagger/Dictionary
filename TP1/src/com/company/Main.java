package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);

        boolean loop = false;
        do {
            System.out.println("Entrez votre mot. Appuyez sur Enter avec aucune lettre pour quitter le programme.");
            String mot = clavier.nextLine().trim().toLowerCase();
            if (mot.equals("")) {
                loop = true;
                break;
            }
            Dictionnaire dictionnaire = new Dictionnaire("dictionnaire.txt");
            dictionnaire.remplirTableau();
            dictionnaire.verifier(mot);


        } while (loop = false);


    }
}
