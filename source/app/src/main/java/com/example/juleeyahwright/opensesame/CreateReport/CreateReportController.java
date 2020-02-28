package com.example.juleeyahwright.opensesame.CreateReport;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
/*
CreateReportController: makes sure that report is added to database
 */
public class CreateReportController {

    private CreateReportListener listener;
    private FirebaseFirestore db;

    public CreateReportController(@NonNull FirebaseFirestore db, CreateReportListener listener) {
        this.db = db;
        this.listener = listener;
    }

    public CreateReportController(CreateReportListener listener) {
        this.db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public void writeReport(String title, String info, LatLng location) {
        this.writeReport(new Report(title, info, location));
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
