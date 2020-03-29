package com.example.juleeyahwright.opensesame.Report.Update;

import androidx.annotation.NonNull;

import com.example.juleeyahwright.opensesame.Message.Create.MessageCreateService;
import com.example.juleeyahwright.opensesame.Message.Create.MessageCreateServiceListener;
import com.example.juleeyahwright.opensesame.Message.MessageReference;
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleService;
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleServiceListener;
import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ReportUpdateService implements ReportGetSingleServiceListener, MessageCreateServiceListener {

    private enum Type {
        MESSAGE, FIELDS
    }

    private ReportUpdateServiceListener listener;

    private String message;
    private ReportReference reportReference;
    private String newTitle, newInfo, newLocInfo;
    private Type current;

    public ReportUpdateService(ReportUpdateServiceListener listener) {
        this.listener = listener;
    }

    public void updateReportFields(ReportReference reportReference, String newTitle, String newInfo, String newLocInfo) {
        this.reportReference = reportReference;

        current = Type.FIELDS;

        this.newTitle = newTitle;
        this.newInfo = newInfo;
        this.newLocInfo = newLocInfo;

        ReportGetSingleService reportGetSingleService = new ReportGetSingleService(this);
        reportGetSingleService.getReport(reportReference);
    }

    public void removeReport(ReportReference reportReference) {
        this.reportReference = reportReference;
        ReportGetSingleService reportGetSingleService = new ReportGetSingleService(this);
        reportGetSingleService.removeReport(reportReference);
    }


    public void addMessageToReport(ReportReference reportReference, String message) {
        this.message = message;

        current = Type.MESSAGE;

        ReportGetSingleService reportGetSingleService = new ReportGetSingleService(this);
        reportGetSingleService.getReport(reportReference);
    }

    private void _addMessageToReport(DocumentSnapshot reference) {
        MessageCreateService messageCreateService = new MessageCreateService(this);

        MessageReference messageReference = new MessageReference(
                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                message);

        messageCreateService.createMessage(reference.getReference(), messageReference);
    }

    private Map<String, Object> makeMap(String name, String information, String locationInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put(Report.INFORMATION_FIELD_NAME, information);
        map.put(Report.NAME_FIELD_NAME, name);
        map.put(Report.LOCATION_INFO_FIELD_NAME, locationInfo);
        return map;
    }

    private void _updateReport(DocumentSnapshot snapshot) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(Report.DEFAULT_COLLECTION_PATH).document(reportReference.getDocumentId()).set(makeMap(newTitle, newInfo, newLocInfo), SetOptions.merge())
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
        switch (current) {
            case FIELDS:
                _updateReport(reference);
                break;
            case MESSAGE:
                _addMessageToReport(reference);
                break;
            default:
                break;
        }
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {
        listener.reportUpdateFailure(exception);
    }

    @Override
    public void messageCreateSuccess() {
        listener.reportUpdateSuccess();
    }

    @Override
    public void messageCreateFailure(@NotNull Exception exception) {
        listener.reportUpdateFailure(exception);
    }
}
