package link.seeyouat.take2.interfaces;

import link.seeyouat.take2.entity.BookInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookRatingApiController {

    private static final Logger _log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/rating/{ISBN13}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookInfo rating(@PathVariable("ISBN13") String ISBN13)
    {
        _log.info(ISBN13);
        BookInfo bookInfo = new BookInfo("코스모스", "9788983711892", 4.5f);

        return bookInfo;
    }



}
