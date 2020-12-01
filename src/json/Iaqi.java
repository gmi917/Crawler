package json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"co",
"d",
"h",
"no2",
"o3",
"p",
"pm10",
"pm25",
"so2",
"w",
"wd",
"t"
})
public class Iaqi {

@JsonProperty("co")
private Co co;
@JsonProperty("d")
private D d;
@JsonProperty("h")
private H h;
@JsonProperty("no2")
private No2 no2;
@JsonProperty("o3")
private O3 o3;
@JsonProperty("p")
private P p;
@JsonProperty("pm10")
private Pm10 pm10;
@JsonProperty("pm25")
private Pm25 pm25;
@JsonProperty("so2")
private So2 so2;
@JsonProperty("w")
private W w;
@JsonProperty("wd")
private Wd wd;
@JsonProperty("t")
private T t;

@JsonProperty("co")
public Co getCo() {
return co;
}

@JsonProperty("co")
public void setCo(Co co) {
this.co = co;
}
@JsonProperty("d")
public D getD() {
return d;
}

@JsonProperty("d")
public void setD(D d) {
this.d = d;
}
@JsonProperty("h")
public H getH() {
return h;
}

@JsonProperty("h")
public void setH(H h) {
this.h = h;
}

@JsonProperty("no2")
public No2 getNo2() {
return no2;
}

@JsonProperty("no2")
public void setNo2(No2 no2) {
this.no2 = no2;
}

@JsonProperty("o3")
public O3 getO3() {
return o3;
}

@JsonProperty("o3")
public void setO3(O3 o3) {
this.o3 = o3;
}

@JsonProperty("p")
public P getP() {
return p;
}

@JsonProperty("p")
public void setP(P p) {
this.p = p;
}

@JsonProperty("pm10")
public Pm10 getPm10() {
return pm10;
}

@JsonProperty("pm10")
public void setPm10(Pm10 pm10) {
this.pm10 = pm10;
}

@JsonProperty("pm25")
public Pm25 getPm25() {
return pm25;
}

@JsonProperty("pm25")
public void setPm25(Pm25 pm25) {
this.pm25 = pm25;
}

@JsonProperty("so2")
public So2 getSo2() {
return so2;
}

@JsonProperty("so2")
public void setSo2(So2 so2) {
this.so2 = so2;
}
@JsonProperty("w")
public W getW() {
return w;
}

@JsonProperty("w")
public void setW(W w) {
this.w = w;
}
@JsonProperty("wd")
public Wd getWd() {
return wd;
}

@JsonProperty("wd")
public void setWd(Wd wd) {
this.wd = wd;
}
@JsonProperty("t")
public T getT() {
return t;
}

@JsonProperty("t")
public void setT(T t) {
this.t = t;
}

}
