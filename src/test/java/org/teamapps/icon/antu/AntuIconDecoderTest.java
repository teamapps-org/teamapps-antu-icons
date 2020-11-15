/*-
 * ========================LICENSE_START=================================
 * TeamApps Antu Icon Provider
 * ---
 * Copyright (C) 2014 - 2020 TeamApps.org
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */
package org.teamapps.icon.antu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AntuIconDecoderTest {

    @Test
    public void decodeIcon() {
        AntuIconDecoder antuIconDecoder = new AntuIconDecoder();
        assertEquals(antuIconDecoder.decodeIcon("DARK~apps__48__calligrahandbook",null).getIconID(),AntuIcon.APP_CALLIGRAHANDBOOK_48.getIconID());
        assertEquals(antuIconDecoder.decodeIcon("DARK~apps__48__calligrahandbook",null).getStyle(),AntuIconStyle.DARK);

        // assertEquals(antuIconDecoder.decodeIcon("DARK~apps__48__calligrahandbook",null),AntuIcon.APP_CALLIGRAHANDBOOK_48.withStyle(AntuIconStyle.DARK));

        assertEquals(antuIconDecoder.decodeIcon("LIGHT~apps__48__calligrahandbook",null).getIconID(),AntuIcon.APP_CALLIGRAHANDBOOK_48.getIconID());
        assertEquals(antuIconDecoder.decodeIcon("LIGHT~apps__48__calligrahandbook",null).getStyle(),AntuIconStyle.LIGHT);

        assertEquals(antuIconDecoder.decodeIcon("apps__48__calligrahandbook",null).getIconID(),AntuIcon.APP_CALLIGRAHANDBOOK_48.getIconID());
        assertEquals(antuIconDecoder.decodeIcon("apps__48__calligrahandbook",null).getStyle(),AntuIconStyle.LIGHT);

    }
}
