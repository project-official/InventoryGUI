# InventoryGUI
This is Minecraft paper API plugin for Java.

* [API Feauture](https://github.com/ProjectTL12345/InventoryGUI#api-feature)
* [API License](https://github.com/ProjectTL12345/InventoryGUI#api-license)
* [How to use this API in server](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-in-server)
* [API Build](https://github.com/ProjectTL12345/InventoryGUI#api-build)
* [How to use this API dependency](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-dependency)

## API Feature
* Making inventory gui Items
* Exit inventory listener

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

* Gradle
```groovy
repositories {
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
public void onExample() {
  Inventory inventory = Bukkit.createInventory(null, 27, "Test GUI");
  AddGuiItem setItem = new AddGuiItem(); // Please define this class!
  
  setItem.onCreateItem(
    inventory, // Get your own inventory
    26, // Item Location
    Material.BARRIER, // Item Type
    ChatColor.GREEN + "Test", // Item Name
    Arrays.asList(ChatColor.BLUE + "This is Real Test."), // Item Lore
    1, // Item Amount
    false // Set Enchantment false or true
  );
}
```
  
* Kotlin
```Java
fun onExample() {
  val inventory = Bukkit.createInventory(null, 27, "Test GUI")
  val setItem = AddGuiItem() // Please define this class!
  
  setItem.onCreateItem(
    inventory, // Get your own inventory
    26, // Item Location
    Material.BARRIER, // Item Type
    "${ChatColor.GREEN}Test", // Item Name
    Arrays.asList("${ChatColor.BLUE}This is Real Test."), // Item Lore
    1, // Item Amount
    false // Set Enchantment false or true
  )
}
```

* Scala
```Scala
def onExample(): Unit = {
  val inventory = Bukkit.createInventory(null, 27, "Test GUI")
  val setItem = new AddGuiItem() // Please define this class!
  
  setItem.onCreateItem(
    inventory, // Get your own inventory
    26, // Item Location
    Material.BARRIER, // Item Type
    s"${ChatColor.GREEN}Test", // Item Name
    Arrays.asList(s"${ChatColor.BLUE}This is Real Test."), // Item Lore
    1, // Item Amount
    false // Set Enchantment false or true
  )
}
```

### Exit inventory listener

* Java
```Java
public void onExample() {
  Inventory inventory = Bukkit.createInventory(null, 27, "Test GUI");
  AddGuiItem setItem = new AddGuiItem(); // Please define this class!
  --- // Ignore it
  
  setItem.onCreateExitButton(inventory, 26);
}
```
  
* Kotlin
```Java
fun onExample() {
  val inventory = Bukkit.createInventory(null, 27, "Test GUI")
  val setItem = AddGuiItem() // Please define this class!
  --- // Ignore it
  
  setItem.onCreateExitButton(inventory, 26)
}
```

* Scala
```Scala
def onExample(): Unit = {
  val inventory = Bukkit.createInventory(null, 27, "Test GUI")
  val setItem = new AddGuiItem() // Please define this class!
  --- // Ignore it
  
  setItem.onCreateExitButton(inventory, 26)
}
```
