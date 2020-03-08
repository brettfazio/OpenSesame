package com.example.juleeyahwright.opensesame.Message.Create;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Message.MessageReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

public class MessageCreateService {

    private final MessageCreateServiceListener listener;

    public MessageCreateService(MessageCreateServiceListener listener) {
        this.listener = listener;
    }

    // write the report if successful connection to the database is established
    public void createMessage(DocumentReference reference, MessageReference messageReference) {
        reference.collection(MessageReference.DEFAULT_COLLECTION_PATH)
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
