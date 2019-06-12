/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class App {


    public static void main(String[] args) throws IOException {

        try {
            System.out.println(getQuotesFromAPI());
        } catch (IOException err) {
            Path path = FileSystems.getDefault().getPath("assets", "recentquotes.json");

            String jsonStrings = getQuotesData(path);
            Quote[] myQuotes = quotify(jsonStrings);

            System.out.println(myQuotes[((int)(Math.random() * myQuotes.length + 1))]);
        }
    }

    public static Quote[] quotify(String quoteJSONString){

        Gson gson = new GsonBuilder().serializeNulls().create();
        Quote[] quotesFromJson = gson.fromJson(quoteJSONString, Quote[].class );


        return quotesFromJson;
    }


    public static String getQuotesData(Path path) throws IOException {

        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String output = "";
        StringBuilder stringBuilder = new StringBuilder();

        while((output = reader.readLine()) != null){
            stringBuilder.append(output);
        }

        return stringBuilder.toString();
    }

    public static StarWarsQuote getQuotesFromAPI() throws IOException {
        URL urlForAPI = new URL("http://swquotesapi.digitaljedi.dk/api/SWQuote/RandomStarWarsQuote");
        HttpURLConnection connection = (HttpURLConnection) urlForAPI.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        StringBuilder output = new StringBuilder();
        String string = "";

        while ((string = reader.readLine()) != null) {
            output.append(string);
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        StarWarsQuote starWarsQuote = gson.fromJson(output.toString(), StarWarsQuote.class);

        return starWarsQuote;
    }

    public static void cacheQuote(String quoteToAdd, Path path) throws IOException {
        //TODO: append quotes to json file
    }
}
