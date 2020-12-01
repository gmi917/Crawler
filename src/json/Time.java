package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"s",
"tz",
"v"
})
public class Time {

@JsonProperty("s")
private String s;
@JsonProperty("tz")
private String tz;
@JsonProperty("v")
private Integer v;

@JsonProperty("s")
public String getS() {
return s;
}

@JsonProperty("s")
public void setS(String s) {
this.s = s;
}

@JsonProperty("tz")
public String getTz() {
return tz;
}

@JsonProperty("tz")
public void setTz(String tz) {
this.tz = tz;
}

@JsonProperty("v")
public Integer getV() {
return v;
}

@JsonProperty("v")
public void setV(Integer v) {
this.v = v;
}

}
