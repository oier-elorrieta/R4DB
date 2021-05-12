package ModeloBBDD;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPool {
    
    private final String DB="ethazi4";
    private final String URL="jdbc:mysql://localhost:33060/"+DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER="root";
    private final String PASS="elorrieta";
    
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource=null;
    
    private ConnectionPool(){
     
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        basicDataSource.setMinIdle(1);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(100);
        basicDataSource.setMaxWaitMillis(500);
        
    }
    
    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
        } 
        return dataSource;
    }

    public Connection getConnection() throws SQLException{
      return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }    
    
}

