package AdventOfCode2023.day6;

import java.math.BigInteger;

public class WaitForIt {
    public static void main(String[] args) {
        String time = "40929790";
        String distance = "215106415051100";

        BigInteger waysToWin = calculateWaysToWinPart2(time, distance);

        System.out.println(STR."Total ways to beat the record: \{waysToWin}");
    }

    private static BigInteger calculateWaysToWinPart2(String time, String distance) {
        BigInteger raceDuration = new BigInteger(time);
        BigInteger recordDistance = new BigInteger(distance);

        BigInteger waysToWin = BigInteger.ZERO;
        for (BigInteger holdDownTime = BigInteger.ZERO; holdDownTime.compareTo(raceDuration) < 0; holdDownTime = holdDownTime.add(BigInteger.ONE)) {
            BigInteger remainingTime = raceDuration.subtract(holdDownTime);
            BigInteger distanceTraveled = holdDownTime.multiply(remainingTime);
            if (distanceTraveled.compareTo(recordDistance) > 0) {
                waysToWin = waysToWin.add(BigInteger.ONE);
            }
        }
        return waysToWin;
    }

    private static int calculateWaysToWin(int[] time, int[] distance) {
        int totalWaysToWin = 1;
        for (int i = 0; i < time.length; i++) {
            int waysToWin = calculateWaysToWinForRace(time[i], distance[i]);
            totalWaysToWin *= waysToWin;
        }
        return totalWaysToWin;
    }

    private static int calculateWaysToWinForRace(int time, int distance) {
        int waysToWin = 0;
        for (int holdDown = 1; holdDown <= time; holdDown++){
            int remainingTime = time - holdDown;
            int maxDistance = holdDown * remainingTime;
            if (maxDistance > distance) {
                waysToWin++;
            }
            
        }
        return waysToWin;
    }
}
