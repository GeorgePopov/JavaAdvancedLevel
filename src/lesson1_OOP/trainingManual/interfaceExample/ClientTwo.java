package lesson1_OOP.trainingManual.interfaceExample;

public class ClientTwo implements Callback {

    @Override
    public void callback(int param) {
        System.out.println("ClientTwo " + param);
    }
}