package lesson1_OOP.trainingManual.interfaceExample;

public class ClientOne implements Callback {

    @Override
    public void callback(int param) {
        System.out.println("ClientOne " + param);
    }
}