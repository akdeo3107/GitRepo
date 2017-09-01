package biz.neustar.utils;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;

import biz.neustar.base.DriverFactory;
import biz.neustar.settings.ObjectRepo;
import biz.neustar.settings.ReadConfig;
import biz.neustar.settings.XMLDatabase;
import biz.neustar.test.uiExceptions.DLBaseException;

import org.apache.commons.lang3.StringUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class DBUtils extends XMLDatabase{

	private static Logger _log = Logger.getLogger(DBUtils.class);

	public static final void cleanupDBResounces(final Connection connection,
			final Statement statment, final ResultSet resultset) {

		if (resultset != null) {
			try {
				resultset.close();
				DBUtils._log.debug("Closing Resultset : successful ");
			} catch (final SQLException e) {
				DBUtils._log.error("Closing Resultset : failed ", e);
			}
		}

		if (statment != null) {
			try {
				statment.close();
				DBUtils._log.debug("Closing PreparedStatement : successful");
			} catch (final SQLException e) {
				DBUtils._log.error("Closing PreparedStatement : failed", e);
			}
		}

		if (connection != null) {
			try {
				connection.close();
				DBUtils._log.debug("Closing Connection completed : successful");

			} catch (final SQLException e) {
				DBUtils._log.error("Closing Connection : failed", e);
			}

		}
	}
	/**
	 * This method used to get count from db taking query as a parameter
	 * @param sql
	 * @return
	 */
	public int getResultCount(String sql) {
		int count = 0;
		DataSource dataSource = null;

		try {
			try {
				dataSource = DBUtils.fetchDBConnectionPool();
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DLBaseException dl) {
			dl.printStackTrace();

		} catch (PropertyVetoException pv) {
			pv.printStackTrace();

		}
		Connection con = null;
		//PreparedStatement ps = null;
		ResultSet rs = null;
		if(dataSource != null){
			try {
				con = dataSource.getConnection();
				rs = con.prepareStatement(sql).executeQuery();
				

				if(rs.next()){
					count = rs.getInt(1);
					
					System.out.println("count :"+count);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

		return count;
	}

	/**
	 * Takes in properties as argument and returns the connection pool object.
	 * The properties accepted can be loaded from a properties file that in turn
	 * is loaded from a the location provided in the Job context.
	 * 
	 * @param properties
	 * @return
	 * @throws DLBaseException
	 * @throws PropertyVetoException
	 * @throws XPathExpressionException 
	 */
	public static final ComboPooledDataSource fetchDBConnectionPool()
			throws DLBaseException, PropertyVetoException, XPathExpressionException {
		ComboPooledDataSource connectionPool = null;
		ObjectRepo.dbreader = new XMLDatabase(DriverFactory.envType, ReadConfig.platformname);
		final String dbName = ObjectRepo.dbreader.getDbUrl();
		final String dbUserName = ObjectRepo.dbreader.getDbUserName();
		final String dbPassword = ObjectRepo.dbreader.getDbPassword();
		final String minConnPoolSize = ObjectRepo.dbreader.getDbMinPoolSize();
		final String maxConnPoolSize = ObjectRepo.dbreader.getDbMaxPoolSize();

		final String dbDriverClass = ObjectRepo.dbreader.getDbDriver();
		final String dbConnectionTimeout = ObjectRepo.dbreader.getDbConnectionTimeout();

		connectionPool = new ComboPooledDataSource();

		if (StringUtils.isNotBlank(dbConnectionTimeout)) {
			connectionPool.setUnreturnedConnectionTimeout(Integer
					.parseInt(dbConnectionTimeout));
		}
		connectionPool.setDriverClass(dbDriverClass); // loads the jdbc driver
		connectionPool.setJdbcUrl(dbName);
		connectionPool.setUser(dbUserName);
		connectionPool.setPassword(dbPassword);
		// the settings below are optional -- c3p0 can work with defaults
		connectionPool.setMinPoolSize(Integer.parseInt(minConnPoolSize));
		connectionPool.setMaxPoolSize(Integer.parseInt(maxConnPoolSize));

		return connectionPool;
	}


	
	
}
