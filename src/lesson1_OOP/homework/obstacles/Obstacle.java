package lesson1_OOP.homework.obstacles;

import lesson1_OOP.homework.competitors.Competitor;

public interface Obstacle {

    boolean passObstacle(Competitor competitor);
    void obstacleInfo();
}
