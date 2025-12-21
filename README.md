# 🎨 ColorAPI

**ColorAPI** is a lightweight library for **Minecraft (Spigot/Paper)** plugins that allows you to easily translate legacy color codes (`&`) and **HEX colors** (`#RRGGBB`) with automatic version compatibility.

It is designed to be used as a **dependency**, not as a plugin.

---

## ✨ Features
- Legacy color support (`&`)
- HEX color support (`#ff0000`)
- Supports `List<String>` (lore, messages, etc.)
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
        <version>v1.0.0</version>
    </dependency>
</dependencies>

```
🚀 Usage
Simple text

```java
String message = ColorAPI.translate("&aHello &#ff0000World");
player.sendMessage(message);
```

Lists (lore, multi-line messages)

```java
List<String> lore = ColorAPI.translate(config.getStringList("item.lore"));
itemMeta.setLore(lore);
```

⚠️ Requirements

- Java 8 or higher

- Spigot / Paper

- This library is not a plugin and should not be placed in the /plugins folder


📄 License

Free to use in personal and public projects.
Credits are appreciated if used in published plugins.

👤 Author

Developed by xEliiox
- Discord: .zlex_
