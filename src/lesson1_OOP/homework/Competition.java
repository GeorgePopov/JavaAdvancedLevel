package lesson1_OOP.homework;

import lesson1_OOP.homework.competitors.Cat;
import lesson1_OOP.homework.competitors.Competitor;
import lesson1_OOP.homework.competitors.Person;
import lesson1_OOP.homework.competitors.Robot;
import lesson1_OOP.homework.obstacles.Obstacle;
import lesson1_OOP.homework.obstacles.Track;
import lesson1_OOP.homework.obstacles.Wall;

import java.util.ArrayList;

public class Competition {
    private final String titleCompetition;

    private ArrayList<Competitor> winners = new ArrayList<>();

    private Obstacle[] obstacles = {new Track(getTrack()), new Wall(getWall())};
    private Competitor[] competitors = {
            new Person("Rob", getTrack(), getWall()),
            new Cat("Sam", getTrack(), getWall()),
            new Robot("R2-D2", getTrack(), getWall()),
            new Person("George", getTrack(), getWall()),
            new Cat("Ursula", getTrack(), getWall()),
            new Robot("C3-PO", getTrack(), getWall())
    };

    public Competition(String titleCompetition) {
        this.titleCompetition = titleCompetition;
        System.out.println("Competition " + titleCompetition + "\n");
    }

    private int getTrack() {
        return (int) (Math.random() * 50) + 1;
    }

    private double getWall() {
        return (Math.random() * 4) + 1;
    }

    protected void competitionInfo() {
        System.out.println("Information about competition!" + "\n");
        System.out.println("Obstacles :");
        for (Obstacle o : obstacles) {
            o.obstacleInfo();
        }
        System.out.println();

        System.out.println("Competitors :");
        for (Competitor c : competitors) {
            c.competitorInfo();
        }
        System.out.println();
    }

    protected void startCompetition() {
        System.out.println("Start!");
        winners.clear();

        for (Competitor competitor : competitors) {
            System.out.println();
            for (Obstacle obstacle : obstacles) {           // второй forech можно вывести в отдельный метод, а
                if (obstacle.passObstacle(competitor)) {    // разультат записать в boolean переменную и проверку if()
                    winners.add(competitor);
                }
                else {
                    System.out.printf("%s dropped out \n", competitor);
                    break;
                }
            }
        }
        System.out.println();
    }

    protected void winnersInfo() {
        if (winners.isEmpty()) {
            System.out.println("Nobody passed the competition!");
        }
        else {
            System.out.println("Winners :" + System.lineSeparator() + winners);
        }
    }
}