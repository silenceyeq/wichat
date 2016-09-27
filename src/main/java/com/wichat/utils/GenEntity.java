package com.wichat.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;


/**
 * POJO Product
 * 
 * @author Tumi 日期：2012-10-10
 */
public class GenEntity {

	private String tableName;// 表名
	private String time;// 时间
	private String tableComment;// 表注释
	private String tableEntity;// 表注释
	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private String[] colComments; // 列名注释数组
	private int priIndex = 0; // 主键列序号
	private String[] attNames; // 生成类属性数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 数据库连接
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/wichat?serverTimezone=UTC&amp";
	private static final String NAME = "root";
	private static final String PASS = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	// 文件路径
	private static final String path1 = "F:/workspace_test/wichat/src/main/java/com/wichat/entity/";
	private static final String path2 = "F:/workspace_test/wichat/src/main/java/com/wichat/dao/";
	private static final String path3 = "F:/workspace_test/wichat/src/main/java/com/wichat/service/";

	// 文件包名
	private static final String pack1 = "com.wichat.entity";
	private static final String pack2 = "com.wichat.mybatis.mapper";
	private static final String pack3 = "com.wichat.service";

	/*
	 * 构造函数
	 */
	public GenEntity(String table) {
		tableName = table;
		tableEntity = initcap(formatName(table));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		time = sdf.format(new Date());
		// 创建连接
		Connection con;
		String sql1 = "SELECT t.TABLE_COMMENT "
				+ "FROM information_schema.TABLES t " + "WHERE table_name = '"
				+ tableName + "'";
		String sql2 = "SELECT t.COLUMN_NAME,t.DATA_TYPE,t.COLUMN_COMMENT,t.COLUMN_KEY "
				+ "FROM information_schema.COLUMNS t "
				+ "WHERE table_name = '"
				+ tableName + "'";
		System.out.println(sql1);
		System.out.println(sql2);
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			Statement stmt = con.createStatement(); // 创建Statement对象
			System.out.println("成功连接到数据库！");

			ResultSet rs = stmt.executeQuery(sql1);// 创建数据对象
			rs.next();
			tableComment = rs.getString(1);
			rs = stmt.executeQuery(sql2);// 创建数据对象
			rs.last();
			int size = rs.getRow();
			colnames = new String[size];
			colTypes = new String[size];
			colComments = new String[size];
			attNames = new String[size];

			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				colnames[i] = rs.getString(1).toLowerCase();
				attNames[i] = formatName(colnames[i]);
				colTypes[i] = rs.getString(2).toLowerCase();
				colComments[i] = rs.getString(3);
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image")
						|| colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				if ("pri".equalsIgnoreCase(rs.getString(4))) {
					priIndex = i;
				}
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 功能：生成实体类文件
	 * 
	 * @param path
	 */
	private void genEntity() {
		try {
			System.out.println("生成实体类文件begin...");
			String content = parse();
			FileWriter fw = new FileWriter(path1 + tableEntity + ".java");
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
			pw.close();
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse() {
		StringBuffer sb = new StringBuffer();
		processHead(sb);
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + tableComment + " 实体类\r\n");
		sb.append(" * \r\n");
		sb.append(" * @author " + time + "\r\n");
		sb.append(" */\r\n");
		// 实体部分
//		sb.append("@Entity\r\n");
//		sb.append("@Table(name = \"" + tableName + "\")\r\n");
		sb.append("public class " + tableEntity + ""
//				+ " extends DomainObject"
				+ " {\r\n");
		sb.append("\r\n");
		processAllAttrs(sb);// 属性
		sb.append("\r\n");
		processAllMethod(sb);// get set方法
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 功能：生成头信息
	 * 
	 * @param sb
	 */
	private void processHead(StringBuffer sb) {
		sb.append("package " + pack1 + ";\r\n");
		sb.append("\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
			sb.append("\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
			sb.append("\r\n");
		}
//		sb.append("import javax.persistence.Column;\r\n");
//		sb.append("import javax.persistence.Entity;\r\n");
//		sb.append("import javax.persistence.GeneratedValue;\r\n");
//		sb.append("import javax.persistence.Id;\r\n");
//		sb.append("import javax.persistence.Table;\r\n");
//		sb.append("import javax.persistence.Transient;\r\n");
		sb.append("\r\n");
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\t// " + colComments[i] + "\r\n");
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
					+ attNames[i] + ";\r\n");
		}

	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {
//		sb.append("\t@Transient\r\n");
//		sb.append("\t@Override\r\n");
//		sb.append("\tpublic Object getId() {\r\n");
//		sb.append("\t\treturn this." + attNames[priIndex] + ";\r\n");
//		sb.append("\t}\r\n");
		sb.append("\r\n");
		for (int i = 0; i < colnames.length; i++) {
//			sb.append("\t/**\r\n");
//			sb.append("\t * Get " + colComments[i] + "\r\n");
//			sb.append("\t */\r\n");
//			if (i == priIndex) {
//				sb.append("\t@Id\r\n");
//				sb.append("\t@GeneratedValue\r\n");
//			}
//			sb.append("\t@Column(name = \"" + colnames[i] + "\")\r\n");
			sb.append("\t/**\r\n");
			sb.append("\t * Get " + colComments[i] + "\r\n");
			sb.append("\t */\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
					+ initcap(attNames[i]) + "() {\r\n");
			sb.append("\t\treturn " + attNames[i] + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\r\n");
			sb.append("\t/**\r\n");
			sb.append("\t * Set " + colComments[i] + "\r\n");
			sb.append("\t */\r\n");
			sb.append("\tpublic void set" + initcap(attNames[i]) + "("
					+ sqlType2JavaType(colTypes[i]) + " " + attNames[i]
					+ ") {\r\n");
			sb.append("\t\tthis." + attNames[i] + " = " + attNames[i] + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\r\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}
	
	private String toLowerInitial(String str){
		char[] ch = str.toCharArray();
		if(ch[0] >= 'A' && ch[0] <='Z'){
			ch[0] = (char)(ch[0] + 32);
		}
		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("double")
				|| sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")
				|| sqlType.equalsIgnoreCase("longtext")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")
				|| sqlType.equalsIgnoreCase("date")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}

		return null;
	}

	/**
	 * 功能：带下划线字符串 驼峰命名
	 * 
	 * @param str
	 * @return
	 */
	public String formatName(String str) {
		char[] ch = str.toCharArray();
		String re = "";
		int i = 0;
		while (i < ch.length) {
			if ('_' == ch[i]) {
				if (i + 1 < ch.length) {
					if (ch[i + 1] >= 'a' && ch[i + 1] <= 'z') {
						re += (char) (ch[i + 1] - 32);
						i++;
					}
				}
			} else {
				re += ch[i];
			}
			i++;
		}
		return re;
	}

	/**
	 * 功能：生成Dao文件
	 * 
	 */
	private void genDao() {
		try {
			System.out.println("生成Dao文件begin...");
			String contentDao = parseDao();
			FileWriter fwDao = new FileWriter(path2 + tableEntity + "Dao.java");
			PrintWriter pwDao = new PrintWriter(fwDao);
			pwDao.println(contentDao);
			pwDao.flush();
			pwDao.close();

//			String contentDaoImpl = parseDaoImpl();
//			FileWriter fwDaoImpl = new FileWriter(path2 + "" // hibernate/impl/
//					+ tableEntity + "DaoImpl.java");
//			PrintWriter pwDaoImpl = new PrintWriter(fwDaoImpl);
//			pwDaoImpl.println(contentDaoImpl);
//			pwDaoImpl.flush();
//			pwDaoImpl.close();
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：生成Dao主体代码
	 * 
	 * @return
	 */
	private String parseDao() {
		StringBuffer sb = new StringBuffer();
		// 头部信息
		sb.append("package " + pack2 + ";\r\n");
		sb.append("\r\n");
		sb.append("import " + pack1 + "." + tableEntity + ";\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + tableComment + " Dao\r\n");
		sb.append(" * \r\n");
		sb.append(" * @author " + time + "\r\n");
		sb.append(" */\r\n");
		// 实体部分
		sb.append("public interface " + tableEntity + "Dao "
//				+ "extends DaoSupport<" + tableEntity + ">"
				+ " {\r\n");
		sb.append("\r\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 功能：生成Dao实现类主体代码
	 * 
	 * @return
	 */
	private String parseDaoImpl() {
		StringBuffer sb = new StringBuffer();
		// 头部信息
		sb.append("package " + pack2 + ".impl;\r\n");
		sb.append("\r\n");
		sb.append("import org.hibernate.SessionFactory;\r\n");
		sb.append("\r\n");
		sb.append("import " + pack1 + "." + tableEntity + ";\r\n");
		sb.append("import " + pack2 + "." + tableEntity + "Dao;\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + tableComment + " Dao实现类\r\n");
		sb.append(" * \r\n");
		sb.append(" * @author " + time + "\r\n");
		sb.append(" */\r\n");
		// 实体部分
		sb.append("public class " + tableEntity
				+ "DaoImpl "
//				+ "extends HibernateDaoSupportImpl<" + tableEntity + ">"
				+ "\r\n");
		sb.append("\t\timplements " + tableEntity + "Dao {\r\n");
		sb.append("\r\n");
		sb.append("\tpublic " + tableEntity
				+ "DaoImpl(SessionFactory sessionFactory) {\r\n");
		sb.append("\t\tsuper(sessionFactory, " + tableEntity + ".class);\r\n");
		sb.append("\t}\r\n");
		sb.append("\r\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 功能：生成Service文件
	 * 
	 * @param path
	 */
	private void genService() {
		try {
			System.out.println("生成Service文件begin...");
			String contentDao = parseService();
			FileWriter fwDao = new FileWriter(path3 + tableEntity
					+ "Service.java");
			PrintWriter pwDao = new PrintWriter(fwDao);
			pwDao.println(contentDao);
			pwDao.flush();
			pwDao.close();

			String contentDaoImpl = parseServiceImpl();
			FileWriter fwDaoImpl = new FileWriter(path3 + "" + tableEntity // impl/
					+ "ServiceImpl.java");
			PrintWriter pwDaoImpl = new PrintWriter(fwDaoImpl);
			pwDaoImpl.println(contentDaoImpl);
			pwDaoImpl.flush();
			pwDaoImpl.close();
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：生成Service主体代码
	 * 
	 * @return
	 */
	private String parseService() {
		StringBuffer sb = new StringBuffer();
		// 头部信息
		sb.append("package " + pack3 + ";\r\n");
		sb.append("\r\n");
		sb.append("import " + pack1 + "." + tableEntity + ";\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + tableComment + " Service\r\n");
		sb.append(" * \r\n");
		sb.append(" * @author " + time + "\r\n");
		sb.append(" */\r\n");
		// 实体部分
		sb.append("public interface " + tableEntity
				+ "Service"
//				+ " extends ServiceSupport<" + tableEntity + "> "
				+ "{\r\n");
		sb.append("\r\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 功能：生成Service实现类主体代码
	 * 
	 * @return
	 */
	private String parseServiceImpl() {
		StringBuffer sb = new StringBuffer();
		// 头部信息
		sb.append("package " + pack3 + ";\r\n");
		sb.append("\r\n");
		sb.append("import org.springframework.stereotype.Service;");
		sb.append("\r\n");
		sb.append("import " + pack1 + "." + tableEntity + ";\r\n");
		sb.append("import " + pack2 + "." + tableEntity + "Mapper;\r\n");
		sb.append("import " + pack3 + "." + tableEntity + "Service;\r\n");
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * " + tableComment + " Service实现类\r\n");
		sb.append(" * \r\n");
		sb.append(" * @author " + time + "\r\n");
		sb.append(" */\r\n");
		// 实体部分
		sb.append("@Service");
		sb.append("public class " + tableEntity
				+ "ServiceImpl"
//				+ " extends ServiceSupportImpl<" + tableEntity + "> "
				+ " implements\r\n");
		sb.append("\t\t" + tableEntity + "Service {\r\n");
		sb.append("\r\n");
		sb.append("\tprivate " + tableEntity + "Mapper "+toLowerInitial(tableEntity)+"Mapper;\r\n");
		System.out.println(tableEntity);
		sb.append("\r\n");
//		sb.append("\tpublic " + tableEntity + "ServiceImpl(" + tableEntity
//				+ "Dao dao) {\r\n");
//		sb.append("\t\tsuper(dao);\r\n");
//		sb.append("\t\tthis.dao = dao;\r\n");
//		sb.append("\t}\r\n");
		sb.append("\r\n");
		sb.append("}");
		return sb.toString();
	}

	
	/**
	 * 出口
	 * 
	 * @param args
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		 String table = "user";
		 GenEntity ge = new GenEntity(table);
//		 ge.genEntity();
//		 ge.genDao();
		 ge.genService();
//			Properties ps = System.getProperties();
//			Set<String> pname = ps.stringPropertyNames();
//			for (String name : pname) {
//				System.out.println(name + " --------------- " +ps.getProperty(name) );
//			}
//			String rootPath = Thread.currentThread().getClass().getResource("/").getFile().toString();
//			System.out.println(rootPath);
//			
//			String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
//			
//			System.out.println(t);
//			int num=t.indexOf("target");
//
//			String path=t.substring(1,num)+"src/main/java/com/wichat/mybatis";
//			System.out.println(path);
	}

	
}
