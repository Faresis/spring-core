package ua.dp.mign.service;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.dp.mign.model.Event;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBEventLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    public DBEventLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() throws SQLException {
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
        ResultSet t_event = metaData.getTables(null, "ME", "T_EVENT", null);
        if (!t_event.next()) {
            jdbcTemplate.execute("create table t_event(ID BIGINT, MSG VARCHAR(500), PRIMARY KEY (ID))");
        }
    }

    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO t_event (id, msg) VALUES (?, ?)",
                event.getId(),
                event.getMessage()
        );
    }
}
