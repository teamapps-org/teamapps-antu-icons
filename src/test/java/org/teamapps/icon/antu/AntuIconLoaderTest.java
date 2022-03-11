/*-
 * ========================LICENSE_START=================================
 * TeamApps Antu Icon Provider
 * ---
 * Copyright (C) 2014 - 2022 TeamApps.org
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

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.teamapps.icons.IconResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class AntuIconLoaderTest {

    private AntuIconLoader antuIconLoader = new AntuIconLoader();

    @Test
    public void loadIcon() {
        IconResource iconResource = antuIconLoader.loadIcon(AntuIcon.STATUS_SECURITY_LOW_22.withStyle(AntuIconStyle.LIGHT), 22, null);
        String svg = new String(iconResource.getBytes());
        assertNotEquals("", svg);
        assertTrue(svg.contains("<svg"));

        String resourcePath = "/org/teamapps/icon/antu/Antu/status/22/security-low.svg";
        String resourcesvg = loadResourceString(resourcePath);
        assertEquals(resourcesvg, svg);

        assertTrue("String contains svg tag", resourcesvg.contains("svg"));
        assertTrue("resource contains svg start tag " + "\n" + svg, svg.contains("<svg"));
        assertTrue("resource contains svg end tag " + "\n" + svg, svg.contains("</svg>"));

    }

    @Test
    public void loadIconDark() {
        IconResource iconResource = antuIconLoader.loadIcon(AntuIcon.STATUS_KDECONNECT_TRAY_22.withStyle(AntuIconStyle.DARK), 22, null);
        String svg = new String(iconResource.getBytes());
        assertNotEquals("", svg);
        assertTrue(svg.contains("<svg"));

        String resourcePath = "/org/teamapps/icon/antu/AntuDark/status/22/kdeconnect-tray.svg";
        String resourcesvg = loadResourceString(resourcePath);
        assertEquals(resourcesvg, svg);

        assertTrue("String contains svg tag", resourcesvg.contains("svg"));
        assertTrue("resource contains svg start tag " + "\n" + svg, svg.contains("<svg"));
        assertTrue("resource contains svg end tag " + "\n" + svg, svg.contains("</svg>"));

    }

    @Test
    public void loadAllIcons() {

        for (AntuIcon antuIcon : AntuIcon.getIcons()) {
            String svg = "";
            try {
                IconResource iconResource = antuIconLoader.loadIcon(antuIcon, 22, null);
                svg = new String(iconResource.getBytes());
            } catch (Exception e) {
                System.out.println("ERROR Loading Icon: " + antuIcon.getIconId() + ", error: " + e.getMessage());
            }
            assertNotNull(svg);
//            assertNotEquals("", svg);
            if (!svg.contains("<svg")) {
                System.out.println(antuIcon.getIconId() + "\n" + svg);
            }
            assertTrue("resource contains svg start tag " + antuIcon.getIconId() + "\n" + svg, svg.contains("<svg"));
            assertTrue("resource contains svg end tag " + antuIcon.getIconId() + "\n" + svg, svg.contains("</svg>"));
        }
    }

    @Test
    public void loadAllIconsDark() {
        // Test Dark Icons
        for (AntuIcon antuIcon : AntuIcon.getIcons()) {
            String svg = "";
            try {
                IconResource iconResource = antuIconLoader.loadIcon(antuIcon.withStyle(AntuIconStyle.DARK), 22, null);
                svg = new String(iconResource.getBytes());
            } catch (Exception e) {
                System.out.println("ERROR Loading Icon: " + antuIcon.getIconId() + ", error: " + e.getMessage());
            }
            assertNotNull(svg);
//            assertNotEquals("should not be empty " + antuIcon.getIconID() + "\n" + svg,"", svg);
            assertTrue("resource contains svg start tag " + antuIcon.getIconId() + "\n" + svg, svg.contains("<svg"));
            assertTrue("resource contains svg end tag " + antuIcon.getIconId() + "\n" + svg, svg.contains("</svg>"));
        }

//        String resourcePath = "/org/teamapps/icon/antu-classic/Antu/status/22/kdeconnect-tray.svg";
//        String resourcesvg = loadResourceString(resourcePath);
//        assertEquals(resourcesvg, svg);
    }

    private static String loadResourceString(String resourceName) {
        try {
            return IOUtils.resourceToString(resourceName, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Resource not found.", e);
        }
    }
}
