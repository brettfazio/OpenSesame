package com.example.juleeyahwright.opensesame.Report.Get;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ReportGetService {

    private FirebaseFirestore db;
    private ReportGetServiceListener listener;

    public ReportGetService(ReportGetServiceListener listener) {
        db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public ReportGetService(FirebaseFirestore db, ReportGetServiceListener listener) {
        this.db = db;
        this.listener = listener;
    }

    public void getReports() {
        CollectionReference reports = db.collection(Report.DEFAULT_COLLECTION_PATH);
        reports.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot snapshot = task.getResult();
                            listener.reportRetrievalSuccess(snapshot, convertToReportReferences(snapshot));
                        } else {
                            listener.reportRetrievalFailure(task.getException());
                        }
                    }
                });
    }

    private ReportReference[] convertToReportReferences(QuerySnapshot querySnapshot) {
        ReportReference[] result = new ReportReference[querySnapshot.size()];

        int index = 0;
        for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
            result[index] = new ReportReference(documentSnapshot);
            index++;
        }

        return result;
    }

    public void addMessageToReport(ReportReference reportReference, String message) {
        DocumentReference documentReference = db.collection(ReportReference.DEFAULT_COLLECTION_PATH).document(reportReference.getDocumentId());

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                    } else {

                    }
                } else {

                }
            }
        });
    }

}
