package com.hone.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hone.common.config.Config;

/**
 * <p>JDBC处理类</p>
 * @author hourz
 * @since 2018-09-10
 */
public class JdbcUtils {
	// 双重锁模式:是饱汉模式的优化,进行双重判断,当已经创建过实例对象后就无需加锁,提高效率.
	private static JdbcUtils singleton;
	/**
	 * <p>无参构造器</p>
	 */
	private JdbcUtils(){
		
	}
	/**
	 * <p>双重锁模式实现</p>
	 * @return
	 */
	public static JdbcUtils getInstance(){
		if(singleton == null){
			synchronized(JdbcUtils.class){
				if(singleton == null){
					singleton = new JdbcUtils();
				}
			}
		}
		return singleton;
	}
	// 用户名
	public static String user = null;
	// 密码
	public static String password = null;
    // 驱动
	public static String driver = null;
    // URL：包含前缀、IP地址、端口号、数据库名
	public static String url = null;
    // 连接对象
    private Connection conn = null;
    private PreparedStatement statement;
	// 读取配置文件且加载数据库驱动
    static {
        // 读取配置文件
        driver = Config.getInstance().getProperty("datasource.driverClassName");
        url = Config.getInstance().getProperty("datasource.url");
        user = Config.getInstance().getProperty("datasource.username");
        password = Config.getInstance().getProperty("datasource.password");
        // 加载驱动
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // 建立数据库的连接
    public Connection setConn(String newUrl, String newDriver, String newUser,String newPassword){
        try {
        	try {
    			Class.forName(newDriver);
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // 建立数据库的连接
    public Connection getConn(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * <p>结果集封装</p>
     * @param rs 查询结果ResultSet
     * @return 结果集封装JSON
     * @throws SQLException 
     */
    public List<Map<String, Object>> listMapObject(ResultSet resultSet) throws SQLException{
    	ResultSetMetaData setMetaData = resultSet.getMetaData();
		List<Map<String, Object>> resultList = new ArrayList<>();
		while (resultSet.next()) {
			Map<String, Object> result = new HashMap<String, Object>();
			for (int i = 1; i <= setMetaData.getColumnCount(); i++) {
				String columnName = setMetaData.getColumnName(i).toLowerCase();
				Object obj = resultSet.getObject(i);
				result.put(columnName, obj);
			}
			resultList.add(result);
		}
		return resultList;
    }
    public void createStatement(PreparedStatement preparedStatement,String sql) throws SQLException {
		statement = conn.prepareStatement(sql);
	}
    /**
     * <p>查询语句</p>
     * @param sql 
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String,Object>> query(String sql, List<Object> params) throws SQLException {
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultList=listMapObject(resultSet);
        close(resultSet, statement, conn);
		return resultList;
    }
    
    public List<String> queryToDataSource(String sql, List<Object> params) throws SQLException {
    	List<String> resultList = new ArrayList<>();
    	statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultList=null;
        close(resultSet, statement, conn);
		return resultList;
    }
    
/*    // 查询返回List容器
    public List<Map<String,Object>> query(String sql, Object...params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // 获得连接
            conn = getConn();
            // 获得preparedSttement对象进行预编译（？占位符）
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for(Object p : params){
                pst.setObject(paramsIndex++, p);
            }
            // 执行sql语句获得结果集的对象
            rs = pst.executeQuery();
            // 获得结果集中列的信息
            ResultSetMetaData rst = rs.getMetaData();
            // 获得结果集的列的数量
            int column = rst.getColumnCount();
            // 创建List容器
            List<Map<String,Object>> rstList = new ArrayList<Map<String,Object>>();
            // 处理结果
            while(rs.next()){
                // 创建Map容器存取每一列对应的值
                Map<String,Object> m = new HashMap<String,Object>();
                for(int i=1;i<=column;i++){
                    m.put(rst.getColumnName(i), rs.getObject(i));
                }
                // 将Map容器放入List容器中
                rstList.add(m);
            }
            return rstList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally{
            // 关闭资源
            close(rs, pst, conn);
        }
    }*/
    
    // 分页查询总共有多少条记录totleSize
    public long queryLong(String sql,Object...params){
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // 获得连接
            conn = getConn();
            // 获得preparedSttement对象进行预编译（？占位符）
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for(Object p : params){
                pst.setObject(paramsIndex++, p);
            }
            // 执行sql语句获得结果集的对象
            rs = pst.executeQuery();
            while(rs.next()){
                return Long.valueOf(rs.getLong(1));
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    // 插入
    public boolean insert(String sql,Object...params){
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // 获得连接
            conn = getConn();
            // 获得PrepareStatement对象进行预编译
            pst = conn.prepareStatement(sql);
            // 处理将数据插入占位符
            int paramsIndex = 1;
            for(Object p : params){
                pst.setObject(paramsIndex++, p);
            }
            // 执行sql语句
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            // 关闭资源
            close(rs, pst, conn);
        }
    }
    
    // 修改
    public boolean update(String sql,Object...params){
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            // 获得连接
            conn = getConn();
            // 获得PrepareStatement对象进行预编译
            pst = conn.prepareStatement(sql);
            // 处理将数据插入占位符
            int paramsIndex = 1;
            for(Object p : params){
                pst.setObject(paramsIndex++, p);
            }
            // 执行sql语句
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            // 关闭资源
            close(rs, pst, conn);
        }
    }
    
    /**
     * <p>删除操作</p>
     * @param sql
     * @param params
     * @return
     */
    public boolean delete(String sql,Object...params){
        PreparedStatement pst = null;
        try {
            // 获得连接
            conn = getConn();
            // 获得PrepareStatement对象进行预编译
            pst = conn.prepareStatement(sql);
            // 处理将数据插入占位符
            int paramsIndex = 1;
            for(Object p : params){
                pst.setObject(paramsIndex++, p);
            }
            // 执行sql语句
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            // 关闭资源
            close(null, pst, conn);
        }
    }
    
    /**
     * <p>关闭资源</p>
     * @param rs 结果集
     * @param pst 声明
     * @param conn 连接
     */
    public void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection conn){
        if(resultSet != null){
            try {
            	resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if(preparedStatement!=null){
            try {
            	preparedStatement.close();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
            preparedStatement = null;
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    
    
    
    public PreparedStatement getStatement() {
		return statement;
	}
	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}

	
	public static void main(String[] args) throws SQLException {
    	//JdbcUtils jdbc = new JdbcUtils();
    	String newUrl = "jdbc:mysql://localhost:3306/cas";
    	String newDriver = "com.mysql.jdbc.Driver";
    	String newUser = "root";
    	String newPassword = "123456";
    	JdbcUtils.getInstance().setConn(newUrl, newDriver, newUser, newPassword);
    	
		String sql ="SELECT * FROM rwgl_cjxx";
		List<Map<String, Object>> resultList = JdbcUtils.getInstance().query(sql,null);
		for(Map<String, Object> map : resultList) {
			for(String key:map.keySet()) {
				System.out.println("键："+key+"；值："+map.get(key));
			}
		}
	}
}
