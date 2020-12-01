package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"v"
})
public class So2 {

@JsonProperty("v")
private Double v;

@JsonProperty("v")
public Double getV() {
return v;
}

@JsonProperty("v")
public void setV(Double v) {
this.v = v;
}

}