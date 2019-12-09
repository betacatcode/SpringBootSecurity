package com.ruin.springbootsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ruin
 * @date 2019/12/8-21:21
 */

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void testBCrypt(){
//        对密码进行加密
        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
        System.out.println(hashpw);

//        校验密码
        boolean b1=BCrypt.checkpw("123","$2a$10$0FKVIkqkcCiEcHKs4.oQUO4aYLliaQm5AtnP2fwQSxX6JhhAo14vS");
        boolean b2=BCrypt.checkpw("123","$2a$10$hq2FvCw4ISVxzLzTaIvMSuDe3DDfOce7/u5JdOsbUAlLQt8iZLYpK");

        System.out.println(b1);
        System.out.println(b2);
    }
}
