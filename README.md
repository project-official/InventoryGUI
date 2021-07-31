# InventoryGUI
This is Minecraft paper gui API.

* [API Feature](https://github.com/ProjectTL12345/InventoryGUI#api-feature)
* [API License](https://github.com/ProjectTL12345/InventoryGUI#api-license)
* [How to use this API in server](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-in-server)
* [API Build](https://github.com/ProjectTL12345/InventoryGUI#api-build)
* [How to use this API dependency](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-dependency)

## API Feature
* DSL style code
* Modular inventory gui with no event declaration required

## API License
This API uses the GPL-3.0 open source license.
* License: [InventoryGUI License](https://github.com/ProjectTL12345/InventoryGUI/blob/master/LICENSE)

## How to use this API in server
This is a dependency library, you must add this API to use the plugin using this dependency library.
To use a plugin using this dependency library, you need to do the following:

* Step 1: Please go to your server.
* Step 2: Please add this dependency library to your plugins folder.

If you did the above, you can use the plugin using this dependency.

## API Build

* Maven
```XML
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.ProjectTL12345</groupId>
  <artifactId>InventoryGUI</artifactId>
  <version>VERSION</version>
</dependency>
```

* Gradle (Groovy DSL)
```groovy
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  compileOnly 'com.github.ProjectTL12345:InventoryGUI:VERSION'
}
```

* Gradle (Kotlin DSL)
```kotlin
repositories {
  maven("https://jitpack.io")
}

dependencies {
  compileOnly("com.github.ProjectTL12345:InventoryGUI:VERSION")
}
```
If you use gradle, do not use implementations! This is plugin.

## How to use this API dependency
This api must use kotlin only.
```Kotlin
class TestGui(val plugin: Plugin) {
  fun inventory(player: Player) {
    player.openInventory(
    plugin.gui(InventoryType.CHEST_27, Component.text("TestGUI")) {
      slot(0, ItemStack(Material.GRASS_BLOCK)) {
        player.sendMessage("Hello!")
      }
    })
  }
}
```
P.S. We do not recommend using code styles below InventoryGUI 3.1.1.
