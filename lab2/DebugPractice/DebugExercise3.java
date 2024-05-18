/**
 * Created by jug on 1/22/18.
 *
 * The code is correct.
 * However,food.csv provides  wrong data  on line 89 which is "kroger turnip 0.45 -387128732".
 * You can find that the num of available is negative( -387128732),and it can not be.
 */
public class DebugExercise3 {
    public static int countTurnips(In in) {
        int totalTurnips = 0;
        while (!in.isEmpty()) {
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            int numAvailable = in.readInt();
            if (foodType.equals("turnip")) {
                int newTotal = totalTurnips + numAvailable;
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        In in = new In("foods.csv");
        System.out.println(countTurnips(in));
    }
}
