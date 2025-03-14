package com.ktna.cloud.component;

import com.baomidou.mybatisplus.extension.ddl.IDdl;
import lombok.SneakyThrows;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * com.ktna.cloud.component
 *
 * @author kingdragon 王小龙
 * @since 2025/3/3 13:55
 */
@Component
public class DDLInitProvider implements IDdl {

    @Inject("h2")
    private DataSource dataSource;

    @Init
    public void init() throws SQLException {
        dataSource.getConnection();
    }

    @Override
    public void runScript(Consumer<DataSource> consumer) {
        consumer.accept(dataSource);
    }

    @Override
    @SneakyThrows
    public List<String> getSqlFiles() {

        return Collections.singletonList(
                "classpath:db/V1.0__initTable.sql"
        );
    }

}
