package com.example.juleeyahwright.opensesame.Report.Update;

import com.example.juleeyahwright.opensesame.Message.Create.MessageCreateService;
import com.example.juleeyahwright.opensesame.Message.Create.MessageCreateServiceListener;
import com.example.juleeyahwright.opensesame.Message.MessageReference;
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleService;
import com.example.juleeyahwright.opensesame.Report.GetSingle.ReportGetSingleServiceListener;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class ReportUpdateService implements ReportGetSingleServiceListener, MessageCreateServiceListener {
    private ReportUpdateServiceListener listener;

    private String message;

    public ReportUpdateService(ReportUpdateServiceListener listener) {
        this.listener = listener;
    }

    public void addMessageToReport(ReportReference reportReference, String message) {
        this.message = message;

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

    @Override
    public void reportRetrievalSuccess(@NotNull DocumentSnapshot reference) {
        _addMessageToReport(reference);
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
