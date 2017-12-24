package id.fadhil.resepmama.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by riyan on 20/12/2017.
 */

public class Result {
    @SerializedName("result")
    @Expose
    private String result;

    public String getResult(){
        return  result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
