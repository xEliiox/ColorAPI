# 🎨 ColorAPI
**ColorAPI** is a lightweight library for **Minecraft (Spigot/Paper)** plugins that allows you to easily translate legacy color codes (`&`) and **HEX colors** (`#RRGGBB` / `&#RRGGBB`) with automatic version compatibility.
It is designed to be used as a **dependency**, not as a plugin.

---

## ✨ Features
- Legacy color support (`&a`, `&c`, etc.)
- HEX color support in both `#RRGGBB` and `&#RRGGBB` formats
- Supports `List<String>` (lore, messages, etc.)
- In-place list translation for memory efficiency
- Automatic HEX detection (only enabled on 1.16+ servers)
- Lightweight and easy to use
- Java 8+

---

## 📦 Installation (Maven)
Add JitPack to your `pom.xml`:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.xEliiox</groupId>
        <artifactId>ColorAPI</artifactId>
        <version>2.0.5</version>
    </dependency>
</dependencies>
```

Then shade it into your plugin:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.4.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

---

## 🚀 Usage

### Import
```java
import io.github.xeliiox.colorapi.ColorAPI;
```

### Simple text
```java
String message = ColorAPI.translate("&aHello #ff0000World");
player.sendMessage(message);
```

### Both HEX formats are supported
```java
ColorAPI.translate("&aFormat 1: #FF5555red");   // #RRGGBB
ColorAPI.translate("&aFormat 2: &#FF5555red");  // &#RRGGBB
```

### Lists (lore, multi-line messages)
```java
List<String> lore = ColorAPI.translate(config.getStringList("item.lore"));
itemMeta.setLore(lore);
```

### In-place list translation (memory efficient)
```java
List<String> lore = config.getStringList("item.lore");
ColorAPI.translateInPlace(lore);
itemMeta.setLore(lore);
```

### Check HEX support at runtime
```java
if (ColorAPI.isHexSupported()) {
    // Server is 1.16+
}
```

---

## ⚠️ Requirements
- Java 8 or higher
- Spigot / Paper
- This library is not a plugin and should not be placed in the `/plugins` folder

---

## 🔄 Changelog

### v2.0.5
- Added `&#RRGGBB` HEX format support
- Fixed `group(2)` bug in HEX pattern matching
- Simplified version check logic
- Added `Matcher.quoteReplacement` for safe regex replacement
- Added `translateInPlace(List<String>)` method
- Added `isHexSupported()` method
- Improved Javadoc

### v1.0.0
- Initial release

---

## 📄 License
Free to use in personal and public projects.
Credits are appreciated if used in published plugins.

---

## 👤 Author
Developed by **xEliiox**
- Discord: `.zlex_`
