//package path.path.path

// simply register saveDefaultConfig() in your onEnable()
// to reference simply create a constructor to your main class and use plugin.getConfig().(option)

// @author voltration
// MIT License

import gg.hecate.xyz.XYZ
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.logging.Level

object Configuration {
  
    private var dataConfig: FileConfiguration? = null
    private var configFile: File? = null


    private fun reloadConfig() {
        if (configFile == null) configFile = File(plugin?.dataFolder, "config.yml")
        dataConfig = YamlConfiguration.loadConfiguration(configFile!!)
        val defaultStream = plugin?.getResource("config.yml")
        if (defaultStream != null) {
            val defaultConfig = YamlConfiguration.loadConfiguration(InputStreamReader(defaultStream))
            (dataConfig as YamlConfiguration).setDefaults(defaultConfig)
        }
    }

    private val config: FileConfiguration?
        get() {
            if (dataConfig == null) reloadConfig()
            return dataConfig
        }

    fun saveConfig() {
        if (dataConfig == null || configFile == null) return
        try {
            config!!.save(configFile!!)
        } catch (e: IOException) {
            plugin?.logger?.log(Level.SEVERE, "Can not save $e")
        }
    }

    private fun saveDefaultConfig() {
        if (configFile == null) configFile = File(plugin?.dataFolder, "config.yml")
        if (!configFile!!.exists()) {
            plugin?.saveResource("config.yml", false)
        }
    }

}
