package kg.easy.sellservice.services;

public interface EmailService {

    void sendMessage(String to, String subject, String text);

}
