package com.example.pam.expendablelistview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ardy on 8/24/2016.
 */
public class Group {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }
}
