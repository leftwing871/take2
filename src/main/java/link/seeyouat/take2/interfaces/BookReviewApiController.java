package link.seeyouat.take2.interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import link.seeyouat.take2.entity.APIResultProtocol;
import link.seeyouat.take2.entity.BookInfo;
import link.seeyouat.take2.util.EnvironmentVariableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

@RestController
public class BookReviewApiController {

    private static final Logger _log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/review/{ISBN13}", produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResultProtocol review(@PathVariable("ISBN13") String ISBN13) throws IOException {
        _log.info(ISBN13);
        BookInfo bookInfo = new BookInfo("코스모스", "9788983711892", 4.5f, "");

        String CLUSTER_IP_rating = EnvironmentVariableHelper.getInstance().getProperty("CLUSTER_IP_rating");
        if(CLUSTER_IP_rating == null)
            CLUSTER_IP_rating = "127.0.0.1";

        _log.info("CLUSTER_IP_rating: " + CLUSTER_IP_rating);
        String targetUrl = "http://" + CLUSTER_IP_rating + ":8080/rating/9788983711892";
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
        bookInfo.setRating(reqBookInfo.getRating());

        //System.out.println("HTTP body : " + response.toString());

        APIResultProtocol apiResultProtocol = new APIResultProtocol();
        apiResultProtocol.setCode(0);
        apiResultProtocol.setVer(1);
        apiResultProtocol.setData(bookInfo);
        return apiResultProtocol;
    }



}
