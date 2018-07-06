package test1;

/**
 * Created by Array on 2018/7/6.
 */
public class Media {
    private String mediaID;
    private String status;
    private String mediaTypeID;

    public Media() {
    }

    public void setMediaID(String mediaID){
        this.mediaID = mediaID;
    }

    public String getMediaID(){
        return mediaID;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setMediaTypeID(String mediaTypeID){
        this.mediaTypeID = mediaTypeID;
    }

    public String getMediaTypeID(){
        return mediaTypeID;
    }

}
