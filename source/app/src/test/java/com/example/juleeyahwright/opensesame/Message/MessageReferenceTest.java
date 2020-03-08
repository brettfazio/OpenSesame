package com.example.juleeyahwright.opensesame.Message;

import com.google.firebase.firestore.DocumentSnapshot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class MessageReferenceTest {

    @Mock
    private DocumentSnapshot snapshot;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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

    @Test
    public void constructWithDocumentSnapshot() {
        Mockito.when(snapshot.get("creatorID")).thenReturn("1234");
        Mockito.when(snapshot.get("contents")).thenReturn("This");

        MessageReference message = new MessageReference(snapshot);

        assert (message.getCreatorID().equals("1234"));
        assert (message.getContents().equals("This"));
    }
}