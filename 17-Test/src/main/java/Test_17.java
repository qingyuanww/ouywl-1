import org.junit.Test;

import java.util.Date;
import java.util.Optional;

/**
 * @description:
 * @author: oywl
 * @time: 2024-05-20 13:39
 */

public class Test_17 {
    @Test
    public void t1() throws Exception {
        Optional<Object> empty = Optional.empty();
        String str = new String("1");
        Optional<String> optional = Optional.ofNullable(str);
        System.out.println(optional.orElse("2"));
        System.out.println(optional.orElseGet(() -> "3"));
        optional.orElseThrow(()->new Exception(""));

    }
    @Test
    public void t2(){

    }
}
