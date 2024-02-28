package AdventOfCode2023.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

    public static void main(String[] args) {
        int sum = 0;

        ArrayList<String> file = readFile("src/AdventOfCode2023/day3/text.txt");
        for (int i = 0; i < file.size(); i++) {
            String line = file.get(i);
            ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(line);

            for(HashMap<String, Integer> number : numberLocations){
                if(hasAdjacentSymbol(i, number, file)){
                    sum += number.get("number");
                }
            }
        }

        System.out.printf("Sum = %d\n", sum);
    }

    public static ArrayList<String> readFile(String filename){
        ArrayList<String> file = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            while (line != null){
                file.add(line);
                line = reader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }

    public static ArrayList<HashMap<String, Integer>> getNumbers(String line){
        ArrayList<HashMap<String, Integer>> numberLocations = new ArrayList<>();

        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(line);

        while (m.find()){
            HashMap<String, Integer> numberLocation = new HashMap<>();
            numberLocation.put("start", m.start());
            numberLocation.put("end", m.end());
            numberLocation.put("number", Integer.parseInt(m.group(1)));
            numberLocations.add(numberLocation);
        }
        return numberLocations;
    }

    public static boolean isSymbol(char c){
        return !Character.isDigit(c) && (c != '.');
    }

    public static boolean hasAdjacentSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        return hasNorthSymbol(row, number, file) ||
                hasNorthWestSymbol(row, number, file) ||
                hasNorthEastSymbol(row, number, file) ||
                hasSouthSymbol(row, number, file) ||
                hasSouthWestSymbol(row, number, file) ||
                hasSouthEastSymbol(row, number, file) ||
                hasEastSymbol(row, number, file) ||
                hasWestSymbol(row, number, file);
    }

    public static boolean hasNorthSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(row == 0){
            return false;
        }
        String aboveLine = file.get(row - 1);
        for (int i = number.get("start"); i < number.get("end"); i++) {
            char c = aboveLine.charAt(i);
            if(isSymbol(c)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasSouthSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(row == file.size() - 1){
            return false;
        }
        String belowLine = file.get(row + 1);
        for (int i = number.get("start"); i < number.get("end"); i++) {
            char character = belowLine.charAt(i);
            if(isSymbol(character)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasEastSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        String line = file.get(row);

        if((number.get("end") - 1) == line.length() - 1){
            return false;
        }
        char rightChar = line.charAt(number.get("end"));
        return isSymbol(rightChar);
    }

    public static boolean hasWestSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(number.get("start") == 0) {
            return false;
        }
        String line = file.get(row);
        char leftChar = line.charAt(number.get("start") - 1);
        return isSymbol(leftChar);
    }

    public static boolean hasNorthWestSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(row == 0 || number.get("start") == 0){
            return false;
        }
        char nwCar = file.get(row - 1).charAt(number.get("start") - 1);
        return isSymbol(nwCar);
    }

    public static boolean hasNorthEastSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(row == 0 || (number.get("end") -1) == file.get(row).length() - 1){
            return false;
        }
        char northEastCharacter = file.get(row - 1).charAt(number.get("end"));
        return isSymbol(northEastCharacter);
    }

    public static boolean hasSouthWestSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(row == file.size() - 1 || number.get("start") == 0){
            return false;
        }
        char southWestCharacter = file.get(row + 1).charAt(number.get("start") - 1);
        return isSymbol(southWestCharacter);
    }

    public static boolean hasSouthEastSymbol(int row, HashMap<String, Integer> number, ArrayList<String> file){
        if(row == file.size() - 1 || (number.get("end") - 1) == file.get(row).length() - 1){
            return false;
        }
        char southEastCharacter = file.get(row + 1).charAt(number.get("end"));
        return isSymbol(southEastCharacter);
    }
}