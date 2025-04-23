package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ExchangeRateApiService {

  private static final String API_URL = "https://v6.exchangerate-api.com/v6/2bd6e38afddadc9fe9cf6856/latest/USD";
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
