package org.revature.service;

import org.revature.App;
import org.revature.exception.CMDException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * service class used to execute command line arguments
 */
@Service
public class CMDService {
    /**
     * run a cmd command and convert the entire output to a string
     * @param cmd
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String runCommandReturnOutput(String[] cmd) throws CMDException {
        try {
            App.log.info("running this command: " + Arrays.toString(cmd));
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = "";
            String outputLine = "";
            do {
                outputLine = reader.readLine();
                if (outputLine != null)
                    result += outputLine + "\n";
            } while (outputLine != null);
            process.waitFor();

            return result;
        }catch (IOException | InterruptedException e){
            throw new CMDException("Couldn't run the command: "+Arrays.toString(cmd), e);
        }
    }
}