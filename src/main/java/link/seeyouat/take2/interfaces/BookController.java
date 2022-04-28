package link.seeyouat.take2.interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import link.seeyouat.take2.entity.APIResultProtocol;
import link.seeyouat.take2.entity.BookInfo;
import link.seeyouat.take2.entity.Post;
import link.seeyouat.take2.util.EnvironmentVariableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private static final Logger _log = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/list")
    public String hello(Model model) {

        Post post1 = new Post(1, "lee", "book1");
        Post post2 = new Post(2, "choi", "book2");
        Post post3 = new Post(3, "kim", "book3");
        List<Post> list = new ArrayList<>();
        list.add(post1);
        list.add(post2);
        list.add(post3);
        model.addAttribute("list", list);
        return "book/list";
    }

    @GetMapping("/productpage")
    public String productpage(Model model) throws IOException {

        String CLUSTER_IP_review = EnvironmentVariableHelper.getInstance().getProperty("CLUSTER_IP_review");
        if(CLUSTER_IP_review == null)
            CLUSTER_IP_review = "127.0.0.1";

        _log.info("CLUSTER_IP_review: " + CLUSTER_IP_review);
        String targetUrl = "http://" + CLUSTER_IP_review + ":8080/review/9788983711892";
        String USER_AGENT = "Mozilla/5.0";

        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET"); // optional default is GET
        con.setRequestProperty("User-Agent", USER_AGENT); // add request header
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println("HTTP 응답 코드 : " + responseCode);

        APIResultProtocol aPIResultProtocol = new GsonBuilder().create().fromJson(response.toString(), APIResultProtocol.class);
        Object bookInfoObj = aPIResultProtocol.getData();

        Gson gson = new Gson();
        String bookInfoString = gson.toJson(bookInfoObj);

        BookInfo reqBookInfo = new GsonBuilder().create().fromJson(bookInfoString, BookInfo.class);
        //bookInfo.setRating(reqBookInfo.getRating());

        model.addAttribute("bookInfo", reqBookInfo);
        model.addAttribute("reviewVersion", aPIResultProtocol.getVer());

        return "book/productpage";
    }
}
