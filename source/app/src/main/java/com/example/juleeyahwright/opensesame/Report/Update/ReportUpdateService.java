package com.example.juleeyahwright.opensesame.Report.Update;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleService;
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleServiceListener;
import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class ReportUpdateService implements ReportGetSingleServiceListener {

    private FirebaseFirestore db;
    private ReportUpdateServiceListener listener;

    private String message;

    public ReportUpdateService(ReportUpdateServiceListener listener) {
        db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public ReportUpdateService(FirebaseFirestore db, ReportUpdateServiceListener listener) {
        this.db = db;
        this.listener = listener;
    }

    public void addMessageToReport(ReportReference reportReference, String message) {
        this.message = message;

        ReportGetSingleService reportGetSingleService = new ReportGetSingleService(this);
        reportGetSingleService.getReport(reportReference);
    }

    private void _addMessageToReport(DocumentSnapshot reference) {
        reference.getReference()
                .update(Report.MESSAGES_FIELD_NAME, FieldValue.arrayUnion(this.message))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.reportUpdateSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.reportUpdateFailure(e);
                    }
                });
    }

    @Override
    public void reportRetrievalSuccess(@NotNull DocumentSnapshot reference) {
        _addMessageToReport(reference);
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {
        listener.reportUpdateFailure(exception);
    }
}
