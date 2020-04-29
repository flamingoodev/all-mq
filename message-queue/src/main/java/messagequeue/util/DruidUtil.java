package messagequeue.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/23 14:45
 */
public class DruidUtil {

    private static DataSource dataSource;

    // 加载配置文件并创建好一个连接池对象
    static {
        Properties ps = new Properties();
        try {
            ps.load(DruidUtil.class.getClassLoader().getResourceAsStream("data-source-mysql.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     *
     * @return Connection
     * @throws SQLException SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 获取连接池对象
     *
     * @return DataSource
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 归还连接对象，有结果集的连接（查询）
     *
     * @param preparedStatement PreparedStatement
     * @param connection        Connection
     * @param resultSet         ResultSet
     */
    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 归还连接对象，无结果集的连接（查询）
     *
     * @param preparedStatement PreparedStatement
     * @param con               Connection
     */
    public static void close(PreparedStatement preparedStatement, Connection con) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement = null;
            }
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
