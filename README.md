# InventoryGUI
- This is minecraft paper library for kotlin

## Library Feature
- DSL Script style
- Support Listener in code

## Library License
Library used open source license General Public License 3.0
- License: [InventoryGUI Library]()

## How to use
### Write build script
You can use this library with jitpack and Maven or Gradle!

- maven
```xml
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

- Gradle (Groovy DSL)

```groovy
repositories {
    maven "https://jitpack.io/"
}

dependencies {
    implementation "com.github.ProjectTL12345:InventoryGUI:VERSION"
}
```

<br/><br/>
If you want use Kotlin DSL follow this code!

- Gradle (Kotlin DSL)
```kotlin
repositories {
    maven("https://jitpack.io/")
}

dependencies {
    implementation("com.github.ProjectTL12345:InventoryGUI:VERSION")
}
```

### Writing code
Example is here []()