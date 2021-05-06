import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Info {
    public String error;
    public ArrayList<UserData> logs;

    public Info(@JsonProperty("error") String error,
                @JsonProperty("logs") ArrayList<UserData> logs
            ) {
        this.error = error;
        this.logs = logs;
    }
}
