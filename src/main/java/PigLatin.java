import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class PigLatin {

    public void tester() {
        String[] lines = new String[8]; 
        try {
            File myFile = new File("words.txt");
            Scanner myReader = new Scanner(myFile);
            int counter = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines[counter] = data;
                counter++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("there are " + lines.length + " lines");
        for (int i = 0 ; i < lines.length; i++) {
            System.out.println(pigLatin(lines[i]));
        }
    }

    public int findFirstA(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).equals("a")) {
                return i;
            }
        }
        return -1;
    }

    public int findFirstAorB(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i + 1).equals("a") || word.substring(i, i + 1).equals("b")) {
                return i;
            }
        }
        return -1;
    }

    public int findFirstVowel(String word) {
        String vowels = "aeiou";
        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(word.charAt(i)) != -1) {
                return i;
            }
        }
        return -1;
    }

    public String pigLatin(String word) {
        int vowelIndex = findFirstVowel(word);

        if (vowelIndex == -1) { // No vowels
            return word + "ay";
        } else if (vowelIndex == 0) { // Starts with a vowel
            return word + "way";
        } else if (vowelIndex > 0 && vowelIndex + 1 < word.length() 
                   && word.substring(vowelIndex - 1, vowelIndex + 1).equals("qu")) { 
            // Handles "qu" as a single consonant sound
            String prefix = word.substring(0, vowelIndex + 1); // Includes "qu"
            String rest = word.substring(vowelIndex + 1);
            return rest + prefix + "ay";
        } else { // Vowel is in the middle or end
            String prefix = word.substring(0, vowelIndex);
            String rest = word.substring(vowelIndex);
            return rest + prefix + "ay";
        }
    }

    public static void main(String[] args) {
        PigLatin pigLatin = new PigLatin();
        pigLatin.tester();
    }
}
