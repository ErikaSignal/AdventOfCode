package AdventOfCode2023.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Card implements Comparable<Card>{

    static int FIVE_OF_A_KIND = 6;
    static int FOUR_OF_A_KIND = 5;
    static int FULL_HOUSE = 4;
    static int THREE_OF_A_KIND = 3;
    static int TWO_PAIR = 2;
    static int ONE_PAIR = 1;
    static int HIGH_CARD = 0;

    int type = HIGH_CARD;
    int[] hand = new int[5];
    long bid;
    int[] typeAmount = new int[15];
    private long rank;

    public Card(String line) {
        int i = 0;

        Arrays.fill(typeAmount, 0);

        String[] lineArr = line.split(" ");

        bid = Long.parseLong(lineArr[1]);

        for (String c : lineArr[0].split("")) {
            switch (c) {
                case "A" -> hand[i] = 14;
                case "K" -> hand[i] = 13;
                case "Q" -> hand[i] = 12;
                case "J" -> hand[i] = 0;
                case "T" -> hand[i] = 10;
                case "9" -> hand[i] = 9;
                case "8" -> hand[i] = 8;
                case "7" -> hand[i] = 7;
                case "6" -> hand[i] = 6;
                case "5" -> hand[i] = 5;
                case "4" -> hand[i] = 4;
                case "3" -> hand[i] = 3;
                case "2" -> hand[i] = 2;
            }
            typeAmount[hand[i]]++;
            i++;
        }

        List<Integer> amounts = new ArrayList<>();
        for (int k = 1; k < typeAmount.length; k++) {
            int v = typeAmount[k];
            if(v == 0) continue;
            amounts.add(v);
        }

        if(typeAmount[0] == 5){
            type = Card.FIVE_OF_A_KIND;
            return;
        }

        Collections.sort(amounts, (a, b) -> b - a);

        boolean usedTypeAmount = false;

        for (int j : amounts) {
            if(!usedTypeAmount){
                j += typeAmount[0];
            }

            if (j == 5) {
                type = FIVE_OF_A_KIND;
                break;
            }
            if (j == 4) {
                type = FOUR_OF_A_KIND;
                break;
            }
            if (j == 3) {
                if (type == Card.ONE_PAIR) {
                    type = Card.FULL_HOUSE;
                } else {
                    type = THREE_OF_A_KIND;
                }
                usedTypeAmount = true;
            }
            if (j == 2) {
                if (type == Card.ONE_PAIR) {
                    type = Card.TWO_PAIR;
                } else if (type == Card.THREE_OF_A_KIND) {
                    type = Card.FULL_HOUSE;
                } else {
                    type = ONE_PAIR;
                }
                usedTypeAmount = true;
            }
        }
    }

    @Override
    public int compareTo(Card card){
        if(card.type != this.type){
            return Integer.compare(card.type, this.type);
        }
        for (int i = 0; i < 5; i++) {
            if(card.hand[i] != this.hand[i]){
                return Integer.compare(card.hand[i], this.hand[i]);
            }
        }
        return 0;
    }

    public void setRank(long rank){
        this.rank = rank;
    }

    public long getScore(){
        return bid * rank;
    }
}
