package AdventOfCode2023.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Trebuchet {

    private static final Map<String, Integer> STRING_NUMBER_MAP = new HashMap<>() {{
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
    }};

    public static void main(String[] args) {
        try(Scanner input = new Scanner(new File("src/AdventOfCode2023/day1/text.txt"))) {
            int sum = 0;

            while(input.hasNextLine()) {
                sum += computeCalibrationNum(input.nextLine());
            }
            System.out.println(sum);

        } catch (FileNotFoundException e) {
            System.out.println(STR."File not found. \{e.getMessage()}");
            System.exit(1);
        }
    }

    private static int computeCalibrationNum(String line) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                sb.append(line.charAt(i));
                break;
            } else if (getProperNumber(line.substring(i)) > 0) {
                sb.append(getProperNumber(line.substring(i)));
                break;
            }
        }

        for(int j = line.length() - 1; j >= 0; j--) {
            if (Character.isDigit(line.charAt(j))) {
                sb.append(line.charAt(j));
                break;
            } else if (getProperNumber(line.substring(j)) > 0) {
                sb.append(getProperNumber(line.substring(j)));
                break;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static int getProperNumber(String string) {
        for(String number : STRING_NUMBER_MAP.keySet()) {
            if (string.startsWith(number)) {
                return STRING_NUMBER_MAP.get(number);
            }
        }
        return -1;
    }
}
