import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories
class DatabaseConfig(
    @Value("\${spring.datasource.url}") private val dbUrl: String,
    @Value("\${spring.datasource.username}") private val username: String,
    @Value("\${spring.datasource.password}") private val password: String,
    @Value("\${spring.datasource.hikari.maximum-pool-size}") private val maxPoolSize: Int,
    @Value("\${spring.datasource.hikari.minimum-idle}") private val minIdle: Int,
    @Value("\${spring.datasource.hikari.idle-timeout}") private val idleTimeout: Long,
    @Value("\${spring.datasource.hikari.max-lifetime}") private val maxLifetime: Long,
    @Value("\${spring.datasource.hikari.connection-timeout}") val connectionTimeout: Long,
    @Value("\${spring.datasource.hikari.leak-detection-threshold}") private val leakDetectionThreshold: Long,
    @Value("\${spring.datasource.driver-class-name}") private val driverClassName: String,
) {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = dbUrl
        dataSource.username = username
        dataSource.password = password
        dataSource.maximumPoolSize = maxPoolSize
        dataSource.minimumIdle = minIdle
        dataSource.idleTimeout = idleTimeout
        dataSource.maxLifetime = maxLifetime
        dataSource.connectionTimeout = connectionTimeout
        dataSource.leakDetectionThreshold = leakDetectionThreshold
        dataSource.driverClassName = driverClassName
        return dataSource
    }
}