package com.example.juleeyahwright.opensesame;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.juleeyahwright.opensesame.Report.Report;
import com.example.juleeyahwright.opensesame.Report.ReportReference;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetService;
import com.example.juleeyahwright.opensesame.Report.Get.ReportGetServiceListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

public class ReportGetGetServiceTest implements ReportGetServiceListener {

    private static final int SUCCESS = 1;
    private static final int FAILURE = -1;
    private static final int UNDEF = 0;

    private Task<QuerySnapshot> successTask;
    private Task<QuerySnapshot> failureTask;

    @Mock
    private FirebaseFirestore db;

    @Mock
    private CollectionReference collectionReference;

    @Mock
    private QuerySnapshot querySnapshot;

    private ReportGetService reportGetService;

    private int result;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        successTask = new Task<QuerySnapshot>() {
            @Override
            public boolean isComplete() {
                return true;
            }

            @Override
            public boolean isSuccessful() {
                return true;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Nullable
            @Override
            public QuerySnapshot getResult() {
                return querySnapshot;
            }

            @Nullable
            @Override
            public <X extends Throwable> QuerySnapshot getResult(@NonNull Class<X> aClass) throws X {
                return null;
            }

            @Nullable
            @Override
            public Exception getException() {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnSuccessListener(@NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnCompleteListener(@NonNull OnCompleteListener<QuerySnapshot> onCompleteListener) {
                onCompleteListener.onComplete(successTask);
                return successTask;
            }
        };

        failureTask = new Task<QuerySnapshot>() {
            @Override
            public boolean isComplete() {
                return true;
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
            public QuerySnapshot getResult() {
                return null;
            }

            @Nullable
            @Override
            public <X extends Throwable> QuerySnapshot getResult(@NonNull Class<X> aClass) throws X {
                return null;
            }

            @Nullable
            @Override
            public Exception getException() {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnSuccessListener(@NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super QuerySnapshot> onSuccessListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
                return null;
            }

            @NonNull
            @Override
            public Task<QuerySnapshot> addOnCompleteListener(@NonNull OnCompleteListener<QuerySnapshot> onCompleteListener) {
                onCompleteListener.onComplete(failureTask);
                return failureTask;
            }
        };

        reportGetService = new ReportGetService(db, this);

        result = UNDEF;
    }

    /*
    Testing: Successful fetch to database
    Pass Criteria: reportRetrievalSuccess is called
     */
    @Test
    public void getReportsSuccess_test() {
        Mockito.when(db.collection(Report.DEFAULT_COLLECTION_PATH)).thenReturn(collectionReference);
        Mockito.when(collectionReference.get()).thenReturn(successTask);

        reportGetService.getReports();
        System.out.println(result);
        assert (result == SUCCESS);
    }

    /*
    Testing: Failure fetch to database
    Pass Criteria: reportRetrievalFailure is called
    */
    @Test
    public void getReportsFailure_test() {
        Mockito.when(db.collection(Report.DEFAULT_COLLECTION_PATH)).thenReturn(collectionReference);
        Mockito.when(collectionReference.get()).thenReturn(failureTask);

        reportGetService.getReports();
        System.out.println(result);
        assert (result == FAILURE);
    }

    @Override
    public void reportRetrievalSuccess(@NotNull QuerySnapshot querySnapshot, @NotNull ReportReference[] reportReferences) {
        result = SUCCESS;
    }

    @Override
    public void reportRetrievalFailure(@NotNull Exception exception) {
        result = FAILURE;
    }
}
