import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = null;


        try {
            reader = new BufferedReader(new FileReader("text1.txt"));
            String textOne = reader.readLine();

            reader = new BufferedReader(new FileReader("text2.txt"));
            String textTwo = reader.readLine();

            double[] results =  {
                findAverageWordLength(textOne), findAverageWordLength(textTwo), calculateTypeTokenRatio(textOne), calculateTypeTokenRatio(textTwo),
                        calculateHapaxLegomenaRatio(textOne), calculateHapaxLegomenaRatio(textTwo), calculateAverageSentenceLength(textOne), calculateAverageSentenceLength(textTwo)
            };

            double similarity = abs(results[0]-results[1]) * 11 + abs(results[2]-results[3]) * 33 + abs(results[4]-results[5]) * 50 + abs(results[6]-results[7]) * 0.4;

            System.out.println("The Average Word Length in Text One is: " + results[0]);
            System.out.println("The Type Token Ratio of Text One is: " + results[2]);
            System.out.println("The Hapax Legomena Ratio of Text One is: " + results[4]);
            System.out.println("The Average Sentence Length in Text One is: " + results[6]);
            System.out.println("_________________________________");
            System.out.println("The Average Word Length in Text Two is: " + results[1]);
            System.out.println("The Type Token Ratio of Text Two is: " + results[3]);
            System.out.println("The Hapax Legomena Ratio of Text Two is: " + results[5]);
            System.out.println("The Average Sentence Length in Text Two is: " + results[7]);
            System.out.println("_________________________________");
            System.out.println("Similarity: " + similarity);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static double findAverageWordLength(String text) {
        int count = 0;
        double sum = 0;
        String[] words = text.replaceAll("\\p{Punct}", "").split("\\s+");
        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }
        double average = 0;
        if (count > 0)
            average = sum / count;
        return average;
    }

    public static double calculateTypeTokenRatio(String text) {
        HashSet<String> h = new HashSet<>();
        String[] words = text.replaceAll("\\p{Punct}", "").split("\\s+");
        double count = words.length;
        for (String word : words) {
            h.add(word);
        }
        return h.size() / count;
    }

    public static double calculateHapaxLegomenaRatio(String text) {
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        String[] words = text.replaceAll("\\p{Punct}", "").split("\\s+");
        double count = words.length;
        for (String word : words) {
            if (h.get(word) == null) {
                h.put(word, 1);
            } else {
                h.put(word, (h.get(word) + 1));
            }
        }

        int uniqueCount = 0;
        for (String word : words) {
            if (h.get(word) == 1) {
                uniqueCount++;
            }
        }
        return uniqueCount / count;
    }

    public static double calculateAverageSentenceLength(String text) {
        int count = text.split("[!?.]+").length;
        String[] words = text.replaceAll("\\p{Punct}", "").split("\\s+");
        int countWords = words.length;

        return countWords / count;

    }

}


