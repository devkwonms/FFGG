package com.project.soccer.service;

import com.project.soccer.common.properties.GullitKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
public class UrlConnService {

    // api urlConn method
    public String urlConn(String api) throws IOException {
        StringBuffer result = new StringBuffer();
        System.setProperty("https.protocols", "TLSv1.2");

        HttpsURLConnection urlConnection = null;
        try {
            URL url = new URL(api);
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", GullitKey.API_KEY);
            // connect()를 호출하여 서버로 요청을 보냄
            urlConnection.connect();

            // 서버 응답 코드가 정상적인 경우에만 데이터를 가져옴
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = urlConnection.getInputStream();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                }
            } else {
                // 서버 응답 코드가 정상적이지 않은 경우, 예외 처리
                throw new IOException("서버 응답 코드가 " + responseCode + "입니다.");
            }
        } catch (MalformedURLException e) {
//            log.error("잘못된 URL 입니다. url={}", api);
            throw e;
        }
        catch (IOException e) {
            // URL 연결 중 예외 발생
//            log.error("URL 연결 중 IOException 발생: {} {}", e.getMessage(), urlConnection);
            throw e;
        }
        catch (Exception e) {
            // 그 외 예외 발생
            log.error("예외 발생: {}", e.getMessage());
            throw e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result.toString();
    }

}
