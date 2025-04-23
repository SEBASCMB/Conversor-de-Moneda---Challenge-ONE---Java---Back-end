package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

public class ExchangeRateApiService {

  private static final String API_URL;
  static {
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("API_KEY");
    API_URL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
  }
  private final Gson gson = new Gson();

  public ExchangeRateResponse getLatestRates() throws IOException, InterruptedException {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(API_URL))
      .GET()
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return gson.fromJson(response.body(), ExchangeRateResponse.class);
  }

    
}
