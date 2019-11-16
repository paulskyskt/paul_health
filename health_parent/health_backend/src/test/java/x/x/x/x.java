package x.x.x;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class x {
    public static void main(String[] args) {
        BCryptPasswordEncoder bb = new BCryptPasswordEncoder();
        String pwd = bb.encode("123");
        System.out.println(pwd);

    }
}
