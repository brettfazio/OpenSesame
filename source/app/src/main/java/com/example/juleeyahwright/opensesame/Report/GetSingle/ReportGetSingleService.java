package com.example.juleeyahwright.opensesame.Report.GetSingle;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReportGetSingleService {

    private FirebaseFirestore db;
    private ReportGetSingleServiceListener listener;

    public ReportGetSingleService(ReportGetSingleServiceListener listener) {
        db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public ReportGetSingleService(FirebaseFirestore db, ReportGetSingleServiceListener listener) {
        this.db = db;
        this.listener = listener;
    }

    public void getReport(ReportReference reportReference) {
        DocumentReference documentReference = db.collection(ReportReference.DEFAULT_COLLECTION_PATH).document(reportReference.getDocumentId());

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        listener.reportRetrievalSuccess(document);
                    } else {
                        listener.reportRetrievalFailure(task.getException());
                    }
                } else {
                    listener.reportRetrievalFailure(task.getException());
                }
            }
        });
    }
}
