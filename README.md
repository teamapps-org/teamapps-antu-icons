# Teamapps Icon Provider for Antu Classic Icons


This Icon set contains 3582 SVG icons and two styles `LIGHT`(default) and `DARK` for Dark Backgrounds. The original purpose of this collection is a for a Linux Desktop Operating System.

Icons Source: <https://github.com/fabianalexisinostroza/Antu-classic>

The Icons are in various Categories:

* `AntuIcon.ACTION_*`: 1471 icons
* `AntuIcon.APPLET_*`: 55 icons
* `AntuIcon.APP_*`: 1498 icons
* `AntuIcon.CATEGORY_*`: 32 icons
* `AntuIcon.DEVICE_*`: 70 icons
* `AntuIcon.EMBLEM_*`: 21 icons
* `AntuIcon.EMOTE_*`: 32 icons
* `AntuIcon.PLACE_*`: 270 icons
* `AntuIcon.STATUS_*`: 136 icons

Mimetype Icons of the Antu Theme are excluded

## Usage

Add dependency to your TeamApps Maven Project:

~~~xml
        <dependency>
            <groupId>org.teamapps</groupId>
            <artifactId>teamapps-antu-icon-provider</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
~~~

Usage:

~~~java
import org.teamapps.icons.Icon;
import org.teamapps.icon.antu.AntuIcon;
import org.teamapps.icon.antu.AntuIconStyle;

public class IconExample {
    public static Void someMethod() {
        Icon icon1 = AntuIcon.STATUS_SECURITY_HIGH_64;
        Icon icon2 = AntuIcon.STATUS_MIC_OFF_22.withStyle(AntuIconStyle.DARK);
        Icon icon3 = AntuIcon.ACTION_AUTOCORRECTION_32.withStyle(AntuIconStyle.LIGHT); // Default Style
    }
}
~~~

## Creation Notes

Sync Icons:

~~~bash
# clone antu-classic repository to directory beside teamapps-antu-icon-provider
git clone https://github.com/fabianalexisinostroza/Antu-classic ../antu-classic

# sync content without .git and mimetype icons to resources, exclude duplicate folders
rsync -a --delete  --delete-excluded \
  --exclude .git \
  --exclude /*/mimetypes/ \
  --exclude /*/actions/16 \
  --exclude /*/actions/22 \
  --exclude /*/devices/16 \
  --exclude /*/places/16 \
  --exclude /*/status/16 \
  --exclude /AntuExtras \
  ../antu-classic/ src/main/resources/org/teamapps/icon/antu-classic/

cd src/main/resources/org/teamapps/icon/antu-classic/
find Antu -name '*.svg' -type f
~~~

Enum naming regex

~~~bash
((\D+__)(\d\d)__(.*)\.svg)
$4_$3
~~~

## TEAMAPPS SOFTWARE LICENSE

Apache 2.0

## ANTU ICONS LICENSE

LGPL, GNU LESSER GENERAL PUBLIC LICENSE Version 2.1 <https://github.com/fabianalexisinostroza/Antu-classic/blob/master/LICENSE.md>