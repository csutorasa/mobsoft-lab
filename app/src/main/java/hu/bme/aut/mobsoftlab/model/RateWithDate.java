package hu.bme.aut.mobsoftlab.model;

import java.util.Objects;
import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.SerializedName;


public class RateWithDate   {
  
  @SerializedName("rate")
  private BigDecimal rate = null;
  
  @SerializedName("date")
  private Date date = null;

  public BigDecimal getRate() {
    return rate;
  }
  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RateWithDate rateWithDate = (RateWithDate) o;
    return Objects.equals(rate, rateWithDate.rate) &&
        Objects.equals(date, rateWithDate.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rate, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RateWithDate {\n");
    
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
