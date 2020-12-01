package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"sync"
})
public class Debug {

@JsonProperty("sync")
private String sync;

@JsonProperty("sync")
public String getSync() {
return sync;
}

@JsonProperty("sync")
public void setSync(String sync) {
this.sync = sync;
}

}
