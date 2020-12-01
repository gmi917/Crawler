package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"v"
})
public class Pm25 {

@JsonProperty("v")
private Integer v;

@JsonProperty("v")
public Integer getV() {
return v;
}

@JsonProperty("v")
public void setV(Integer v) {
this.v = v;
}

}