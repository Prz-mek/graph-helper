package graph_helper.classes;

import graph_helper.exceptions.DataError;
import graph_helper.exceptions.FileError;
import graph_helper.exceptions.NotNumericError;
import graph_helper.exceptions.WrongDimensionsError;
import org.json.JSONException;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static int greetings(){
        System.out.println("Witaj w systemie pomagających w obliczeniach nad grafami!");
        System.out.println("Proszę podać numer operacji, którą chciałbyś wykonać na grafie:");
        System.out.println("(1)Najmniejsze drzewo rozpinające");
        System.out.println("(2)Najkrótsza ścieżka pomiędzy punktami");
        System.out.println("(3)Problem komiwojażera");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        return mode;
    }
    private static int[][] askForFile(){
        System.out.println("Proszę podać ścieżkę do pliku");
        Scanner scanner = new Scanner(System.in);
        DataReader reader = new DataReader();
        try {
            int[][] matrix = reader.getWeightsMatrix(scanner.next());
            return matrix;
        } catch (NotNumericError n) {
            System.out.println("Wszystkie dane w pliku muszą być numeryczne!");
            return null;
        } catch (DataError d){
            System.out.println("Następujący graf nie jest wspierany (np. zawiera ujemne długości ścieżek)!");
            return null;
        } catch (WrongDimensionsError w){
            System.out.println("Macierz musi mieć równą liczbę kolumn i wierszy!");
            return null;
        } catch (FileError f){
            System.out.println("Podano ścieżkę do pliku który nie istnieje");
            return null;
        } catch (RuntimeException r){
            System.out.println("Zły format pliku!");
            return null;
        }
    }

    private static void printRoute(int[] points){
        for(int i = 0; i < points.length - 1; i++){
            System.out.print(points[i] + "->");
        }
        System.out.println(points[points.length-1]);
    }

    private static void printPairs(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j<i; j++){
                if(arr[j][i] != 0){
                    System.out.println(i + " <-> " + j);
                }
            }
        }
    }
    public static void main ( String [] arguments ) {
        int mode = greetings();
        int[][] matrix = askForFile();
        if (matrix != null){
            switch (mode) {
                case 1:
                    System.out.println("Najmniejsze drzewo rozpinające dla podanego grafu");
                    MinimalSpanTreeAlgorithm algo1 = new MinimalSpanTreeAlgorithm();
                    int[][] answer1 = algo1.applyAlg(matrix);
                    printPairs(answer1);
                    break;
                case 2:

                case 3:
                    System.out.println("Rozwiązanie problemu komiwojażera");

                    try {
                        TspDynamicAlgorithm algo3 = new TspDynamicAlgorithm();
                        int[] answer3 = algo3.applyAlg(matrix);
                        printRoute(answer3);
                    } catch (IllegalArgumentException e){
                        System.out.println("Macierz nieodpowiednia dla algorytmu. Sprawdź czy:");
                        System.out.println("-macierz jest kwadratowa");
                        System.out.println("-macierz nie jest za mała (min. rozmiar to 3x3");
                        System.out.println("-macierz nie jest za duża (maks. rozmiar to 20x20");
                        break;
                    }
                    break;
            }
        } else {
            System.out.println("Proszę wczytać odpowiedni plik");
        }
    }
}
