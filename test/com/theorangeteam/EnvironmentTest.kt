package com.theorangeteam

import org.junit.Test
import kotlin.test.assertNotNull

class EnvironmentTest {


    @Test
    fun shouldHaveFirebaseJsonAdded() {
        assertNotNull(JsonLoaderForTests.loadWithRoot("resources/the-proud-firebase-adminsdk.json"))
    }
}