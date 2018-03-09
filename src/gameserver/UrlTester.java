package hangman;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

public class UrlTester {

    static HashSet<String> words = new HashSet<String>();

    public static void main(String[] args) throws IOException {
        String popURL = "https://www.dr.dk/mu-online/api/1.3/list/view/mostviewed?channel=dr1&channeltype=TV&limit=20&offset=0";

        JsonElement root = getJson(popURL);
        JsonObject rootobj = root.getAsJsonObject(); //Array
        JsonArray arr = rootobj.getAsJsonArray("Items");

        for(int i =1; i<arr.size();i++){
            JsonObject obj = arr.get(i).getAsJsonObject();

            String assetLink = obj.get("PrimaryAsset").getAsJsonObject().get("Uri").getAsString();

            JsonElement asset =getJson(assetLink);


            JsonArray subtitleList= asset.getAsJsonObject().get("SubtitlesList").getAsJsonArray();

            for(int j = 0; j<subtitleList.size();j++){
                JsonObject subtitleData= subtitleList.get(j).getAsJsonObject();
                if(subtitleData.get("Language").toString().equals("\"Danish\"")){

                    String subtitleLink = subtitleData.get("Uri").toString();

                    URL url = new URL(subtitleLink.substring(1,subtitleLink.length()-1));
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.connect();

                    Scanner sc = new Scanner((InputStream) request.getContent());
                    sc.nextLine();
                    sc.nextLine();
                    while(sc.hasNextLine()){
                        String line = sc.nextLine();
                        if(line.length()<1){

                            continue;
                        }
                        if(Character.isDigit(line.charAt(0))){

                            continue;
                        }

                        String[] posWords = line.split(" ");

                        for(String s : posWords){
                            boolean isInvalid=false;
                            s=s.trim();
                            for(int k=0; k<s.length();k++){
                                if(!Character.isLetter(s.charAt(k))){
                                    isInvalid=true;
                                }

                            }

                            if(s.length()<3){
                                continue;
                            }

                            if(!isInvalid){
                                words.add(s.toLowerCase());
                            }
                        }


                    }

                }

            }



        }
        System.out.println(words);
        System.out.println(words.size());
    }

    static JsonElement getJson(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        return root;
    }
}
