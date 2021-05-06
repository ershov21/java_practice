import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserData {
    public Date created_at;
    public String first_name;
    public String message;
    public String second_name;
    public Integer user_id;

    public UserData(@JsonProperty("created_at") Date created_at,
                @JsonProperty("first_name") String first_name,
                    @JsonProperty("message") String message,
                    @JsonProperty("second_name") String second_name,
                    @JsonProperty("user_id") Integer user_id
    ) {
        this.created_at = created_at;
        this.first_name = first_name;
        this.message = message;
        this.second_name = second_name;
        this.user_id = user_id;
    }

    public Date getCreated() {
        return this.created_at;
    }
}
