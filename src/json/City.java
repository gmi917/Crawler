package json;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"geo",
"name",
"url"
})
public class City {

@JsonProperty("geo")
private List<Double> geo = null;
@JsonProperty("name")
private String name;
@JsonProperty("url")
private String url;

@JsonProperty("geo")
public List<Double> getGeo() {
return geo;
}

@JsonProperty("geo")
public void setGeo(List<Double> geo) {
this.geo = geo;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

}
