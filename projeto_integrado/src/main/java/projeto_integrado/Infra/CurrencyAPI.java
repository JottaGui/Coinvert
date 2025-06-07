package projeto_integrado.Infra;

import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;
import projeto_integrado.Erros.Erros;

@Service
public class CurrencyAPI {


    String origem;
    String destino;
    Integer ano;
    Integer mes;
    Integer dia;

    public String obterCotacao(String origem, String destino) {
        try {
        	 destino = destino.toUpperCase();
             origem = origem.toUpperCase();
             
            String url = "https://economia.awesomeapi.com.br/json/last/" + origem + "-" + destino;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject pegarJson = new JSONObject(response.body());
            return pegarJson.getJSONObject(origem + destino).getString("bid");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String valoremadata(String origem, String destino, Integer ano, Integer mes, Integer dia) {
        try {

            destino = destino.toUpperCase();
            origem = origem.toUpperCase();

            String data = String.format("%d-%02d-%02d", ano, mes, dia);
            String url = "https://api.frankfurter.app/" + data + "?from=" + origem + "&to=" + destino;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Java 11 HttpClient")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Cotação não disponível para a data: " + data + " (HTTP " + response.statusCode() + ")");
                return null; // Ou você pode retornar "0" ou lançar exceção, conforme sua lógica
            }
            JSONObject Json = new JSONObject(response.body());

                double valor = Json.getJSONObject("rates").getDouble(destino);
                double valorformatado = Math.round(valor * 100) / 100.0;

            return String.valueOf(valorformatado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}
