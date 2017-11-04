package io.nothing.sample;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by aangjnr on 16/10/2017.
 */

public class Question {

    String type;
    String tag;
    String label;
    List<String> values;
    Object answer;


    Question(String type, String tag, String label, @Nullable List<String> values) {
        this.label = label;
        this.tag = tag;
        this.type = type;
        this.values = values;
    }


    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
