# InventoryGUI
This is Minecraft paper API plugin for Java.

* [API Feature](https://github.com/ProjectTL12345/InventoryGUI#api-feature)
* [API License](https://github.com/ProjectTL12345/InventoryGUI#api-license)
* [How to use this API in server](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-in-server)
* [API Build](https://github.com/ProjectTL12345/InventoryGUI#api-build)
* [How to use this API dependency](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-dependency)

## API Feature
* Simple create inventory gui
* ~~Modular inventory gui with no event declaration required~~ (Coming soon)

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
  jcenter()
  maven {
    url = 'https://jitpack.io'
  }
}

dependencies {
  compileOnly 'com.github.ProjectTL12345:InventoryGUI:VERSION'
}
```

* Gradle (Kotlin DSL)
```kotlin
repositories {
  jcenter()
  maven("https://jitpack.io")
}

dependencies {
  compileOnly("com.github.ProjectTL12345:InventoryGUI:VERSION")
}
```

## How to use this API dependency
### Making inventory gui items

* Java
```Java
public void onExample(Player player) {
    CreateGUI test = new CreateGUI(27, "Test GUI");
    test.setItem(new ItemStack(Material.DIAMOND_SWORD), "Test_Item", 1);
    testGUI.setExitButton(16);

    testGUI.openInventory(player);
}
```

* Groovy
```Groovy
void onExample(Player player) {
    CreateGUI test = new CreateGUI(27, "Test GUI")
    test.setItem(new ItemStack(Material.DIAMOND_SWORD), "Test_Item", 1)
    testGUI.setExitButton(16)

    testGUI.openInventory(player)
}
```

* Kotlin
```Kotlin
fun onExample(player: Player) {
    val test = CreateGUI(27, "Test GUI").let { // You can define another variable
        // Ex) test ->
        it.setItem(ItemStack(Material.DIAMOND_SWORD), "Test_Item", 1)
        it.setExitButton(16)
        
        it // You must return it
    }

    testGUI.openInventory(player);
}
```

* Scala
```Scala
def onExample(player: Player): Unit = {
  val test = new CreateGUI(27, "Test GUI")
  test.setItem(new ItemStack(Material.DIAMOND_SWORD), "Test_Item", 1)
  testGUI.setExitButton(16)

  testGUI.openInventory(player)
}
```
