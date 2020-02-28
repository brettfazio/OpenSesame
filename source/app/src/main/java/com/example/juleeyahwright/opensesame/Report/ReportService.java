package com.example.juleeyahwright.opensesame.Report;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ReportService {

    private FirebaseFirestore db;
    private ReportServiceListener listener;

    public ReportService(ReportServiceListener listener) {
        db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public ReportService(FirebaseFirestore db, ReportServiceListener listener) {
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
                            listener.reportRetrievalSuccess(snapshot);
                        } else {
                            listener.reportRetrievalFailure(task.getException());
                        }
                    }
                });
    }


}
