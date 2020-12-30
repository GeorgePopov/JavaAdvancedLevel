package lesson1_OOP.homework;

public class MainCompetition {
    public static void main(String[] args) {
        Competition competition = new Competition("Olympic 2020");

        competition.competitionInfo();
        competition.startCompetition();
        competition.winnersInfo();
    }
    /**
     * Можно было соревнования разделить на два класса, 1-й с методами 'start',
     * 'winner'... а 2-й подготовка к соревнованиям, где заполянются массивы и т.д.
     * Создать еще пакет соревнования с двумя классами 'соревнования и подготовка к соревнованиям'
     */
}
