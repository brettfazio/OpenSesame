package com.example.juleeyahwright.opensesame.AccountModel;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.CreateReport.CreateReportListener;
import com.example.juleeyahwright.opensesame.Report.Report;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountReportController {
    private final AccountReportListener listener;
    private final FirebaseFirestore db;

    public AccountReportController(@NonNull FirebaseFirestore db, AccountReportListener listener) {
        this.db = db;
        this.listener = listener;
    }

    public AccountReportController(AccountReportListener listener) {
        this.db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public void updateReport(Report report, String title, String info, String locationInfo, LatLng location, String collectionPath, String uid) {
        this.writeReport(new Report(title, info, locationInfo, location, collectionPath, uid));
    }

    // write the report if successful connection to the database is established
    public void writeReport(@NonNull final Report report) {

        db.collection(report.getCollectionPath())
                .add(report.getFirebaseMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        listener.reportCreateSuccess(report);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.reportCreateFailure(report, e);
                    }
                });
    }
}
