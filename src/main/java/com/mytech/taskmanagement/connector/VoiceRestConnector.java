package com.mytech.taskmanagement.connector;


import com.mytech.taskmanagement.dao.entity.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoiceRestConnector {
    private final RestTemplate restTemplate;
    @Value("${com.tech.voice.kaleyra.url}")
    private String kaleyraApiEndpoint;
    private final OkHttpClient okHttpClient;
    @Value("${com.tech.voice.kaleyra.apikey}")
    private String apiKey;
    private final static String TO = "+919999196461";
    private final static String BRIDGE = "+918046983237";
    private final static String SSID = "HXIN1709604395IN";


    /**
     * This to send a voice notification
     *
     * @param taskRequestDto
     * @throws IOException
     */
    public void postVoiceRemainderWithOkhttp(Task taskRequestDto) throws IOException {
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        //Create the endpoint
        String endPointNew = String.format(
                kaleyraApiEndpoint, SSID, TO, BRIDGE,apiKey,taskRequestDto.getDescription());

        Request request = new Request.Builder()
                .url(endPointNew)
                .method(HttpMethod.POST.toString(), body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body() == null ? null : response.body().string();
        log.info("responseBody" + responseBody);
    }
}
