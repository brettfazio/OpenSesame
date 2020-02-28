package com.example.juleeyahwright.opensesame;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.juleeyahwright.opensesame.CreateReport.CreateReportController;
import com.example.juleeyahwright.opensesame.CreateReport.CreateReportListener;
import com.example.juleeyahwright.opensesame.CreateReport.Report;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

public class CreateReportControllerTest implements CreateReportListener {

    private static final String TEST_COLLECTION_PATH = "reports-test";

    private static final int SUCCESS = 1;
    private static final int FAILURE = -1;
    private static final int UNDEF = 0;

    private Task<DocumentReference> successTask;
    private Task<DocumentReference> failureTask;

    @Mock
    private FirebaseFirestore db;

    @Mock
    private CollectionReference collectionReference;

    @Mock
    private DocumentReference reference;

    private CreateReportController createReportController;

    private int createResult;

    // Firebase unit test setup
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        successTask = new Task<DocumentReference>() {
            @Override
            public boolean isComplete() {
                return false;
            }

            @Override
            public boolean isSuccessful() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Nullable
            @Override
            public DocumentReference getResult() {
                return null;
            }

            @Nullable
            @Override
            public <X extends Throwable> DocumentReference getResult(@NonNull Class<X> aClass) throws X {
                return null;
            }

            @Nullable
            @Override
            public Exception getException() {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnSuccessListener(@NonNull OnSuccessListener<? super DocumentReference> onSuccessListener) {
                onSuccessListener.onSuccess(reference);
                return successTask;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super DocumentReference> onSuccessListener) {
                onSuccessListener.onSuccess(reference);
                return successTask;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super DocumentReference> onSuccessListener) {
                onSuccessListener.onSuccess(reference);
                return successTask;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<DocumentReference> onCompleteListener) {
                onCompleteListener.onComplete(successTask);
                return successTask;
            }
        };
        failureTask = new Task<DocumentReference>() {
            @Override
            public boolean isComplete() {
                return false;
            }

            @Override
            public boolean isSuccessful() {
                return false;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Nullable
            @Override
            public DocumentReference getResult() {
                return null;
            }

            @Nullable
            @Override
            public <X extends Throwable> DocumentReference getResult(@NonNull Class<X> aClass) throws X {
                return null;
            }

            @Nullable
            @Override
            public Exception getException() {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnSuccessListener(@NonNull OnSuccessListener<? super DocumentReference> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super DocumentReference> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super DocumentReference> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                onFailureListener.onFailure(new Exception());
                return failureTask;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                onFailureListener.onFailure(new Exception());
                return failureTask;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                onFailureListener.onFailure(new Exception());
                return failureTask;
            }

            @NonNull
            @Override
            public Task<DocumentReference> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<DocumentReference> onCompleteListener) {
                onCompleteListener.onComplete(failureTask);
                return failureTask;
            }
        };

        createReportController = new CreateReportController(db, this);

        createResult = UNDEF;
    }

    /*
    Testing: When report fields are entered, a report is made
    Pass Criteria: createResult task returns success
     */
    @Test
    public void writeReportSuccess_test() {
        Report report = new Report("test", "test info", "basement", new LatLng(0,0), TEST_COLLECTION_PATH);

        Mockito.when(db.collection(TEST_COLLECTION_PATH)).thenReturn(collectionReference);
        Mockito.when(collectionReference.add(report.getFirebaseMap())).thenReturn(successTask);

        createReportController.writeReport(report);

        assert (createResult == SUCCESS);
    }

    @Override
    public void reportCreateSuccess(@NotNull Report report) {
        createResult = SUCCESS;
    }

    @Override
    public void reportCreateFailure(@NotNull Report report, @NotNull Exception exception) {
        createResult = FAILURE;
    }
}
