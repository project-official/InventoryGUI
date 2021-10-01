# InventoryGUI
This is Minecraft paper gui library.

* [Library Feature](https://github.com/ProjectTL12345/InventoryGUI#library-feature)
* [Library License](https://github.com/ProjectTL12345/InventoryGUI#library-license)
* [API Build](https://github.com/ProjectTL12345/InventoryGUI#import-library)
* [How to use this Library](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-library)

## Library Feature
* DSL style code
* Modular inventory gui with no event declaration required

## Library License
This API uses the GPL-3.0 open source license.
* License: [InventoryGUI License](https://github.com/ProjectTL12345/InventoryGUI/blob/master/LICENSE)

## Import Library

* Maven
```XML
<repositories>
    <repository>
        <releases>
            <enabled>true</enabled>
        </releases>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
</repositories>

<dependency>
    <groupId>net.projecttl</groupId>
    <artifactId>InventoryGUI-api</artifactId>
    <version>VERSION</version>
</dependency>
```

* Gradle (Groovy DSL)
```groovy
repositories {
  mavenCentral()
}

dependencies {
  compileOnly 'net.projecttl:InventoryGUI-api:VERSION'
}
```

* Gradle (Kotlin DSL)
```kotlin
repositories {
  mavenCentral()
}

dependencies {
  compileOnly("net.projecttl:InventoryGUI-api:VERSION")
}
```
Do not shade it! This can installed without shade (latest only feature)!

* Plugin.YML (latest only)
```
# ...
libraries:
  - net.projecttl.InventoryGUI-api:VERSION
# ...
```

## How to use this Library
This api must use kotlin only. (you can use java but it will be hard.)
```Kotlin
class TestGui(val plugin: JavaPlugin) {
  fun inventory(player: Player) {
    player.gui(plugin, InventoryType.CHEST_27, Component.text("TestGUI")) {
        slot(0, ItemStack(Material.GRASS_BLOCK)) {
            player.sendMessage("Hello!")
        }

        // dummy slot
        slot(1, ItemStack(Material.IRON_INGOT))
    }
  }
}
```