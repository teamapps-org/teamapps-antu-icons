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

public class AntuIconTest {

    @Test
    public void getIcons() throws Exception {
        assertTrue(AntuIcon.getIcons().contains(AntuIcon.ACTION_DOCUMENTINFO_24));
    }

    @Test
    public void forID() throws Exception {
        assertEquals(AntuIcon.forID("STATUS_KDECONNECT_TRAY_22"), AntuIcon.STATUS_KDECONNECT_TRAY_22);
    }
    @Test
    public void forIDDark() throws Exception {
        assertEquals(AntuIcon.forIDDark("STATUS_KDECONNECT_TRAY_22"), AntuIcon.STATUS_KDECONNECT_TRAY_22.withStyle(AntuIconStyle.DARK));
    }

    @Test
    public void withStyle() {
        assertEquals(AntuIcon.STATUS_KDECONNECT_TRAY_22.withStyle(AntuIconStyle.DARK).getStyle(), AntuIconStyle.DARK);

        assertEquals(AntuIcon.STATUS_KDECONNECT_TRAY_22.withStyle(AntuIconStyle.LIGHT), AntuIcon.STATUS_KDECONNECT_TRAY_22);
    }

    @Test
    public void getStyle() {
        assertEquals(AntuIcon.STATUS_KDECONNECT_TRAY_22.getStyle(), AntuIconStyle.LIGHT);

        assertEquals(AntuIcon.STATUS_KDECONNECT_TRAY_22.withStyle(AntuIconStyle.DARK).getStyle(), AntuIconStyle.DARK);
        assertEquals(AntuIcon.STATUS_KDECONNECT_TRAY_22.withStyle(AntuIconStyle.LIGHT).getStyle(), AntuIconStyle.LIGHT);
    }

    @Test
    public void getIconID() {
        assertEquals("STATUS_KDECONNECT_TRAY_22", AntuIcon.STATUS_KDECONNECT_TRAY_22.getIconID());
    }
}
