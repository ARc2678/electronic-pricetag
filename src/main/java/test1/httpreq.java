package test1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
 * Created by Array on 2018/6/26.
 */

public class httpreq{

    private int statusCode;

    public int getStatusCode(){
        return statusCode;
    }

    public static void main(String[] args) throws UnirestException {

    }

    public void doPost(Item item)throws UnirestException{
        System.out.print(item.toJson(item));
        HttpResponse<String> response = Unirest.post("http://192.168.1.233:2333/abc/defg/hijk/lmn/opqrstu/vwxyz")
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

    public void bind(Item item, String eslCode) throws UnirestException {
        String b = "{\"values\":[{\"mediaId\":\""+eslCode+"**********"+"itemId\":\""+item.getId()+"\",\"extended\":{}}]}]}";
        System.out.println(b);
        HttpResponse<String> response = Unirest.post("http://192.168.1.123:1234/api/this/is/not/absolute/")
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
        HttpResponse<String> response = Unirest.get("http://192.168.1.123:1234/you/do/know/what/it/is"
                + "/" + item.getId())
                .header("Content-Type", "application/json")
                .asString();
        statusCode = response.getStatus();
        String stuff = response.getBody();
        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        System.out.println(response.getRawBody());
        return stuff;
    }

    public void unbind(String eslCode) throws UnirestException {
        String ub = "{\"values\":[{\"mediaId\":\"" + eslCode + "\"}]}";
        System.out.println(ub);
        HttpResponse<String> response = Unirest.post("http://192.168.1.000:2678/guesswhatitis")
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
