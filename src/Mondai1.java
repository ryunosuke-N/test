import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GolfCourse {
    private final int[] pars = {
            4, 4, 3, 4, 5, 4, 5, 3, 4,
            4, 3, 4, 5, 4, 3, 4, 5, 4
    };

    public int getPar(int hole) {
        return pars[hole];
    }

    public int getHoleCount() {
        return pars.length;
    }
}

class GolfScoreCalculator {
    private final GolfCourse course;

    public GolfScoreCalculator(GolfCourse course) {
        this.course = course;
    }

    public int calculateScore(List<Integer> scores) {
        int total = 0;

        int holeCount = Math.min(scores.size(), course.getHoleCount());

        for (int i = 0; i < holeCount; i++) {
            total += scores.get(i) - course.getPar(i);
        }

        return total;
    }
}

public class Mondai1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GolfCourse course = new GolfCourse();
        GolfScoreCalculator calculator = new GolfScoreCalculator(course);

        while (true) {

            System.out.print("Input > ");
            String input = scanner.nextLine();

            // 空入力チェック
            if (input.trim().isEmpty()) {
                System.out.println("空入力です。再入力してください。");
                continue;
            }

            // 使用可能文字チェック
            if (!input.matches("[0-9, ]+")) {
                System.out.println("数字、カンマ、半角スペースのみ入力可能です。");
                continue;
            }

            try {
                String[] tokens = input.split(",");
                List<Integer> scores = new ArrayList<>();

                for (String token : tokens) {

                    token = token.trim();

                    if (token.isEmpty()) {
                        continue;
                    }

                    int score = Integer.parseInt(token);

                    if (score <= 0) {
                        throw new IllegalArgumentException(
                                "0以下の整数は入力できません。");
                    }

                    // 19ホール目以降は無視
                    if (scores.size() < 18) {
                        scores.add(score);
                    }
                }

                if (scores.isEmpty()) {
                    System.out.println("スコアが入力されていません。");
                    continue;
                }

                int result = calculator.calculateScore(scores);

                System.out.print(scores.size()
                        + "ホール終了して、");

                if (result > 0) {
                    System.out.println("+" + result + "です");
                } else if (result == 0) {
                    System.out.println("±0です");
                } else {
                    System.out.println(result + "です");
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("数値形式が正しくありません。");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}