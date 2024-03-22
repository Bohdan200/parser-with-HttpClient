package web.methods;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.net.URISyntaxException;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class DataMethods {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String get(String url) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static Type getType(Type className) {
        return TypeToken
                .getParameterized(List.class, className)
                .getType();
    }
}
