package AdventOfCode2023.day7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CamelCards {
    public static void main(String[] args) {
        String text = """
                32T3K 765
                T55J5 684
                KK677 28
                KTJJT 220
                QQQJA 483
                """;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/AdventOfCode2023/day7/text.txt"));
            long val = 0;
            List<Card> cards = new ArrayList<>();

            for(String line : reader.lines().toList()){ //text.split("\n")){ //
                cards.add(new Card(line));
            }
            Collections.sort(cards);

            int rank = cards.size();
            for(Card card : cards){
                card.setRank(rank);
                val += card.getScore();
                rank--;
            }
            System.out.println(val);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
