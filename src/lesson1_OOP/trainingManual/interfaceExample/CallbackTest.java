package lesson1_OOP.trainingManual.interfaceExample;

public class CallbackTest {
    public static void main(String[] args) {
        Callback client1 = new ClientOne();
        Callback client2 = new ClientTwo();

        client1.callback(1);
        client2.callback(2);
    }
}