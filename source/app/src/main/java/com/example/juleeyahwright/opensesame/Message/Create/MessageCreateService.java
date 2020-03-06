package com.example.juleeyahwright.opensesame.Message.Create;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Message.MessageReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MessageCreateService {

    private final MessageCreateServiceListener listener;
    private final FirebaseFirestore db;

    public MessageCreateService(@NonNull FirebaseFirestore db, MessageCreateServiceListener listener) {
        this.db = db;
        this.listener = listener;
    }

    public MessageCreateService(MessageCreateServiceListener listener) {
        this.db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    // write the report if successful connection to the database is established
    public void createMessage(DocumentReference report, MessageReference messageReference) {

        report.collection(MessageReference.DEFAULT_COLLECTION_PATH)
                .add(messageReference.getFirebaseMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        listener.messageCreateSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.messageCreateFailure(e);
                    }
                });


    }


}
