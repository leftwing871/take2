package link.seeyouat.take2.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class RestApiController {

    private static final Logger _log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/resthome")
    public String resthome()
    {
        _log.trace("Trace Log");
        _log.debug("Debug Log");
        _log.info("Info Log");
        _log.warn("Warn Log");
        _log.error("Error Log");

        return "The server is running well.";
    }

}