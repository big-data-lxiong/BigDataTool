package com.xl;

import org.json.JSONObject;
import org.junit.Test;

public class TestRestHelp {

    @Test
    public void testRestHelpPost(){
        String data = "action=login&username=azadmin&password=azadmin";
        String azkabanUrl = "http://localhost:8081";
        String res = RestHelp.post(azkabanUrl, data);
        JSONObject jsonObject = new JSONObject(res);
        jsonObject.getString("session.id");
    }

}
