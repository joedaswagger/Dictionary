package com.company;

import javax.xml.transform.Result;
import java.io.*;

public class Dictionnaire {
    private String nomFichier;
    private String tableau1D[];
    private BufferedReader reader;

    /**
     * Constructeur
     * @param nomFichier
     */
    public Dictionnaire(String nomFichier) {
        this.nomFichier = nomFichier;
        tableau1D = new String[compterLigne()];
    }

    /**
     * Cette méthode compte le nombre des lignes pour faire la longueur
     * du tableau des mots du dictionnaire dans le constructeur
     * @return nombre de lignes
     */
    public int compterLigne() {
        int nbLignes = 0;
        String motsDictionnaire;
        try {
            reader = new BufferedReader(new FileReader(nomFichier));
            while ((motsDictionnaire = reader.readLine()) != null) {
                nbLignes++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur de création de fichier");

        } catch (IOException e) {
            System.out.println("Erreur de lecture de fichier");
        }
        return nbLignes;
    }

    /**
     * Méthode pour remplir le tableau du dictionnaire
     */
    public void remplirTableau() {
        int lignes = 0;
        String motsDictionnaire;
        try {
            reader = new BufferedReader(new FileReader(nomFichier));
            while ((motsDictionnaire = reader.readLine()) != null) {
                tableau1D[lignes] = motsDictionnaire;
                lignes++;

            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur de création de fichier");

        } catch (IOException e) {
            System.out.println("Erreur de lecture de fichier");
        }
    }

    /**
     * Méthode pour vérifier si le mot entré existe dans le dictionnaire
     * @param mot
     */
    public void verifier(String mot) {
        boolean verif = false;
        for (int i = 0; i < tableau1D.length; i++) {
            if (tableau1D[i].equals(mot)) {
                System.out.println("Ce mot est correcte. Pas besoin de corriger");
                verif = true;
            }
        }   if (!verif) {
            distance(mot);
        }
    }

    /**
     * Méthode pour calculer la distance entre les mots (Distance de Levenshtein)
     * @param mot
     */
    public void distance(String mot) {
        int[] tableauDistance = new int[compterLigne()];
        int distance = 0;
        for (int i = 0; i < tableau1D.length; i++) {
            int[][] tableauLevenshtein = new int[mot.length() + 1][tableau1D[i].length() + 1];
            int valeurSub = 0;
           for (int j = 0; j < tableauLevenshtein.length ; j++ ){
               for (int k = 0; k < tableauLevenshtein[j].length; k++){
                   tableauLevenshtein[j][0] = j;
                   tableauLevenshtein[0][k] = k;
               }
           }
            for (int l = 1; l < mot.length() +1; l++) {
                for (int m = 1; m < tableau1D[i].length() +1 ; m++) {
                    if (mot.charAt(l - 1) == tableau1D[i].charAt(m - 1)) {
                        valeurSub = 0;
                    } else {
                        valeurSub = 1;
                    }

                    int valeur1 = tableauLevenshtein[l - 1][m] + 1;
                    int valeur2 = tableauLevenshtein[l][m - 1] + 1;
                    int valeur3 = tableauLevenshtein[l - 1][m - 1] + valeurSub;
                    int minRelatif = Math.min(valeur1, valeur2);
                    int min = Math.min(minRelatif, valeur3);
                    tableauLevenshtein[l][m] = min;
                }
            }
            distance = tableauLevenshtein[mot.length()][tableau1D[i].length()];
            tableauDistance[i] = distance;

        }
        Resultat resultat = new Resultat(tableau1D, tableauDistance);
        resultat.trier();

        System.out.println(resultat.toString());
    }


}
