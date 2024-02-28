package AdventOfCode2023.day3;

import java.util.ArrayList;

public class Asterisk {
    ArrayList<Integer> numbers;
    boolean hasNorthWest;
    boolean hasNorth;
    boolean hasNorthEast;
    boolean hasEast;
    boolean hasSouthEast;
    boolean hasSouth;
    boolean hasSouthWest;
    boolean hasWest;

    public Asterisk(){
        numbers = new ArrayList<>();
    }

    @Override
    public String toString(){
        return STR."Asterisk{, hasNorthWest=\{hasNorthWest}, hasNorth=\{hasNorth}, hasNorthEast=\{hasNorthEast}, hasEast=\{hasEast}, hasSouthEast=\{hasSouthEast}, hasSouth=\{hasSouth}, hasSouthWest=\{hasSouthWest}, hasWest=\{hasWest}}";
    }
}
