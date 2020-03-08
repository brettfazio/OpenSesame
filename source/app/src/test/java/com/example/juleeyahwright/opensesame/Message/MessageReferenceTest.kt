package com.example.juleeyahwright.opensesame.Message

import com.google.firebase.firestore.DocumentSnapshot
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MessageReferenceTest {
    @Mock
    private val snapshot: DocumentSnapshot? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getFirebaseMap() {
            val report = MessageReference("dummy", "this is a dummy report")
            val map = report.firebaseMap
            assert(map["creatorID"] != null)
            assert(map["contents"] != null)
            assert(map.size == 2)
            assert(map["creatorID"] == "dummy")
            assert(map["contents"] == "this is a dummy report")
        }

    @Test
    fun getCreatorID() {
            val report = MessageReference("dummy", "this is a dummy report")
            assert(report.creatorID == "dummy")
        }

    @Test
    fun getContents() {
            val report = MessageReference("dummy", "this is a dummy report")
            assert(report.contents == "this is a dummy report")
        }

    @Test
    fun constructWithDocumentSnapshot() {
        Mockito.`when`(snapshot!!["creatorID"]).thenReturn("1234")
        Mockito.`when`(snapshot["contents"]).thenReturn("This")
        val message = MessageReference(snapshot)
        assert(message.creatorID == "1234")
        assert(message.contents == "This")
    }
}