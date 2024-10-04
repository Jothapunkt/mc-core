import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.7.2"
  id("xyz.jpenilla.run-paper") version "2.3.0" // Adds runServer and runMojangMappedServer tasks for testing
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1" // Generates plugin.yml based on the Gradle config
}

group = "com.jothapunkt.spigot"
version = "1.0.0-SNAPSHOT"
description = "Core library"

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 11 installed for example.
  toolchain.languageVersion = JavaLanguageVersion.of(21)
}

// 1)
// For >=1.20.5 when you don't care about supporting spigot
// paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

// 2)
// For 1.20.4 or below, or when you care about supporting Spigot on >=1.20.5
// Configure reobfJar to run when invoking the build task
tasks.assemble {
  dependsOn(tasks.reobfJar)
}

repositories {
    mavenCentral()
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
  paperweight.paperDevBundle("1.21-R0.1-SNAPSHOT")
  implementation("com.comphenix.protocol:ProtocolLib:5.1.0")
  implementation("com.comphenix.packetwrapper:PacketWrapper:1.13-R0.1-SNAPSHOT")
}

tasks {
  compileJava {
    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release = 21
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }

  // Only relevant when going with option 2 above
  reobfJar {
    // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
    // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
    outputJar = layout.buildDirectory.file("../../server/plugins/raftcraft-${project.version}.jar")
  }
}

// Configure plugin.yml generation
// - name, version, and description are inherited from the Gradle project.
bukkitPluginYaml {
  main = "com.jothapunkt.spigot.raftcraft.RaftCraft"
  load = BukkitPluginYaml.PluginLoadOrder.POSTWORLD
  authors.add("Jothapunkt")
  apiVersion = "1.21"

  commands {
    register("flotsam") {
      description = "Spawn flotsam"
      usage = "/flotsam"
      permission = "raftcraft.flotsam"
    }
    register("raft") {
      description = "Manage rafts"
      usage = "/raft"
      permission = "raftcraft.raft"
    }
    register("worlds") {
      description = "Manage worlds"
      usage = "/worlds"
      permission = "raftcraft.worlds"
    }
    register("dungeons") {
      description = "Manage dungeons"
      usage = "/dungeons"
      permission = "raftcraft.dungeons"
    }
    register("mounts") {
      description = "Spawn and manage mounts"
      usage = "/mounts"
      permission = "raftcraft.mounts"
    }
    register("skills") {
      description = "Manage skills"
      usage = "/skills"
      permission = "raftcraft.skills"
    }
    register("spawn") {
      description = "Spawn creatures"
      usage = "/spawn"
      permission = "raftcraft.spawn"
    }
    register("recipes") {
      description = "Manage recipes"
      usage = "/recipes"
      permission = "raftcraft.recipes"
    }
    register("inspect") {
      description = "Inspect data"
      usage = "/inspect"
      permission = "raftcraft.inspect"
    }
    register("tinker") {
      description = "Tinker with items"
      usage = "/tinker"
      permission = "raftcraft.tinker"
    }
    register("equipment") {
      description = "Manage equipment"
      usage = "/equipment"
      permission = "raftcraft.equipment"
    }
    register("skilltree") {
      description = "Manage skilltrees"
      usage = "/skilltree"
      permission = "raftcraft.skilltree"
    }
  }
}
