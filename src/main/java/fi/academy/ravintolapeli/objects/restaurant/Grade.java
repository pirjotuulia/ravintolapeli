package fi.academy.ravintolapeli.objects.restaurant;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class Grade {//ravintolaolion sisäinen olio
    private LocalDate date;
    private String grade;
    private int score;

    public Grade() {
    }

    @Override
    public String toString() {
        return "{" +
                "date: '" + date +
                "', grade: '" + grade + '\'' +
                "', score: '" + score +
                '}';
    }

    public Grade(LocalDate date, String grade, int score) {
        this.date = date;
        this.grade = grade;
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
