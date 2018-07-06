package test1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
 * Created by Array on 2018/6/26.
 */

public class httpreq{

    private static Item item;
    private int statusCode;

    public int getStatusCode(){
        return statusCode;
    }

    public static void main(String[] args) throws UnirestException {

    }

    public void doPost(Item item)throws UnirestException{
        System.out.print(item.toJson(item));
        HttpResponse<String> response = Unirest.post("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items")
                .header("Content-Type", "application/json")
                .body("["+ item.toJson(item) + "]")
                .asString();

        statusCode = response.getStatus();
        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        System.out.println(response.getRawBody());
    }

    public void bind(Item item, String eslCode) throws UnirestException {//实际上应该是一个get方法
        String b = "{\"values\":[{\"mediaId\":\""+eslCode+"\",\"matchingMediaDisplayId\":\"{\\\"itemCount\\\":1,\\\"matchingItemCount\\\":1," +
                "\\\"animatedDisplaysType\\\":\\\"STANDARD\\\"}\",\"items\":[{\"position\":1,\"itemId\":\""+item.getId()+"\",\"extended\":{}}]}]}";
        System.out.println(b);
        HttpResponse<String> response = Unirest.post("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/medias/matchings")
                .header("Content-Type", "application/json")
                .body(b)
                .asString();

        statusCode = response.getStatus();
        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        System.out.println(response.getRawBody());
    }

    public String doGet(Item item) throws UnirestException{
        HttpResponse<String> response = Unirest.get("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items"
                + "/" + item.getId())
                .header("Content-Type", "application/json")
                .asString();
        statusCode = response.getStatus();
        String stuff = response.getBody();
        return stuff;
    }

    public String getMedia(String mediaID) throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/medias"
                + "/" + mediaID)
                .header("Content-Type", "application/json")
                .asString();
        statusCode = response.getStatus();
        String stuff = response.getBody();
        return stuff;
    }

    public void unbind(String eslCode) throws UnirestException {//解绑
        String ub = "{\"values\":[{\"mediaId\":\"" + eslCode + "\"}]}";
        System.out.println(ub);
        HttpResponse<String> response = Unirest.post("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/medias/matchings")
                .header("Content-Type", "application/json")
                .body(ub)
                .asString();

        statusCode = response.getStatus();
        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        System.out.println(response.getRawBody());
    }
}
