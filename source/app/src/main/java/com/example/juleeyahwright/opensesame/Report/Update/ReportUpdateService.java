package com.example.juleeyahwright.opensesame.Report.Update;

import com.google.firebase.firestore.FirebaseFirestore;

public class ReportUpdateService {

    private FirebaseFirestore db;
    private ReportUpdateServiceListener listener;

    public ReportUpdateService(ReportUpdateServiceListener listener) {
        db = FirebaseFirestore.getInstance();
        this.listener = listener;
    }

    public ReportUpdateService(FirebaseFirestore db, ReportUpdateServiceListener listener) {
        this.db = db;
        this.listener = listener;
    }

}
