package lesson7_networkchat;

public interface AuthService {

    void start();
    void stop();

    String getNickByLoginPass(String login, String pass);
}
