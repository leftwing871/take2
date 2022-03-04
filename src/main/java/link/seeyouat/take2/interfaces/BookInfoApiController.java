package link.seeyouat.take2.interfaces;

import link.seeyouat.take2.entity.APIResultProtocol;
import link.seeyouat.take2.entity.BookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class BookInfoApiController {

    private static final Logger _log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/detail/{ISBN13}", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResultProtocol detail(@PathVariable("ISBN13") String ISBN13)
    {
        _log.info(ISBN13);
        BookInfo bookInfo = new BookInfo("코스모스", "9788983711892", 4.5f);

        APIResultProtocol apiResultProtocol = new APIResultProtocol();
        apiResultProtocol.setCode(0);
        apiResultProtocol.setReturnValue(bookInfo);

        return apiResultProtocol;
    }

}
