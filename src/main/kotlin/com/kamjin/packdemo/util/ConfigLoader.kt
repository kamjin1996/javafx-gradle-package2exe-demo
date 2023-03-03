package com.kamjin.packdemo.util

import org.slf4j.*
import java.io.*
import java.util.*

/**
 * <p>
 *
 * </p>
 *
 * @author kam
 * @since 2023/03/03
 */
object ExtraConfig {

    private val logger = LoggerFactory.getLogger(ExtraConfig::class.java)

    private val extra = Properties()
    private const val configFileName = "extra.properties"

    init {
        //load config
        loadProperties(extra, configFileName)
    }

    operator fun get(key: String): String? {
        return extra[key] as String?
    }

    /**
     * load properties
     * 首先加载外部jar包同级的外部配置文件，如果不存在则加载classpath下的内部配置文件
     */
    private fun loadProperties(properties: Properties, configFileName: String) {
        val propertiesFile = File(PathUtil.getJarPath() + "\\" + configFileName)
        if (propertiesFile.exists()) {
            logger.info("load outside config: ${propertiesFile.absolutePath}")
            FileInputStream(propertiesFile).use {
                properties.load(it)
            }
        } else {
            logger.info("outside config not exist,load inside config>>>>>>>>>>>>>>>>")
            //使用内部配置文件
            PathUtil.loadPropByFilename(properties, configFileName)
        }
    }
}
