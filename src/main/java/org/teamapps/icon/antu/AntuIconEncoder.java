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

import org.teamapps.icons.IconEncoderContext;
import org.teamapps.icons.IconLoaderContext;
import org.teamapps.icons.spi.IconEncoder;

public class AntuIconEncoder implements IconEncoder<AntuIcon, AntuIconStyle> {
    /**
     * Creates a string representation of the provided icon.
     * <p>
     * The string representation may be an arbitrary string, as long as it is an allowed string for URL path segments.
     * It should be safe to use <code>a-z A-Z 0-9 . - _ ~ ! $ ' ( ) * + , ; = : @</code>.
     * If the String contains parenthesis ("(" or ")"), it must make sure to close any opening one of them.
     * <p>
     * Note that this method MUST support encoding unstyled icons, i.e. icons that have no style set (null).
     *
     * @param icon    The icon to encode.
     * @param context {@link IconLoaderContext}
     * @return The encoded icon
     */
    @Override
    public String encodeIcon(AntuIcon icon, IconEncoderContext context) {
        String encodedString;
        AntuIconStyle style = icon.getStyle();
        if (style == AntuIconStyle.DARK) {
            encodedString = "DARK~"+icon.getIconID();
        } else {
            encodedString = icon.getIconID();
        }
        return encodedString;
    }
}
