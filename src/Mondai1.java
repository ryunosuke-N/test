import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ゴルフコースクラス
 * 各ホールのパーを管理する
 */
class GolfCourse {

    // 18ホールのパー
    private final int[] pars = {
            4, 4, 3, 4, 5, 4, 5, 3, 4,
            4, 3, 4, 5, 4, 3, 4, 5, 4
    };

    /**
     * 指定ホールのパーを取得
     */
    public int getPar(int hole) {
        return pars[hole];
    }

    /**
     * ホール数取得
     */
    public int getHoleCount() {
        return pars.length;
    }
}

/**
 * 選手クラス
 * 問題2で追加
 */
class Player {

    // 選手名
    private String name;

    // 18ホール分のスコア
    private List<Integer> scores;

    /**
     * コンストラクタ
     */
    public Player(String name) {
        this.name = name;
        this.scores = new ArrayList<>();
    }

    /**
     * スコア追加
     */
    public void addScore(int score) {
        scores.add(score);
    }

    /**
     * スコア取得
     */
    public List<Integer> getScores() {
        return scores;
    }

    /**
     * 名前取得
     */
    public String getName() {
        return name;
    }
}

/**
 * スコア計算クラス
 */
class GolfScoreCalculator {

    private GolfCourse course;

    public GolfScoreCalculator(GolfCourse course) {
        this.course = course;
    }

    /**
     * トータルスコア計算
     */
    public int calculateScore(List<Integer> scores) {

        int total = 0;

        for (int i = 0; i < course.getHoleCount(); i++) {

            // 実打数 - パー
            total += scores.get(i) - course.getPar(i);
        }

        return total;
    }
}

/**
 * メインクラス
 */
public class Mondai1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GolfCourse course = new GolfCourse();

        GolfScoreCalculator calculator =
                new GolfScoreCalculator(course);

        System.out.println(
                "入力例:");
        System.out.println(
                "田中,鈴木,4,4,3,4,5,4,5,3,4,4,3,4,5,4,3,4,5,4,"
              + "5,4,3,4,6,4,5,3,4,4,3,4,5,4,3,4,5,4");

        while (true) {

            System.out.print("Input > ");

            String input = scanner.nextLine();

            // 空入力チェック
            if (input.trim().isEmpty()) {
                System.out.println("空入力です。");
                continue;
            }

            // カンマで分割
            String[] data = input.split(",");

            // 名前2個 + スコア36個 = 38項目
            if (data.length < 38) {
                System.out.println("入力数が不足しています。");
                continue;
            }

            try {

                //--------------------------------
                // 選手名取得
                //--------------------------------

                String player1Name = data[0].trim();
                String player2Name = data[1].trim();

                //--------------------------------
                // Playerインスタンス生成
                //--------------------------------

                Player player1 = new Player(player1Name);
                Player player2 = new Player(player2Name);

                //--------------------------------
                // 1人目のスコア
                //--------------------------------

                for (int i = 2; i < 20; i++) {

                    int score =
                            Integer.parseInt(data[i].trim());

                    if (score <= 0) {
                        throw new IllegalArgumentException(
                                "0以下の値があります");
                    }

                    player1.addScore(score);
                }

                //--------------------------------
                // 2人目のスコア
                //--------------------------------

                for (int i = 20; i < 38; i++) {

                    int score =
                            Integer.parseInt(data[i].trim());

                    if (score <= 0) {
                        throw new IllegalArgumentException(
                                "0以下の値があります");
                    }

                    player2.addScore(score);
                }

                //--------------------------------
                // スコア計算
                //--------------------------------

                int result1 =
                        calculator.calculateScore(
                                player1.getScores());

                int result2 =
                        calculator.calculateScore(
                                player2.getScores());

                //--------------------------------
                // 結果表示
                //--------------------------------

                System.out.println();
                System.out.println("結果");

                System.out.println(
                        player1.getName()
                        + " : "
                        + result1);

                System.out.println(
                        player2.getName()
                        + " : "
                        + result2);

                //--------------------------------
                // 勝敗判定
                //--------------------------------

                if (result1 < result2) {

                    System.out.println(
                            player1.getName()
                            + " の勝利");

                } else if (result2 < result1) {

                    System.out.println(
                            player2.getName()
                            + " の勝利");

                } else {

                    System.out.println("引き分け");
                }

                break;

            } catch (NumberFormatException e) {

                System.out.println(
                        "数字以外が入力されています");

            } catch (IllegalArgumentException e) {

                System.out.println(
                        e.getMessage());
            }
        }

        scanner.close();
    }
}