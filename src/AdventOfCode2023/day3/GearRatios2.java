package AdventOfCode2023.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios2 {
    public static void main(String[] args) {
        int sum = 0;

        ArrayList<Asterisk> asterisks = new ArrayList<>();
        ArrayList<String> file = readFile("src/AdventOfCode2023/day3/text.txt");

        for (int i = 0; i < file.size(); i++) {
            String line = file.get(i);
            ArrayList<Integer> asteriskIndices = getAsteriskIndices(line);

            for(int index : asteriskIndices){
                Asterisk asterisk = new Asterisk();
                if(getAdjacentNumber(asterisk, i, index, file) > 1){
                    asterisks.add(asterisk);
                }
            }
        }
        for(Asterisk asterisk : asterisks){
            int multResult = asterisk.numbers.getFirst();
            for (int i = 1; i < asterisk.numbers.size(); i++) {
                multResult *= asterisk.numbers.get(i);
            }
            sum += multResult;
        }

        System.out.printf("The sum of numbers: = %d\n", sum);
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

    public static ArrayList<Integer> getAsteriskIndices(String line){
        ArrayList<Integer> asteriskIndices = new ArrayList<>();
        Pattern p = Pattern.compile("\\*");
        Matcher m = p.matcher(line);

        while (m.find()){
            asteriskIndices.add(m.start());
        }
        return asteriskIndices;
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

    public static void getNorthNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(row == 0){
            return;
        }
        String aboveLine = file.get(row - 1);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(aboveLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(asteriskIndex >= location.get("start") && asteriskIndex < location.get("end")){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasNorth = true;
                if(location.get("start") < asteriskIndex){
                    asterisk.hasNorthWest = true;
                }
                if(location.get("end") < asteriskIndex){
                    asterisk.hasNorthEast = true;
                }
                return;
            }
        }
    }

    public static void getNorthWestNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(row == 0 || asteriskIndex == 0){
            return;
        }
        String aboveLine = file.get(row - 1);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(aboveLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(location.get("end") == asteriskIndex){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasNorthWest = true;
                return;
            }
        }
    }

    public static void getNorthEastNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(row == 0 || asteriskIndex == file.get(row).length() - 1){
            return;
        }
        String aboveLine = file.get(row - 1);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(aboveLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(location.get("start") == asteriskIndex + 1){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasNorthEast = true;
                return;
            }
        }
    }

    public static void getEastNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(asteriskIndex == file.get(row).length() - 1){
            return;
        }
        String currentLine = file.get(row);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(currentLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(location.get("start") == asteriskIndex + 1){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasEast = true;
                return;
            }
        }
    }

    public static void getWestNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(asteriskIndex == 0){
            return;
        }
        String currentLine = file.get(row);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(currentLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(location.get("end") == asteriskIndex){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasWest = true;
                return;
            }
        }
    }

    public static void getSouthNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(row == file.size() - 1){
            return;
        }
        String belowLine = file.get(row + 1);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(belowLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(asteriskIndex >= location.get("start") && asteriskIndex < location.get("end")){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasSouth = true;
                if(location.get("start") < asteriskIndex){
                    asterisk.hasSouthWest = true;
                }
                if(location.get("end") < asteriskIndex){
                    asterisk.hasSouthEast = true;
                }
                return;
            }
        }
    }

    public static void getSouthEastNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(row == file.size() - 1 || asteriskIndex == file.get(row).length() - 1){
            return;
        }
        String belowLine = file.get(row + 1);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(belowLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(location.get("start") == asteriskIndex + 1){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasSouthEast = true;
                return;
            }
        }
    }

    public static void getSouthWestNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        if(row == file.size() - 1 || asteriskIndex == 0){
            return;
        }
        String belowLine = file.get(row + 1);
        ArrayList<HashMap<String, Integer>> numberLocations = getNumbers(belowLine);

        for(HashMap<String, Integer> location : numberLocations){
            if(location.get("end") == asteriskIndex){
                asterisk.numbers.add(location.get("number"));
                asterisk.hasSouthWest = true;
                return;
            }
        }
    }

    public static int getAdjacentNumber(Asterisk asterisk, int row, int asteriskIndex, ArrayList<String> file){
        getNorthNumber(asterisk, row, asteriskIndex, file);
        if(!asterisk.hasNorthWest){
            getNorthWestNumber(asterisk, row, asteriskIndex, file);
        }
        if(!asterisk.hasNorthEast){
            getNorthEastNumber(asterisk, row, asteriskIndex, file);
        }

        getSouthNumber(asterisk, row, asteriskIndex, file);
        if(!asterisk.hasSouthEast){
            getSouthEastNumber(asterisk, row, asteriskIndex, file);
        }
        if(!asterisk.hasSouthWest){
            getSouthWestNumber(asterisk, row, asteriskIndex, file);
        }

        getEastNumber(asterisk, row, asteriskIndex, file);
        getWestNumber(asterisk, row, asteriskIndex, file);

        return asterisk.numbers.size();
    }
}
