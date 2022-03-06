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
        BookInfo bookInfo = new BookInfo("코스모스", "9788983711892", 4.5f, "전 세계 60개국에 방송되어 6억 시청자를 감동시킨 텔레비전 교양 프로그램을 책으로 옮긴 칼 세이건(Carl Sagan)의 『코스모스(Cosmos)』. 현대 천문학을 대표하는 저명한 과학자인 칼 세이건은 이 책에서 사람들의 상상력을 사로잡고, 난해한 개념을 명쾌하게 해설하는 놀라운 능력을 마음껏 발휘한다. 그는 에라토스테네스, 데모크리토스, 히파티아, 케플러, 갈릴레오, 뉴턴, 다윈 같은 과학의 탐험가들이 개척해 놓은 길을 따라가며 과거, 현재, 미래의 과학이 이뤘고, 이루고 있으며, 앞으로 이룰 성과들을 알기 쉽게 풀이해 들려준다.\n 이 책은 모두 13개장으로 구성되어 있다. 칼 세이건은 이 책에서 10조 개의 별들을 품고 있는 은하가 10조 개 있는 광막한 대우주의 세계에서 은하수 은하의 변방, 자그마한 노란색 별 태양이 이끄는 태양계의 한구석에서 창백하게 빛나는 지구에 이르기까지 코스모스에 대해 우리 인류가 알게 된 것들, 알게 된 과정들, 그리고 알아 갈 것들을 소개하고 그것이 궁극적으로 우리 자신을 알기 위한 것임을 설득력 있게 보여 준다. 코스모스 특별판은 수록 이미지가 흑백으로 실려 있다.");

        APIResultProtocol apiResultProtocol = new APIResultProtocol();
        apiResultProtocol.setCode(0);
        apiResultProtocol.setVer(1);
        apiResultProtocol.setData(bookInfo);
        return apiResultProtocol;

    }

}
