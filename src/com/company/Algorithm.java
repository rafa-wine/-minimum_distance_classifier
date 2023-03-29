package com.company;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private List<double [][]> opPoint;
    public List<double [][]> rPoint;
    public List<double [][]> objNew;
    private double[][] results;
    public int cont=1;

    public Algorithm() {
        rPoint = new ArrayList<>();
        opPoint = new ArrayList<>();
        objNew = new ArrayList<>();
    }

    private void referencePoint() {

        double sum = 0;

        for(double[][] clase : rPoint) {
            double[][] p = new double[2][1];
            for (int i = 0; i < clase.length; i++) {
                sum = 0;
                for (int j = 0; j < clase[0].length; j++) {
                    sum += clase[i][j];
                }
                p[i][0] = sum/clase[0].length;
            }
            opPoint.add(p);
        }
    }
    private void calculateDistances(double[][] newData) {

        double d = 0;
        double sum = 0;
        int noClase = 1;

        for(double[][] opP : opPoint) {
            for(int i = 0; i < newData[0].length; i++) {
                for(int j = 0; j < newData.length; j++) {
                    sum += (Math.pow((newData[j][i] - opP[j][0]), 2));
                }
                d = Math.sqrt(sum);
                if(noClase == 1) {
                    results[i][0] = d;
                    results[i][1] = noClase;
                }
                if(noClase > 1) {
                    if (d < results[i][0]){
                        results[i][0] = d;
                        results[i][1] = noClase;
                    }
                }
                sum = 0;
            }
            noClase++;
        }
    }
    public void addDataOfInput(double[][] input) {
        rPoint.add(input);
    }

    public void runAlgorithm() {

        results = new double[cont][2];

        //- 1: Calcular Punto de referencia por Clase previamente definida.
        referencePoint();


        //- 2: Calcular Distancias.
        calculateDistances(objNew.get(0));
    }

    public String outcome() {
        String tex = "";
        int cont = 0;
        int fam;

        for (int x = 0; x < results.length; x++) {
            cont++;
            fam = (int) results[x][1];
            tex = tex + "Therefore Object " + cont + " belongs to Family " + fam + "\n";
        }

        return tex;
    }
}
