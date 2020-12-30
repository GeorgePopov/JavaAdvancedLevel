package lesson1_OOP.teacherExample;

import lesson1_OOP.teacherExample.obstacle.Track;
import lesson1_OOP.teacherExample.obstacle.Wall;
import lesson1_OOP.teacherExample.participant.Cat;
import lesson1_OOP.teacherExample.participant.Participant;
import lesson1_OOP.teacherExample.participant.Person;
import lesson1_OOP.teacherExample.participant.Robot;

import java.util.Random;


public class Homework1 {
    public static void main(String[] args) {
        System.out.println("Preparatory stage...");
        Competition competition = createAndPrepareCompetition();

        System.out.println("Start competition!");
        competition.startCompetition();

        System.out.println("Competition finish! Winners:");
        for (Participant winner : competition.getLastWinners()) {
            System.out.println(winner);
        }
    }

    // !*!*! Можно сделать метод созданного типа данных
    // Потом создать переменную этого типа и записать в неё этод метод
    // И самое интересное мы возвращаем созданное соревнование, с массивами и названием, оч круто!
    private static Competition createAndPrepareCompetition() {
        Participant person = new Person("Oleg", 50, 500);
        Participant cat = new Cat("Barsik", 200, 1500);
        Participant robot = new Robot("Altron", 150, 5000);

        Random random = new Random();

        Wall wall = new Wall(random.nextInt(200)); // от 0 до 199
        Track track = new Track(random.nextInt(1500));

        Competition competition = new Competition("Infinity War");
        competition.setParticipants(person, cat, robot);
        competition.setObstacles(wall, track);
        return competition;
    }
}