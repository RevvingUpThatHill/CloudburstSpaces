import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.revature.App;
import org.revature.entity.Canonical;
import org.revature.exception.LabException;
import org.revature.exception.LabRetrievalException;
import org.revature.exception.LabZipException;
import org.revature.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {App.class})
public class LabCanonicalTest {
    @Autowired
    LabService labCanonicalService;

    @Test
    public void testLabExistencePositive(){
        Assertions.assertEquals("63eee7730d225ffc235e737410a507252faafb90",
                labCanonicalService.getLatestCanonicalCommit("teds-workspace")[0]);
    }
    @Test
    public void testLabExistenceNegative(){
        Assertions.assertNull(labCanonicalService.getLatestCanonicalCommit("abc-123-xyz"));
    }
    @Test
    public void testUpdatePollTrue() throws LabZipException, LabRetrievalException, IOException, InterruptedException {
        Canonical labCanonical = labCanonicalService.getCanonicalLab("teds-workspace");
        Assertions.assertNotNull(labCanonical);
        Assertions.assertEquals("teds-workspace", labCanonical.getName());
        Assertions.assertEquals("63eee7730d225ffc235e737410a507252faafb90", labCanonical.getCommitHash());
    }
    /**
     * a labretrievalexception ought to be thrown if the lab does not exist
     * @throws LabZipException
     * @throws LabRetrievalException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testUpdatePollFalse() throws LabZipException, LabRetrievalException, IOException, InterruptedException {
        try{
            Canonical labCanonical = labCanonicalService.getCanonicalLab("abc-123-xyz");
            Assertions.fail();
        }catch(LabRetrievalException e){

        }
    }
    /**
     * APPARENTLY ZIP FILE LENGTH IS SEMI RANDOM LOL
     * @throws LabZipException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void zipSizeTest() throws LabException {
        File zip = labCanonicalService.getZipFromWeb("teds-workspace", "https://github.com/tedbeast/");
        Assertions.assertTrue(Math.abs(zip.length()-16460)<100);
        zip.delete();
    }
}
