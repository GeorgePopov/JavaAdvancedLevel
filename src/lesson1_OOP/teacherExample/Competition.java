package lesson1_OOP.teacherExample;

import lesson1_OOP.teacherExample.obstacle.Obstacle;
import lesson1_OOP.teacherExample.participant.Participant;

import java.util.LinkedList;
import java.util.List;

public class Competition {

    private Obstacle[] obstacles;
    private Participant[] participants;
    private final String competitionTitle;

    private List<Participant> lastWinner = new LinkedList<>();


    public Competition(String title) {
        this.competitionTitle = title;
    }

    public void setObstacles(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void setParticipants(Participant... participants) {
        this.participants = participants;
    }

    public void startCompetition() {
        System.out.println("Start competition " + competitionTitle);
        lastWinner.clear();

        for (Participant participant : participants) {
            boolean success = passAllObstacles(participant);
            if (!success) {
                System.out.println("Participant " + participant + " left the competition");
            }
            else {
                lastWinner.add(participant);
            }
        }
    }

    // !*!*! Метод типа List
    public List<Participant> getLastWinners() {
        return lastWinner;
    }

    private boolean passAllObstacles(Participant participant) {
        for (Obstacle obstacle : obstacles) {
            if (!obstacle.passObstacleBy(participant)) {
                return false;
            }
            System.out.println();
        }
        return true;
    }
}