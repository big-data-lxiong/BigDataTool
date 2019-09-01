package com.xl;

import org.json.JSONObject;
import org.junit.Test;

public class TestRestHelp {

    @Test
    public void testRestHelpPost(){
        String data = "action=login&username=azkaban&password=azakaban";
        String azkabanUrl = "http://localhost:8081";
        String res = RestHelp.post(azkabanUrl, data);
        JSONObject jsonObject = new JSONObject(res);
        jsonObject.getString("session.id");
    }

    @Test
    public void testRestHelpPostAndGetAzkabanTask(){
        String data = "session.id=b814b89d-1a29-436b-98a8-ff4e7d6b201c&ajax=fetchFlowExecutions&project=AppdlSendAppsflyerDataToDSS&flow=ApolloRnoToHerculesLvs&start=0&length=10";
        String azkabanUrl = "http://localhost:8081/manager";
        String res = RestHelp.post(azkabanUrl, data);
        JSONObject jsonObject = new JSONObject(res);
        jsonObject.getString("executions");
    }

}
