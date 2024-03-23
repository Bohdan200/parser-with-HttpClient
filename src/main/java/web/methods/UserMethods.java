package web.methods;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.List;
import web.user.User;

public class UserMethods implements IWorkingWithUser {
    private static final HttpClient client = HttpClient.newHttpClient();

    public String get(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static Type getType() {
        return TypeToken
                .getParameterized(List.class, User.class)
                .getType();
    }

    public String post(String url, String newUser) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(newUser))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String put(String url, String userData) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(userData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String remove(String url) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
