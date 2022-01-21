package com.geekbrains.task_from_methodics;

public class Main {
    public static void runObstacleCourse(Obstacle[] obstacles, Movable[] movables) {
        for (int i = 0; i < movables.length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                if (obstacles[j] instanceof Track) {
                    if (!movables[i].run((Track) obstacles[j])) break;
                } else {
                    if (!movables[i].jump((Wall) obstacles[j])) break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Wall wall1 = new Wall(10);
        Wall wall2 = new Wall(11);
        Track track1 = new Track(3);
        Track track2 = new Track(5);
        Obstacle[] obstacles = {wall1, wall2, track1, track2};

        Human human1 = new Human(10, 5);
        Human human2 = new Human(3, 10);
        Human human3 = new Human(20, 20);
        Robot robot1 = new Robot(50, 50);
        Cat cat1 = new Cat(12, 2);
        Movable[] movables = {human1, human2, human3, robot1, cat1};
        runObstacleCourse(obstacles, movables);
    }
}
