package main;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import resource.Post;

public class HttpClientTest {

    private String baseUrl = "https://jsonplaceholder.typicode.com/posts/";
    private HttpClient client = HttpClient.newHttpClient();

    public HttpResponse<String> getAll() throws IOException, InterruptedException {
        URI uri = URI.create(baseUrl);
        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .GET().build();
        return client.send(request, BodyHandlers.ofString());
    }

    public HttpResponse<String> get(Integer id) throws IOException, InterruptedException {
        URI uri = URI.create(baseUrl + id);
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        return client.send(request, BodyHandlers.ofString());
    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        HttpClientTest httpClientTest = new HttpClientTest();

        try {
            HttpResponse<String> response = httpClientTest.get(1);
            Post post = gson.fromJson(response.body(), Post.class);
            System.out.println(post.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
