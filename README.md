# InventoryGUI
This is Minecraft paper API plugin for Java.

* [API Feature](https://github.com/ProjectTL12345/InventoryGUI#api-feature)
* [API License](https://github.com/ProjectTL12345/InventoryGUI#api-license)
* [How to use this API in server](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-in-server)
* [API Build](https://github.com/ProjectTL12345/InventoryGUI#api-build)
* [How to use this API dependency](https://github.com/ProjectTL12345/InventoryGUI#how-to-use-this-api-dependency)

## API Feature
* Simple create inventory gui
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
If you use gradle, do not use implementations! This is plugin.

## How to use this API dependency
### Making inventory gui items

* Java
```Java
public void onExample(Player player, Plugin plugin) {
    CreateGUI test = new CreateGUI(27, Component.text("Test GUI"), plugin);
    test.setItem(new ItemStack(Material.DIAMOND_SWORD), Component.text("Test_Item"), 1);
    testGUI.setExit(Component.text("Test GUI"), 16);

    testGUI.addEvent(new AddListener(plugin) {
        @Override 
        @EventHandler 
        public void onEvent(InventoryClickEvent event) {
            if (event.getView().title().equals(inventoryName)) {
                if (event.getCurrentItem().getItemMeta().displayName().equals(Component.text("Test_Item"))) {
                    event.setCancelled(true);
                }
            }
        }
    });

    testGUI.openInventory(player);
}
```

* Groovy
```Groovy
void onExample(Player player, Plugin plugin) {
    CreateGUI test = new CreateGUI(27, Component.text("Test GUI"), plugin)
    test.setItem(new ItemStack(Material.DIAMOND_SWORD), Component.text("Test_Item"), 1)
    testGUI.setExit(Component.text("Test GUI"), 16)

    testGUI.addEvent(new AddListener(plugin) {
        @Override
        @EventHandler
        void onEvent(InventoryClickEvent event) {
            if (event.getView().title().equals(inventoryName)) {
                if (event.getCurrentItem().getItemMeta().displayName().equals(Component.text("Test_Item"))) {
                    event.setCancelled(true)
                }
            }
        }
    })

    testGUI.openInventory player
}
```

* Kotlin
```Kotlin
fun onExample(player: Player, plugin: Plugin) {
    val test = CreateGUI(27, "Test GUI", plugin).let { gui -> // You can define another variable
        gui.setItem(ItemStack(Material.DIAMOND_SWORD), Component.text("Test_Item"), 1)
        gui.setExit(Component.text("Test GUI"), 16)

        gui.addEvent(AddListener(plugin) {
            @EventHandler
            override fun onEvent(event: InventoryClickEvent) {
                if (event.view.title() == inventoryName) {
                    if (event.currentItem().itemMeta().displayName() == Component.text("Test_Item")) {
                        event.isCancelled = true
                    }
                }
            }
        })
        
        gui // You must return this function
    }

    testGUI.openInventory(player)
}
```

* Scala
```Scala
def onExample(player: Player, plugin: Plugin): Unit = {
  val test = new CreateGUI(27, Component.text("Test GUI"), plugin)
  test.setItem(new ItemStack(Material.DIAMOND_SWORD), Component.text("Test_Item"), 1)
  testGUI.setExit(Component.text("Test GUI"), 16)

  testGUI.addEvent(new AddListener(plugin) {
    @EventHandler
    override def onEvent(event: InventoryClickEvent): Unit = {
      if (event.getView.title == inventoryName) {
        if (event.getCurrentItem.getItemMeta.displayName == Component.text("Test_Item")) {
          event.setCancelled(true)
        }
      }
    }
  })

  testGUI.openInventory(player)
}
```
P.S. We do not recommend using code styles below InventoryGUI 2.0.0.