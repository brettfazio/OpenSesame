package com.example.juleeyahwright.opensesame.Message;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class MessageReference {
    public static final String DEFAULT_COLLECTION_PATH = "messages";

    protected static final String CREATOR_ID_FIELD_NAME = "creatorID";
    protected static final String CONTENTS_FIELD_NAME = "contents";

    private final String creatorID;
    private final String contents;
    private final String collectionPath;
    private final Map<String, Object> map;

    // constructor with default collection path
    public MessageReference(String creatorID, String contents) {
        this.creatorID = creatorID;
        this.contents = contents;
        this.collectionPath = DEFAULT_COLLECTION_PATH;
        this.map = makeMap();
    }

    // constructor with custom collection path
    public MessageReference(String creatorID, String contents, String collectionPath) {
        this.creatorID = creatorID;
        this.contents = contents;
        this.collectionPath = collectionPath;
        this.map = makeMap();
    }

    // constructor with a DocumentSnapshot
    public MessageReference(DocumentSnapshot snapshot) {
        this.creatorID = (String) snapshot.get(CREATOR_ID_FIELD_NAME);
        this.contents = (String) snapshot.get(CONTENTS_FIELD_NAME);
        this.collectionPath = DEFAULT_COLLECTION_PATH;
        this.map = makeMap();
    }

    // updates map with new marker after a report has been made
    private Map<String, Object> makeMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(CREATOR_ID_FIELD_NAME, creatorID);
        map.put(CONTENTS_FIELD_NAME, contents);

        return map;
    }

    public Map<String, Object> getFirebaseMap() {
        return map;
    }

    public String getCreatorID() { return creatorID; }

    public String getContents() { return contents; }

    public String getCollectionPath() {
        return collectionPath;
    }
}
