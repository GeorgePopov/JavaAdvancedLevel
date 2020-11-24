package lesson2_Exceptions.teacherExample;

public class MyArrayDataException extends RuntimeException {

    /**
     * В сообщенияъ такого типа необходимо дать краткую, но
     * детальную информацию: что произошло и как это исправить
     */
    public MyArrayDataException(String cellValue, int rowIndex, int collIndex) {
        super(String.format("Invalid value '%s' in array cell[%d][%d], required integer",
                cellValue, rowIndex, collIndex));
    }
}
