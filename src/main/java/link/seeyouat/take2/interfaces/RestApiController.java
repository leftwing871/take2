package link.seeyouat.take2.interfaces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


@RestController
public class RestApiController {

    @Value("${test.projectName}")
    String projectName;

    @Value("${test.version}")
    int version;

    @Value("${test.projectNameAndVersion}")
    String projectNameAndVersion;

    private static final Logger _log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/resthome")
    public String resthome()
    {
        _log.trace("Trace Log");
        _log.debug("Debug Log");
        _log.info("Info Log");
        _log.warn("Warn Log");
        _log.error("Error Log");


        _log.info("projectName: " + projectName);
        _log.info("version: " + String.valueOf(version));
        _log.info("projectNameAndVersion: " + projectNameAndVersion);

        return "The server is running well.";
    }

    @GetMapping("/random")
    public String random()
    {
        Integer sleep_sec = Math.abs(new Random().nextInt() % 10 + 1);

        for (int i = 0; i < 10; i++) {
            sleep_sec = Math.abs(new Random().nextInt() % 10 + 1);
            _log.info("random: " + String.valueOf(sleep_sec));
        }

        return String.valueOf(sleep_sec);
    }

}
