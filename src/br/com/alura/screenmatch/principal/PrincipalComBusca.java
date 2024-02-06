package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.alura.screenmatch.exceptions.YearSizeException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOMDB;

public class PrincipalComBusca {

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner leitura = new Scanner(System.in);
		System.out.println("Digite um filme para buscar: ");
		var busca = leitura.nextLine();
		String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=XXXXX";

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String json = response.body();

		System.out.println("esse é o body antes do gson: " + response.body());

		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		TituloOMDB meuTituloOMDB = gson.fromJson(json, TituloOMDB.class);
		try {
			Titulo meuTitulo = new Titulo(meuTituloOMDB);
			System.out.println("sucesso:" + meuTitulo);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (YearSizeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("O programa finalizou corretamente!");

	}

}
