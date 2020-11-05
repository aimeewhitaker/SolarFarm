package solarfarm.data;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import solarfarm.model.Panel;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PanelJdbcTemplateRepository implements PanelRepository {

    private final JdbcTemplate jdbcTemplate;

    public PanelJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 1. Create a single mapper for all find methods.
    private final RowMapper<Panel> mapper = (resultSet, rowNum) -> {
        Panel panel = new Panel();
        panel.setPanelId(resultSet.getInt("panel_id"));
        panel.setSection(resultSet.getString("section"));
        panel.setRow(resultSet.getInt("row_num"));
        panel.setCol(resultSet.getInt("col_num"));
        panel.setYearInstalled(resultSet.getInt("year_installed"));
        panel.setType(resultSet.getInt("panel_type_id"));
        panel.setTracked(resultSet.getBoolean("is_tracking"));
        return panel;
    };

    @Override
    public List<Panel> findAll(){
        final String sql = "select panel_id, section, row_num, col_num, year_installed, panel_type_id, is_tracking" +
                " from solar_farm;";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Panel findById(int panelId){
        final String sql = "select panel_id, section, row_num, col_num, year_installed," +
            "panel_type_id, is_tracking from solar_farm where panel_id = ?;";
        try {
            // 2. Parameters always follow SQL and mappers.
            // Any number of parameters is allowed.
            return jdbcTemplate.queryForObject(sql, mapper, panelId);
        } catch (EmptyResultDataAccessException ex) {
            // 3. queryForObject can throw an unchecked exception
            // If the ResultSet is empty, it just means the pet with the id wasn't found.
            // So returning null is valid.
            return null;
        }
    }

    @Override
    public List<Panel> findBySection(String section){
        final String sql = "select section, row_num, col_num, year_installed, panel_type_id, is_tracking" +
                "from solar_farm where section = ?;";
        return jdbcTemplate.query(sql, mapper, section);
    }

    @Override
    public Panel add(Panel panel){
        final String sql = "insert into solar_farm (section, row_num, col_num, year_installed," +
            "panel_type_id, is_tracking) values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, panel.getSection());
            ps.setInt(2, panel.getRow());
            ps.setInt(3, panel.getCol());
            ps.setInt(4, panel.getYearInstalled());
            ps.setInt(5, panel.getType());
            ps.setBoolean(6,panel.getTracked());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        return panel;
    }

    @Override
    public boolean update(Panel panel){
        return false;
    }

    @Override
    public boolean remove(int panelId){
        return false;
    }
}
