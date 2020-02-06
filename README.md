# Teamapps Icon Provider for Antu Classic Icons

Icons Source: <https://github.com/fabianalexisinostroza/Antu-classic>

Icons are organised in subgroups:

* `AntuIcon.ACTIONS`: 1471 icons
* `AntuIcon.APPLETS`: 55 icons
* `AntuIcon.APPS`: 1498 icons
* `AntuIcon.CATEGORIES`: 32 icons
* `AntuIcon.DEVICES`: 70 icons
* `AntuIcon.EMBLEMS`: 21 icons
* `AntuIcon.EMOTES`: 32 icons
* `AntuIcon.PLACES`: 270 icons
* `AntuIcon.STATUS`: 136 icons

Mimetype Icons of the Antu Theme are excluded

## Usage

Add dependency to your TeamApps Maven Project:

~~~xml
        <dependency>
            <groupId>org.teamapps</groupId>
            <artifactId>teamapps-antu-icon-provider</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
~~~

Add additional Icon Provider to WebController

~~~java

public class PanelExample {
    public static void main(String[] args) throws Exception {
        SimpleWebController controller = new SimpleWebController(context -> {

            // create new Panel
            Panel panel = new Panel(AntuIcon.STATUS.SECURITY_HIGH_64, "Panel with AntuIcon");

            // add DummyComponent as panel Content
            panel.setContent(new DummyComponent());

            return panel;
        });
        // Register AntuIconProvider
        controller.addAdditionalIconProvider(new AntuIconProvider());
        new TeamAppsJettyEmbeddedServer(controller, Files.createTempDir()).start();
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