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

import org.apache.commons.io.IOUtils;
import org.teamapps.icons.api.IconStyle;
import org.teamapps.icons.provider.SvgIconProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class AntuIconProvider implements SvgIconProvider<IconStyle> {
	public static final String LIBRARY_ID = "antu";

	public static final IconStyle STANDARD = new IconStyle() {
		@Override
		public String getStyleId() {
			return "Antu";
		}

		@Override
		public String getStyleName() {
			return null;
		}

		@Override
		public boolean canBeUsedAsSubIcon() {
			return true;
		}
	};
	public static final IconStyle DARK = new IconStyle() {
		@Override
		public String getStyleId() {
			return "AntuDark";
		}

		@Override
		public String getStyleName() {
			return null;
		}

		@Override
		public boolean canBeUsedAsSubIcon() {
			return true;
		}
	};
	public static final Set<IconStyle> STYLES = new HashSet<>();

	static {
		STYLES.add(STANDARD);
		STYLES.add(DARK);
	}

	public String getInnerSvg(IconStyle style, String s) {
		return null;
	}

	@Override
	public byte[] getIcon(String styleId, int size, String iconName) {
		String svg = getSVG(styleId, iconName);
		if (svg != null) {
			return svg.getBytes(StandardCharsets.UTF_8);
		} else {
			return null;
		}
	}

	private String getSVG(String styleId, String iconName) {
		if (!iconName.endsWith(".svg")) {
			iconName += ".svg";
		}
        String iconPath = iconName.replace("__", "/");
		String iconFolder;

		// Default style of TeamApps is "plain_grey_700"
		if (styleId == null || styleId.equals("plain_grey_700")){
			iconFolder = "Antu";
		} else {
			iconFolder = styleId;
		}
		InputStream inputStream = getClass().getResourceAsStream("/org/teamapps/icon/antu-classic/" + iconFolder + "/" + iconPath);

		if (inputStream == null) {
			return null;
		}
		try {
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getIconLibraryId() {
		return LIBRARY_ID;
	}

	public Set<Integer> getAvailableIconSizes() {
		return null;
	}

	public Set<IconStyle> getAvailableIconStyles() {
		return STYLES;
	}

	public IconStyle getDefaultDesktopStyle() {
		return STANDARD;
	}

	public IconStyle getDefaultMobileStyle() {
		return STANDARD;
	}

	public IconStyle getDefaultSubIconStyle() {
		return STANDARD;
	}

}
