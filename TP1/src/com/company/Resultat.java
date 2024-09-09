package com.company;

public class Resultat {
    private String tableau1D[];
    private int tableauDistance[];

    /**
     * Constructeur des résultats
     *
     * @param tableau1D
     * @param tableauDistance
     */

    public Resultat(String tableau1D[], int tableauDistance[]) {
        this.tableau1D = tableau1D;
        this.tableauDistance = tableauDistance;
    }

    /**
     * Méthode de tri
     */
    public void trier() {
        for (int i = tableauDistance.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++)
                if (tableauDistance[i] < tableauDistance[j]) {

                    int x = tableauDistance[i];
                    String s = tableau1D[i];
                    tableauDistance[i] = tableauDistance[j];
                    tableauDistance[j] = x;
                    tableau1D[i] = tableau1D[j];
                    tableau1D[j] = s;
                }
        }
    }

    /**
     * Méthode pour afficher les 5 mots les plus proches ainsi que leurs distances
     *
     * @return La réponse
     */
    public String toString() {

        return "La distance pour le mot " + tableau1D[0] + " est de " + tableauDistance[0] +
                "\nLa distance pour le mot " + tableau1D[1] + " est de " + tableauDistance[1] +
                "\nLa distance pour le mot" + tableau1D[2] + " est de " + tableauDistance[2] +
                "\nLa distance pour le mot " + tableau1D[3] + " est de " + tableauDistance[3] +
                "\nLa distance pour le mot" + tableau1D[4] + " est de " + tableauDistance[4];
    }
}
