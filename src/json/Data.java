package json;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"aqi",
"idx",
"attributions",
"city",
"dominentpol",
"iaqi",
"time",
"debug"
})
public class Data {

@JsonProperty("aqi")
private Integer aqi;
@JsonProperty("idx")
private Integer idx;
@JsonProperty("attributions")
private List<Attribution> attributions = null;
@JsonProperty("city")
private City city;
@JsonProperty("dominentpol")
private String dominentpol;
@JsonProperty("iaqi")
private Iaqi iaqi;
@JsonProperty("time")
private Time time;
@JsonProperty("debug")
private Debug debug;

@JsonProperty("aqi")
public Integer getAqi() {
return aqi;
}

@JsonProperty("aqi")
public void setAqi(Integer aqi) {
this.aqi = aqi;
}

@JsonProperty("idx")
public Integer getIdx() {
return idx;
}

@JsonProperty("idx")
public void setIdx(Integer idx) {
this.idx = idx;
}

@JsonProperty("attributions")
public List<Attribution> getAttributions() {
return attributions;
}

@JsonProperty("attributions")
public void setAttributions(List<Attribution> attributions) {
this.attributions = attributions;
}

@JsonProperty("city")
public City getCity() {
return city;
}

@JsonProperty("city")
public void setCity(City city) {
this.city = city;
}

@JsonProperty("dominentpol")
public String getDominentpol() {
return dominentpol;
}

@JsonProperty("dominentpol")
public void setDominentpol(String dominentpol) {
this.dominentpol = dominentpol;
}

@JsonProperty("iaqi")
public Iaqi getIaqi() {
return iaqi;
}

@JsonProperty("iaqi")
public void setIaqi(Iaqi iaqi) {
this.iaqi = iaqi;
}

@JsonProperty("time")
public Time getTime() {
return time;
}

@JsonProperty("time")
public void setTime(Time time) {
this.time = time;
}

@JsonProperty("debug")
public Debug getDebug() {
return debug;
}

@JsonProperty("debug")
public void setDebug(Debug debug) {
this.debug = debug;
}

}
