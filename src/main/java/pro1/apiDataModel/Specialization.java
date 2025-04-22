package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Specialization {
    @SerializedName("programNazev")
    public String programNazev;

    @SerializedName("oborNazev")
    public String oborNazev;

    @SerializedName("eprDeadlinePrihlaska")
    public Deadline eprDeadlinePrihlaska;
}
