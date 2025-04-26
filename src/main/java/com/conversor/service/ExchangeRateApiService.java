package main.java.com.conversor.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.cdimascio.dotenv.Dotenv;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import main.java.com.conversor.model.ExchangeRateResponse;

public class ExchangeRateApiService {

  private static final String API_URL;
  private static final String CODES_URL;
  private final HttpClient client;
  private final Gson gson;
  
  static {
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("API_KEY");
    API_URL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
    CODES_URL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";
  }
  
  public ExchangeRateApiService() {
    this.client = HttpClient.newHttpClient();
    this.gson = new Gson();
  }

  public ExchangeRateResponse getLatestRates() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(API_URL))
      .GET()
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return gson.fromJson(response.body(), ExchangeRateResponse.class);
  }

  public String[][] getSupportedCodes() throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(CODES_URL))
      .GET()
      .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    String body = response.body();
    Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
    Map<String, Object> jsonMap = gson.fromJson(body, mapType);
    Object supportedCodesObj = jsonMap.get("supported_codes");

    Type listType = new TypeToken<List<List<String>>>(){}.getType();
    List<List<String>> codesList = gson.fromJson(gson.toJson(supportedCodesObj), listType);
    String[][] codes = new String[codesList.size()][2];

    for (int i = 0; i < codesList.size(); i++) {
        codes[i][0] = codesList.get(i).get(0);
        codes[i][1] = codesList.get(i).get(1);
    }

    return codes;
  }
} 