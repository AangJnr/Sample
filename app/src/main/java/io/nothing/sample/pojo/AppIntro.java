package io.nothing.sample.pojo;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class AppIntro {

    String headerText;
    String subText;
    int image;


    public AppIntro(String headerText, String subText, int image) {

        this.headerText = headerText;
        this.subText = subText;
        this.image = image;
    }


    public int getImage() {
        return image;
    }

    public String getHeaderText() {
        return headerText;
    }

    public String getSubText() {
        return subText;
    }
}

