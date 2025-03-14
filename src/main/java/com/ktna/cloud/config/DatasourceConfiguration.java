package com.ktna.cloud.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.bean.LifecycleBean;
import org.noear.solon.core.event.AppPrestopEndEvent;
import org.noear.solon.core.event.EventBus;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

/**
 * com.example.demo.config
 *
 * @author kingdragon 王小龙
 * @since 2025/2/28 16:46
 */
@Slf4j
@Component
@Configuration
public class DatasourceConfiguration implements LifecycleBean {

    @Bean("h2")
    public DataSource dataSource(@Inject("${solon.data-sources.h2}") HikariDataSource ds,
                                 @Inject("${solon.data-sources.h2.tcp-enabled:false}") Boolean tcpEnabled,
                                 @Inject("${solon.data-sources.h2.tcp-port:9092}") String tcpPort,
                                 @Inject("${solon.data-sources.h2.web-enabled:false}") Boolean webEnabled,
                                 @Inject("${solon.data-sources.h2.web-port:8082}") String webPort) throws SQLException {
        ds.getConnection();

        if (tcpEnabled) {
            Server tcpServer = Server.createTcpServer(
                    "-tcp",
                    "-tcpAllowOthers",
                    "-tcpPort",
                    tcpPort
            ).start();
            EventBus.subscribe(AppPrestopEndEvent.class, (event) -> {
                tcpServer.stop();
            });
        }
        if (webEnabled) {
            Server webServer = Server.createWebServer(
                    "-web",
                    "-webAllowOthers",
                    "-webPort",
                    webPort
            ).start();
            EventBus.subscribe(AppPrestopEndEvent.class, (event) -> {
                webServer.stop();
            });
        }

        return ds;
    }

}
