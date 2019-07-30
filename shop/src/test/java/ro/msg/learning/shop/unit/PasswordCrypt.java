package ro.msg.learning.shop.unit;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCrypt {
    @Test
    public void customEncoder() {
        BCryptPasswordEncoder customPasswordEncoder = new BCryptPasswordEncoder();
        String encoded = customPasswordEncoder.encode("test12");
        System.out.println("Custom encoded " + encoded);
    }
}