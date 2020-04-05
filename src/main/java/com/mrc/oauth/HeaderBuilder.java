package com.mrc.oauth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HeaderBuilder {

    public static HeaderBuilder INSTANCE = new HeaderBuilder();
    private Map<String, String> headers;


    private HeaderBuilder(){
        headers = new HashMap<String, String>();
    }

    public HeaderBuilder headerOAuth(String endPoint, String hubId, String project) {
        final String xTimestamp = getXTimestamp();

        headers.put("endPoint", endPoint);
        headers.put("client", hubId);
        headers.put("secret", hubId);
        headers.put("project", project);
        headers.put("xTimestamp", xTimestamp);

        return this;
    }

    public HeaderBuilder headerToken(String token) {
        headers.put("Authorization", token);
        return this;
    }

    public String getXTimestamp() {
        final Date date = Calendar.getInstance().getTime();
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(date);
    }

    public Map<String, String> build(){
        return headers;
    }

}
