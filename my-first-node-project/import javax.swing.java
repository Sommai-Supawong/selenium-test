import javax.swing.JOptionPane;

public class grade {

    public static void main(String[] args) {

        String str;
        double score;
        String grade;

        str = JOptionPane.showInputDialog(
                null,
                "Input Score",
                "Grade Program",
                JOptionPane.QUESTION_MESSAGE);

        score = Double.parseDouble(str);

        if (score >= 80)
            grade = "A";
        else if (score >= 75)
            grade = "B+";
        else if (score >= 70)
            grade = "B";
        else if (score >= 65)
            grade = "C+";
        else if (score >= 60)
            grade = "C";
        else if (score >= 55)
            grade = "D+";
        else if (score >= 50)
            grade = "D";
        else
            grade = "E";

        JOptionPane.showMessageDialog(
                null,
                "Your Score = " + score + "\nGrade = " + grade,
                "Result",
                JOptionPane.INFORMATION_MESSAGE);
    }
}