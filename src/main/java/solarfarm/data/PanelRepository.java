package solarfarm.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import solarfarm.model.Panel;

import javax.sql.DataSource;
import java.util.List;

public interface PanelRepository {

/*    default DataSource initDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        // 2. connection string is:
        // [db-tech]:[db-vendor]://[host]:[port]/[database-name]
        result.setUrl("jdbc:mysql://localhost:3306/solar_farm");
        // 3. username
        result.setUser("root");
        // 4. password
        result.setPassword("top-secret-password");
        return result;
    }*/

    List<Panel> findAll();

    Panel findById(int panelId);

    List<Panel> findBySection(String section);

    Panel add(Panel panel);

    boolean update(Panel panel);

    boolean remove(int panelId);
}
