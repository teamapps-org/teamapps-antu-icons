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

import org.teamapps.icons.IconLoaderContext;
import org.teamapps.icons.IconResource;
import org.teamapps.icons.IconType;
import org.teamapps.icons.spi.IconLoader;

import java.io.IOException;
import java.io.InputStream;

public class AntuIconLoader implements IconLoader<AntuIcon> {
    /**
     * Loads an icon.
     * <p>
     * The specified icon MUST have a style set (unless the STYLE type is {@link Void}). Callers need to ensure this!
     * <p>
     * Implementations MAY therefore assume the icon's to be styled.
     *
     * @param icon    AntuIcon
     * @param size    The size of the requested icon.
     * @param context {@link IconLoaderContext)
     * @return The icon in binary form and type and size of the icon (as {@link IconResource}), or null, if the icon could not be loaded.
     */
    @Override
    public IconResource loadIcon(AntuIcon icon, int size, IconLoaderContext context) {
        return new IconResource(getSVG(icon.getIconPath(), icon.getStyle()), IconType.SVG);
    }

    private byte[] getSVG(String iconPath, AntuIconStyle style) {

        String folder = style.getFolder();
        // String iconPath = iconPath.replace("__", "/");
        String resourcePath = "/org/teamapps/icon/antu/" + folder + "/" + iconPath + ".svg";


        try(InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                return null;
            }
            return inputStream.readAllBytes();
            // String svg = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            // return svg.getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
