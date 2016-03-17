package lambda;
 
public class Student {
    private final String name;
    private final int score;
    private final int graduationYear;
 
    public Student(String name, int score, int graduationYear) {
        this.name = name;
        this.score = score;
        this.graduationYear = graduationYear;
    }
 
    public String getName() {
        return name;
    }
 
    public int getScore() {
        return score;
    }
 
    public int getGraduationYear() {
        return graduationYear;
    }
}