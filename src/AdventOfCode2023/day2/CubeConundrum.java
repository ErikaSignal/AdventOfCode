package AdventOfCode2023.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {
    public static void main(String[] args) {
        String text = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                """;

        try {
            Pattern pattern = Pattern.compile("(\\d+) (\\w+)");
            BufferedReader reader = new BufferedReader(new FileReader("src/AdventOfCode2023/day2/text.txt"));
            int val = 0;
            for(String line : reader.lines().toList()) { //text.split("\n")) {
                String[] arr = line.split(":");
                int gameNr = Integer.parseInt(arr[0].replace("Game", "").trim());
                int blue = 0;
                int green = 0;
                int red = 0;
                for(String round : arr[1].split(";")){
                    for(String cubes : round.split(",")){
                        Matcher m = pattern.matcher(cubes);
                        if(m.find()){
                            switch (m.group(2)){
                                case "blue":
                                    int newBlue = Integer.parseInt(m.group(1));
                                    if(newBlue > blue){
                                        blue = newBlue;
                                    }
                                    break;
                                case "green":
                                    int newGreen = Integer.parseInt(m.group(1));
                                    if(newGreen > green){
                                        green = newGreen;
                                    }
                                    break;
                                case "red":
                                    int newRed = Integer.parseInt(m.group(1));
                                    if(newRed > red){
                                        red = newRed;
                                    }
                                    break;
                            }
                        }
                    }
                }
                System.out.println(STR."\{gameNr} \{blue} \{green} \{red}");
                //12 red cubes, 13 green cubes, and 14 blue cubes
               /* if(red <= 12 && green <= 13 && blue <= 14){
                    val += gameNr;
                }*/
                System.out.println(STR."\{gameNr} \{blue * green * red}");
                val += (blue * green * red);
            }
            System.out.println(val);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
