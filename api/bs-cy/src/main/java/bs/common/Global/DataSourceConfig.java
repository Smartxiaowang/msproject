package bs.common.Global;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//@Configuration
public class DataSourceConfig {


    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
                .type(DruidDataSource.class)
                .build();
    }

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .type(DruidDataSource.class)
                .build();
    }

}

