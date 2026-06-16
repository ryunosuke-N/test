import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ゴルフコースクラス
 * 各ホールのパーを管理する
 */

/**
 * 選手クラス
 * 名前とスコアを保持する
 */
class Player {

    // 選手名
    private String name;

    // 18ホール分のスコア
    private List<Integer> scores;

    /**
     * コンストラクタ
     */
    public Player(String name, List<Integer> scores) {
        this.name = name;
        this.scores = scores;
    }

    /**
     * 選手名取得
     */
    public String getName() {
        return name;
    }

    /**
     * スコア取得
     */
    public List<Integer> getScores() {
        return scores;
    }
}

/**
 * スコア計算クラス
 */


/**
 * メインクラス
 */
public class Mondai2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GolfCourse course = new GolfCourse();
        GolfScoreCalculator calculator =
                new GolfScoreCalculator(course);

        while (true) {

            System.out.println(
                    "入力形式：選手1,選手2,18ホール分,18ホール分");

            System.out.print("Input > ");

            String input = scanner.nextLine();

            // 空入力チェック
            if (input.trim().isEmpty()) {
                System.out.println("空入力です。");
                continue;
            }

            // カンマで分割
            String[] data = input.split(",");

            // 必須項目数チェック
            // 選手名2つ + スコア36個 = 38個
            if (data.length != 38) {
                System.out.println(
                        "入力数が不正です。38項目入力してください。");
                continue;
            }

            try {

                // ==========================
                // 選手名取得
                // ==========================
                String player1Name = data[0].trim();
                String player2Name = data[1].trim();

                // ==========================
                // 選手1のスコア
                // ==========================
                List<Integer> player1Scores =
                        new ArrayList<>();

                for (int i = 2; i < 20; i++) {

                    int score =
                            Integer.parseInt(data[i].trim());

                    if (score <= 0) {
                        throw new IllegalArgumentException(
                                "0以下の数値は入力できません。");
                    }

                    player1Scores.add(score);
                }

                // ==========================
                // 選手2のスコア
                // ==========================
                List<Integer> player2Scores =
                        new ArrayList<>();

                for (int i = 20; i < 38; i++) {

                    int score =
                            Integer.parseInt(data[i].trim());

                    if (score <= 0) {
                        throw new IllegalArgumentException(
                                "0以下の数値は入力できません。");
                    }

                    player2Scores.add(score);
                }

                // ==========================
                // Playerオブジェクト作成
                // ==========================
                Player player1 =
                        new Player(player1Name,
                                player1Scores);

                Player player2 =
                        new Player(player2Name,
                                player2Scores);

                // ==========================
                // スコア計算
                // ==========================
                int score1 =
                	    calculator.calculateScore(player1.getScores());

                	int score2 =
                	    calculator.calculateScore(player2.getScores());

                // ==========================
                // 結果表示
                // ==========================
                System.out.println();
                System.out.println("結果");

                System.out.println(
                        player1.getName()
                        + " : "
                        + score1);

                System.out.println(
                        player2.getName()
                        + " : "
                        + score2);

                // ==========================
                // 勝敗判定
                // ゴルフは値が小さい方が勝ち
                // ==========================
                if (score1 < score2) {

                    System.out.println(
                            player1.getName()
                            + " の勝利です");

                } else if (score2 < score1) {

                    System.out.println(
                            player2.getName()
                            + " の勝利です");

                } else {

                    System.out.println("引き分けです");
                }

                break;

            } catch (NumberFormatException e) {

                System.out.println(
                        "数値以外が入力されています。");

            } catch (IllegalArgumentException e) {

                System.out.println(
                        e.getMessage());
            }
        }

        scanner.close();
    }
}