package com.example.juleeyahwright.opensesame.Message;

import org.junit.Test;

import java.util.Map;

public class MessageReferenceTest {

    @Test
    public void getFirebaseMap() {
        MessageReference report = new MessageReference("dummy", "this is a dummy report");

        Map<String, Object> map = report.getFirebaseMap();

        assert (map.get("creatorID") != null);
        assert (map.get("contents") != null);
        assert (map.size() == 2);

        assert (map.get("creatorID").equals("dummy"));
        assert (map.get("contents").equals("this is a dummy report"));
    }

    @Test
    public void getCreatorID() {
        MessageReference report = new MessageReference("dummy", "this is a dummy report");

        assert (report.getCreatorID().equals("dummy"));
    }

    @Test
    public void getContents() {
        MessageReference report = new MessageReference("dummy", "this is a dummy report");

        assert (report.getContents().equals("this is a dummy report"));
    }
}