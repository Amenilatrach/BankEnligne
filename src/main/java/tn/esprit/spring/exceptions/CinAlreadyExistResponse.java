package tn.esprit.spring.exceptions;

public class CinAlreadyExistResponse {

    private String cin ;

    public CinAlreadyExistResponse(String cin) {
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
