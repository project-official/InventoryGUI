# InventoryGUI
A papermc GUI library.

* [Features](#Features)
* [Contact](#Contact)
* [License](#License)
* [Importing](#import-library)
* [Usage](#how-to-use-this-library)

## Features
* DSL style code
* Modular inventory GUI without the requirement of event declaration

## Contact
If you wanna send issues or discussions or PR more the faster, please contact with my discord server!
* Discord Server: [Link]("https://discord.gg/ngcTymJQXX")

## License
This library is licensed under the General Public License v3.0.
* License: [InventoryGUI License](LICENSE)

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
  implementation 'net.projecttl:InventoryGUI-api:VERSION'
}
```

* Gradle (Kotlin DSL)
```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("net.projecttl:InventoryGUI-api:VERSION")
}
```

> If you are using spigot/paper 1.17+, you can use the library-loading feature instead of shading.
* plugin.yml (1.17+)
```
# ...
libraries:
  - net.projecttl.InventoryGUI-api:VERSION
# ...
```

## How to use this Library
This library is kotlin-optimized. DSL pattern will break unless you use kotlin.
```Kotlin
class TestGui {
  fun inventory(player: Player) {
    player.gui(Component.text("TestGUI"), InventoryType.CHEST_27) {
        
        // Prints 'Hello!' when clicked
        slot(0, ItemStack(Material.GRASS_BLOCK)) {
            player.sendMessage("Hello!")
        }

        // Does nothing on click
        slot(1, ItemStack(Material.IRON_INGOT))
    }
  }
}
```
> More examples [here](InventoryGUI-test/src/main/kotlin/net/projecttl/inventorygui/test/InventoryGuiTest.kt)
