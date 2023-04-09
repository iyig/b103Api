package posjos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {

  private String checking;
    private String checkout;

    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checking,String checkout) {
        this.checking = checking;
        this.checkout = checkout;
    }

    public String getChecking() {
        return checking;
    }

    public void setChecking(String checking) {
        this.checking = checking;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checking='" + checking + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }

    // "checkin": "2021-09-21",
    //  "checkout": "2021-12-21"
}
