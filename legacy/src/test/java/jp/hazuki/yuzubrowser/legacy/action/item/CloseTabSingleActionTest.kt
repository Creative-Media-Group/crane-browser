/*
 * Copyright (C) 2017-2019 Hazuki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.hazuki.yuzubrowser.legacy.action.item

import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.squareup.moshi.JsonReader
import jp.hazuki.yuzubrowser.legacy.action.SingleAction
import okio.buffer
import okio.source
import org.junit.Test
import java.io.ByteArrayInputStream

class CloseTabSingleActionTest {

    @Test
    fun testDecode() {
        val actionJson = "[[1001,null]]"
        val json = """{"0":$actionJson}"""

        val reader = JsonReader.of(ByteArrayInputStream(json.toByteArray()).source().buffer())
        val action = CloseTabSingleAction(SingleAction.PAGE_AUTO_SCROLL, reader)

        assertk.assertThat(action.defaultAction[0]).isInstanceOf(SingleAction::class.java)

        assertk.assertThat(action.defaultAction[0].id).isEqualTo(1001)

        assertk.assertThat(reader.peek()).isEqualTo(JsonReader.Token.END_DOCUMENT)
    }

    @Test
    fun testNothingDecode() {
        val actionJson = "[]"
        val json = """{"0":$actionJson}"""

        val reader = JsonReader.of(ByteArrayInputStream(json.toByteArray()).source().buffer())
        val action = CloseTabSingleAction(SingleAction.PAGE_AUTO_SCROLL, reader)


        assertk.assertThat(action.defaultAction.size).isEqualTo(0)

        assertk.assertThat(reader.peek()).isEqualTo(JsonReader.Token.END_DOCUMENT)
    }
}