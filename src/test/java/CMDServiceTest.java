
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.revature.App;
import org.revature.exception.CMDException;
import org.revature.service.CMDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(properties = "spring.main.lazy-initialization=true",  classes = {App.class})
public class CMDServiceTest {
    @Autowired
    CMDService cmdService;
    @Test
    public void testReturnedOutput() throws CMDException {
        String[] cmd = new String[] {"git", "--help"};
        String result = cmdService.runCommandReturnOutput(cmd);
        Assertions.assertTrue(result.contains("These are common Git commands used in various situations:"));
    }
}
