package test1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * Created by Array on 2018/6/28.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private String id;
    private String domain;//unused property
    private String name;
    private String description;
    private double price;

    public Item(){};

    //unused constructor
    public Item(String id, String domain, String name, int price,String description){
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.price = price;
        this.description = description;
    }

    public String toJson(Object obj){
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName, Object fieldValue) {
                return null == fieldValue;
            }
        };
        jsonConfig.setJsonPropertyFilter(filter);
        JSONObject json = JSONObject.fromObject(obj,jsonConfig);
        String str = json.toString();
        return str;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

//    public void setDomain(String domain){
//        this.domain = domain;
//    }
//
//    public String getDomain(){
//        return domain;
//    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return price;
    }
}
