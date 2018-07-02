package test1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
 * Created by Array on 2018/6/26.
 */

public class httpreq{

    private static String id;
    private static int price;
    private static Item item;


    public static void setId(String id) {
        httpreq.id = id;
    }

    public static void setPrice(int price) {
        httpreq.price = price;
    }

    public static void main(String[] args) throws UnirestException {

//        HttpResponse<String> response = Unirest.post("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items")
//                .header("Content-Type", "application/json")
//                .body("[{\"id\":\"6921819716088\",\"price\":7}]")
//                .asString();
//
//        id = "6921819716088";
//        price = 6;
//        HttpResponse<String> response = Unirest.post("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items")
//                .header("Content-Type", "application/json")
//                .body("[{\"id\":\"" + id + "\",\"price\":" + price + "}]")
//                .asString();
//        System.out.println("[{\"id\":\"" + id + "\",\" price \":" + price + "}]");
//
//          httpreq req = new httpreq();
//          System.out.print(req.doGet());
    }

    public void doPost(Item item)throws UnirestException{

//        item = new Item();

        System.out.print(item.toJson(item));
        HttpResponse<String> response = Unirest.post("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items")
                .header("Content-Type", "application/json")
//                .body("[{\"id\":\"" + id + "\",\"price\":" + price + "}]")
                .body("["+ item.toJson(item) + "]")
                .asString();

        System.out.println(response.getStatus());
        System.out.println(response.getStatusText());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        System.out.println(response.getRawBody());
    }

    public String doGet(Item item) throws UnirestException{
//        HttpResponse<String> response = Unirest.get("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items"
//                + "/" + item.getId())
        HttpResponse<String> response = Unirest.get("http://192.168.1.120:8888/api/pricing/v1/domains/szsm.123/items"
                + "/" + item.getId())
                .header("Content-Type", "application/json")
                .asString();
        String stuff = response.getBody();
        return stuff;
    }
}
