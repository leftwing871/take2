package link.seeyouat.take2.interfaces;

import link.seeyouat.take2.entity.Post;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String hello() {

        return "index";
    }


    @GetMapping("/environment")
    public String hello(Model model, @RequestParam(value = "sleep_sec", required = false, defaultValue = "") String sleep_sec) throws IOException, InterruptedException {

        //시스템 환경변수 값 전체 가져오기 (key, value 형태)
        Map <String, String> mapX = System.getenv();
        for (Map.Entry <String, String> entry: mapX.entrySet()) {
            System.out.println("Variable Name:- " + entry.getKey() + " Value:- " + entry.getValue());
        }

        // 컨테이너 생성
        GenericXmlApplicationContext ctx
                = new GenericXmlApplicationContext();

        // 환경변수 관리 객체 생성
        ConfigurableEnvironment env = ctx.getEnvironment();

        // 프로퍼티 정보 얻기
        String ip = env.getProperty("ip");
        String hostName = env.getProperty("hostname");
        String SLEEP_SEC = env.getProperty("SLEEP_SEC");
        SLEEP_SEC = SLEEP_SEC == null ? "" : SLEEP_SEC;

        if(!SLEEP_SEC.isEmpty() && !SLEEP_SEC.isBlank())
        {
            Thread.sleep(Integer.parseInt(SLEEP_SEC) * 1000);
        }

        if(!sleep_sec.isEmpty() && !sleep_sec.isBlank())
        {
            Thread.sleep(Integer.parseInt(sleep_sec) * 1000);
        }

//        HashMap<String, String> map = new HashMap<>();
//        map.put("ip", ip);
//        map.put("hostname", hostName);
//        map.put("SLEEP_SEC", SLEEP_SEC);

        model.addAttribute("mapENV", mapX);

        return "environment";
    }
}
