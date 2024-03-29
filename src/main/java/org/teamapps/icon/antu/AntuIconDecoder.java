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

import org.teamapps.icons.IconDecoderContext;
import org.teamapps.icons.spi.IconDecoder;

public class AntuIconDecoder implements IconDecoder<AntuIcon> {
    /**
     * {@inheritDoc}
     */
    @Override
    public AntuIcon decodeIcon(String encodedIconString, IconDecoderContext context) {
        String[] parts = encodedIconString.split("~");

        if (parts.length == 1) {
            // No Style / Default Style
            String iconID = parts[0];
            return AntuIcon.forId(iconID); // .withStyle(AntuIconStyle.LIGHT);
        } else if (parts.length == 2){
            // Some Style
            String iconID = parts[1];
            String iconStyle = parts[0];

            // .withStyle(AntuIconStyle.LIGHT);
            switch (iconStyle) {
                case "DARK":
                    return AntuIcon.forId(iconID).withStyle(AntuIconStyle.DARK);
                case "LIGHT":
                    return AntuIcon.forId(iconID);
                default:
                    return null;
            }

        }
        return null;
    }
}
