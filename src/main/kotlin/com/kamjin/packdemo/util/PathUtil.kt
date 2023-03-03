package com.kamjin.packdemo.util

import org.slf4j.*
import java.io.*
import java.net.*
import java.nio.charset.*
import java.util.*

/**
 * <p>
 *
 * </p>
 *
 * @author kam
 * @since 2023/03/01
 */
object PathUtil {

    private val log = LoggerFactory.getLogger(PathUtil::class.java)

    /**
     * 获取项目加载类的根路径
     * @return
     */
    fun getJarPath(): String {
        var path = ""
        try {
            val location: URL = PathUtil::class.java.protectionDomain.codeSource.location //获得当前的URL
            val file = File(location.path) //构建指向当前URL的文件描述符
            path = if (file.isDirectory) { //如果是目录,指向的是包所在路径，而不是文件所在路径
                file.absolutePath //直接返回绝对路径
            } else { //如果是文件,这个文件指定的是jar所在的路径(注意如果是作为依赖包，这个路径是jvm启动加载的jar文件名)
                file.parent //返回jar所在的父路径
            }
            log.info("jar path=$path")
        } catch (e: Exception) {
            log.error("获取路径错误", e)
        }
        return path
    }

    /**
     * 根据文件名加载为属性文件
     *
     * @param prop
     * @param fileName
     * @throws IOException
     */
    fun loadPropByFilename(prop: Properties, fileName: String?) {
        prop.load(
            InputStreamReader(
                Objects.requireNonNull(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(fileName)
                ),
                StandardCharsets.UTF_8
            )
        )
    }
}