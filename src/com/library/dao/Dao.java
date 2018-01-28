package com.library.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.library.model.*;



public class Dao {
	
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://localhost:3306/db_library?characterEncoding=utf-8";
	protected static String dbUser = "root";
	protected static String dbPwd = "root";
	protected static String second = null;
	private static Connection conn = null;
	
	private Dao() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
			else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}
	
	private static ResultSet executeQuery(String sql) {
		try {
			if(conn==null)
			new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	private static int executeUpdate(String sql) {
		
		try {
			if(conn==null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());				
			return -1;
		} finally {
		}
	}
	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	
	/*
	 * 用户登录方法
	 */
	public static Operator check(String id, String password) {
		int i = 0;
		Operator operater=new Operator();
		String sql = "select *  from tb_operator where id='" + id
				+ "' and password='" + password +"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				operater.setId(rs.getString("id"));
				operater.setName(rs.getString("name"));
				operater.setAuthority(rs.getString("authority"));
				operater.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return operater;		
	}
	
	//查询某个级别的用户有多少个
	public static List findnumber(String authority) {
		List list=new ArrayList();
		String sql = "select *  from tb_operator where authority='" + authority + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Operator operator = new Operator();
				operator.setId(rs.getString("id"));	
				list.add(operator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;		
	}
	
	//根据id查询某个读者
	public static Reader findReader(String id) {
		int i = 0;
		Reader reader = new Reader();
		String sql = "select *  from tb_reader where id='" + id + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				reader.setId(rs.getString("id"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setGrade(rs.getString("grade"));
				reader.setIdcard(rs.getString("idcard"));
				reader.setMax(rs.getString("max"));
				reader.setStart(rs.getDate("start"));
				reader.setEnd(rs.getDate("end"));
				reader.setTel(rs.getString("tel"));
				reader.setMoney(rs.getDouble("money"));
				reader.setCity(rs.getString("city"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return reader;		
	}
	
	/*
	 * 查询类别方法
	 */
	public static List selectBookCategory() {
		List list=new ArrayList();
		String sql = "select *  from tb_bookType";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType bookType=new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setName(rs.getString("name"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}
	
	//根据名称查询图书类别
	public static List selectBookCategory(String name) {
		List list=new ArrayList();
		String sql = "select * from tb_bookType where name='"+name+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type=new BookType();
				type.setDays(rs.getString("days"));
				type.setId(rs.getString("id"));
				type.setFk(rs.getString("fk"));
				type.setName(rs.getString("name"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;		
	}

	//增加图书类别
	public static int InsertBookType(String bookTypeName,String days,Double fk){
		int i=0;
		try{
			String sql="insert into tb_bookType(name,days,fk) values('"+bookTypeName+"','"+days+"',"+fk+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	//更新图书类别
	public static int UpdatebookType(String id,String typeName,String days,String fk){
		int i=0;
		try{
			String sql="update tb_bookType set name='"+typeName+"',days='"+days+"',fk='"+fk+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
//	public static int DelbookType(String id){
//		int i=0;
//		try{
//			String sql="delete from tb_bookType where id='"+id+"'";
//			//System.out.println(sql);
//			i=Dao.executeUpdate(sql);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		Dao.close();
//		return i;
//	}
	
	//取每种书超过规定时间罚款金额
	public static List selectBookTypeFk(String bookType) {
		List list=new ArrayList();
		String sql = "select *  from tb_bookType where id='"+bookType+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type=new BookType();
				type.setFk(rs.getString("fk"));
				type.setDays(rs.getString("days"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
		
	}

	//增加图书
	public static int Insertbook(String id,String typeId,String writer,String translator,String publisher,Date date,Double price,String name,String num){
		int i=0;
		try{
			String sql="insert into tb_bookInfo(id,typeId,writer,translator,publisher,date,price,name,num,rest) values('"+id+"','"+typeId+"','"+writer+"','"+translator+"','"+publisher+"','"+date+"','"+price+"','"+name+"','"+num+"','"+num+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	
	//按书籍编号查询书籍
	public static BookInfo selectBookInfo(String id) {
		BookInfo bookinfo = new BookInfo();
		String sql = "select *  from tb_bookInfo where id='"+id+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				bookinfo.setId(rs.getString("id"));
				bookinfo.setTypeId(rs.getString("typeId"));
				bookinfo.setName(rs.getString("name"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setDate(rs.getDate("date"));
				bookinfo.setPrice(rs.getDouble("price"));
				bookinfo.setRest(rs.getString("rest"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return bookinfo;
	}
	
	/*
	 * 修改图书信息方法
	 */
	public static int updatebook(String id,String typeId,String name,String writer,String translator,String publisher,Date date,Double price,String num){
		int i=0;
		try{
			String sql="update tb_bookInfo set id='"+id+"',typeId='"+typeId+"',name='"+name+"',writer='"+writer+"',translator='"+translator+"',publisher='"+publisher+"',date='"+date+"',price="+price+",num='"+num+"' where id='"+id+"'";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int borrowBook(String bookId,String rest,String readerId, String num){
		int i=0;
		try{
			String sql="update tb_bookInfo set rest='"+rest+"' where id='"+bookId+"'";
			i=Dao.executeUpdate(sql);
			String sql2="update tb_reader set max='"+num+"' where id='"+readerId+"'";
			i=Dao.executeUpdate(sql2);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
//	/*
//	 * 删除图书信息方法
//	 */
//	public static int Delbook(String ISBN){
//		int i=0;
//		try{
//			String sql="delete from tb_bookInfo where ISBN='"+ISBN+"'";
//			//System.out.println(sql);
//			i=Dao.executeUpdate(sql);
//		}catch(Exception e){
//			e.printStackTrace();
//			
//		}
//		Dao.close();
//		return i;
//	}

	//增加新的读者
	public static int InsertReader(String id,String name,String sex,String idcard,String age,String grade,Date start,Date end,String max,String tel,Double money,String city){
		int i=0;
		try{
			String sql="insert into tb_reader(id, name, sex, idcard, age, grade, start, end, max, tel, money, city) values('"+id+"','"+name+"','"+sex+"','"+idcard+"','"+age+"','"+grade+"','"+start+"','"+end+"','"+max+"','"+tel+"','"+money+"','"+city+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//增加新的读者
	public static int InsertReader(String id,String name,String sex,String idcard,String age,String grade,String tel){
		int i=0;
		try{
			String sql="insert into tb_reader(id, name, sex, idcard, age, grade, tel) values('"+id+"','"+name+"','"+sex+"','"+idcard+"','"+age+"','"+grade+"','"+tel+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//查询读者
	public static List countOperator(String authority) {
		List list=new ArrayList();
		String sql = "select *  from tb_reader where id like '"+authority+"%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setId(rs.getString("id"));
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdcard(rs.getString("idcard"));
				reader.setStart(rs.getDate("start"));
				reader.setEnd(rs.getDate("end"));
				reader.setTel(rs.getString("tel"));
				reader.setMoney(rs.getDouble("money"));
				reader.setGrade(rs.getString("grade"));
				reader.setMax(rs.getString("max"));
				reader.setCity(rs.getString("city"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//根据查询读者
	public static Reader selectReader(String readerId) {
		Reader reader = new Reader();
		String sql = "select *  from tb_reader where id='"+readerId+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()) {
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setGrade(rs.getString("grade"));
				reader.setIdcard(rs.getString("idcard"));
				reader.setStart(rs.getDate("start"));
				reader.setMax(rs.getString("max"));
				reader.setTel(rs.getString("tel"));
				reader.setMoney(rs.getDouble("money"));
				reader.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return reader;
	}
	
	//更新读者信息
	public static int updateReader(String id,String name,String sex,String age,
			String grade,String idcard,String tel){
		int i=0;
		try{
			String sql="update tb_reader set id = '"+id+"', name='"+name+"',sex='"+sex+"',age='"+age+"', grade = '"+grade+"', idcard='"+idcard+"',tel='"+tel+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//更新读者信息
	public static int updateReader(String id,String age,String grade,String tel){
		int i=0;
		try{
			String sql="update tb_reader set age='"+age+"', grade = '"+grade+"',tel='"+tel+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//更新读者信息
	public static int updateReader(String id,String name,String sex,String idcard, 
			String age, String grade,Date start, Date end,String maxNum,String tel,
			Double money, String city){
		int i=0;
		try{
			String sql="update tb_reader set id = '"+id+"', name='"+name+"',sex='"+sex+"',age='"+age+"', grade = '"+grade+"', idcard='"+idcard+"',end='"+end+"',max='"+maxNum+"',tel='"+tel+"',money='"+money+"',city='"+city+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	//删除读者
	public static int delReader(String id){
		int i=0;
		try{
			String sql="delete from tb_reader where id='"+id+"'";
			Dao.executeUpdate(sql);
			sql="delete from tb_operator where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
/*
 * 对订购信息表操作
 */
	public static int InsertBookOrder(String ISBN,Date date,String number,String operator,String checkAndAccept,Double zk){
		int i=0;
		try{
			String sql="insert into tb_order(ISBN,date,number,operator,checkAndAccept,zk) values('"+ISBN+"','"+date+"','"+number+"','"+operator+"',"+checkAndAccept+",'"+zk+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}

	/*
	 * 对借阅表进行操作
	 */
	public static int InsertBookBorrow(String bookId,String readerId,Timestamp borrowDate,Timestamp backDate, String operator, String status){
		int i=0;
		try{
			String sql;
			if(backDate != null) {
				sql="insert into tb_borrow(bookId,readerId,borrowDate,backDate,operatorName1,status)values('"+bookId+"','"+readerId+"','"+borrowDate+"','"+backDate+"','"+operator+"','"+status+"')";
			}
			else {
				sql="insert into tb_borrow(bookId,readerId,borrowDate,backDate,operatorName1,status)values('"+bookId+"','"+readerId+"','"+borrowDate+"',null,'"+operator+"','"+status+"')";
			}
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;		
	}
	
	public static List selectBorrow(String readerId) {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow left outer join tb_bookinfo on bookId = id where readerId ='"+readerId+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setBookId(rs.getString("bookId"));
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setStart(rs.getDate("borrowDate"));
				borrow.setEnd(rs.getDate("backDate"));
				borrow.setName(rs.getString("name"));
				borrow.setStatus(rs.getString("status"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static List selectBorrow(String readerId,String bookId) {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow left outer join tb_bookinfo on bookId = id where readerId ='"+readerId+"' and bookId ='"+bookId+"' and not 'status'='已归还' and not `status`='预约中'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setBookId(rs.getString("bookId"));
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setStart(rs.getDate("borrowDate"));
				borrow.setEnd(rs.getDate("backDate"));
				borrow.setName(rs.getString("name"));
				borrow.setStatus(rs.getString("status"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	//查询还书内容，tb_bookinfo tb_reader tb_borrow之间的查询
	public static List selectBookBack(String readerId) {
		List list=new ArrayList();
		String sql = "SELECT a.id AS bookId, a.name AS bookName, a.typeId AS bookType, b.borrowDate, b.backDate, c.name AS readerName, c.id AS readerId, `status` FROM tb_bookInfo a INNER JOIN tb_borrow b ON a.id = b.bookId INNER JOIN tb_reader c ON b.readerId = c.id WHERE (c.id = '"+readerId+"' and `status`!='已归还')";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Back back=new Back();
				back.setBookId(rs.getString("bookId"));
				back.setBookName(rs.getString("bookName"));
				back.setBorrowDate(rs.getString("borrowDate"));
				back.setBackDate(rs.getString("backDate"));
				back.setReaderName(rs.getString("readerName"));
				back.setReaderId(rs.getString("readerId"));
				back.setStatus(rs.getString("status"));
				back.setBookType(rs.getString("bookType"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int updateBookBack(String bookId,String readerId,String operator){//归还图书操作
		int i=0;
		try{
			String sql="update tb_borrow set status='已归还',operatorName2='"+operator+"' where bookId='"+bookId+"'and readerId='"+readerId+"' and not status='已归还'";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;		
	}
	
	//续借图书操作
	public static int renewBookBorrow(String bookId,String readerId,String date){
		int i=0;
		try{
			String sql="update tb_borrow set backDate='"+date+"'"+",`status`='等待归还' where bookId='"+bookId+"'and readerId='"+readerId+"' AND NOT `status` ='已归还'" ;
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;		
	}
	
	
	//查询所有图书信息
	public static List selectbooksearch() {
		List list=new ArrayList();
		String sql = "select *  from tb_bookInfo";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setId(s.getString("id"));
				bookinfo.setTypeId(s.getString("typeId"));
				bookinfo.setName(s.getString("name"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setDate(s.getDate("date"));
				bookinfo.setPrice(s.getDouble("price"));
				bookinfo.setNum(s.getString("num"));
				bookinfo.setRest(s.getString("rest"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	//通过书名查询
	public static List selectbookmohu(String bookname){
		List list=new ArrayList();
		String sql="select * from tb_bookInfo where name like '%"+bookname+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setId(s.getString("id"));
				bookinfo.setTypeId(s.getString("typeId"));
				bookinfo.setName(s.getString("name"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setDate(s.getDate("date"));
				bookinfo.setPrice(s.getDouble("price"));
				bookinfo.setNum(s.getString("num"));
				bookinfo.setRest(s.getString("rest"));
				list.add(bookinfo);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;		
	}
	
	//通过作者查询
	public static List selectbookmohuwriter(String writer){
		List list=new ArrayList();
		String sql="select * from tb_bookInfo where writer like '%"+writer+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setId(s.getString("id"));
				bookinfo.setTypeId(s.getString("typeId"));
				bookinfo.setName(s.getString("name"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setDate(s.getDate("date"));
				bookinfo.setPrice(s.getDouble("price"));
				bookinfo.setNum(s.getString("num"));
				bookinfo.setRest(s.getString("rest"));
				list.add(bookinfo);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;		
	}
	
	//通过图书类别查询
	public static List selectbookmohutype(String type){
		List list=new ArrayList();
		String sql="select * from tb_bookinfo LEFT JOIN tb_booktype on tb_bookinfo.typeId = tb_booktype.id where tb_booktype.`name` LIKE '%"+type+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setId(s.getString("id"));
				bookinfo.setTypeId(s.getString("typeId"));
				bookinfo.setName(s.getString("name"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setDate(s.getDate("date"));
				bookinfo.setPrice(s.getDouble("price"));
				bookinfo.setNum(s.getString("num"));
				bookinfo.setRest(s.getString("rest"));
				list.add(bookinfo);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;		
	}
	
	//新增用户
	public static int InsertOperator(String id,String name,String authority,String password){
		int i=0;
		try{
			String sql="insert into tb_operator(id,name,authority,password) values('"+id+"','"+name+"',"+authority+",'"+password+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static List selectuser() {
		List list=new ArrayList();
		String sql = "select * from tb_operator as a INNER JOIN tb_reader as b ON a.id=b.id  where  b.id like 'L%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Librarian librarian=new Librarian();
				librarian.setId(rs.getString("id"));
				librarian.setName(rs.getString("name"));
				librarian.setSex(rs.getString("sex"));
				librarian.setAge(rs.getString("age"));
				librarian.setGrade(rs.getString("grade"));
				librarian.setIdCard(rs.getString("idcard"));
				librarian.setTel(rs.getString("tel"));
				librarian.setPassword(rs.getString("password"));
				list.add(librarian);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int Deluser(String id){
		int i=0;
		try{
			String sql="delete from tb_operator where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int Updatepass(String password,String name){
		int i=0;
		try{
			String sql="update tb_operator set password='"+password+"' where name='"+name+"'";
			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int updateOperator(String id,String name){
		int i=0;
		try{
			String sql="update tb_operator set name='"+name+"' where id='"+id+"'";			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static int updateOperator(String id,String name,String password){
		int i=0;
		try{
			String sql="update tb_operator set name='"+name+"',id ='"+id+"', password='"+password+"' where id='"+id+"'";			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static String getNews(){
		String sql="select * from tb_news";	
		String result="";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				result = rs.getString("news");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return result;
	}
	
	public static int setNews(String news){
		int i=0;
		try{
			String sql="update tb_news set news='"+news+"'";			
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	public static List selectOrder(String readerId) {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow left outer join tb_bookinfo on bookId = id where readerId ='"+readerId+"' and status='预约中'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setBookId(rs.getString("bookId"));
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setStart(rs.getDate("borrowDate"));
				borrow.setEnd(rs.getDate("backDate"));
				borrow.setName(rs.getString("name"));
				borrow.setStatus(rs.getString("status"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static List selectOrderred(String bookId) {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow left outer join tb_bookinfo on bookId = id where bookId ='"+bookId+"' and status='预约中'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setBookId(rs.getString("bookId"));
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setStart(rs.getDate("borrowDate"));
				borrow.setEnd(rs.getDate("backDate"));
				borrow.setName(rs.getString("name"));
				borrow.setStatus(rs.getString("status"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static List selectOrderred() {
		List list=new ArrayList();
		String sql = "select *  from tb_borrow left outer join tb_bookinfo on bookId = id where status='预约中'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Borrow borrow=new Borrow();
				borrow.setBookId(rs.getString("bookId"));
				borrow.setReaderId(rs.getString("readerId"));
				borrow.setStart(rs.getDate("borrowDate"));
				borrow.setName(rs.getString("name"));
				borrow.setStatus(rs.getString("status"));
				list.add(borrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
	public static int deleteOrder(String readerId, String bookId) {
		int i=0;
		try{
			String sql="delete from tb_borrow  where bookId='"+bookId+"'and readerId='"+readerId+"' AND `status` ='预约中'" ;
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;	
	}
	
	public static int renewOrder(String readerId, String bookId,String date) {
		int i=0;
		try{
			String sql="update tb_borrow set borrowDate='"+date+"'"+",`status`='等待归还' where bookId='"+bookId+"'and readerId='"+readerId+"' AND `status` ='预约中'" ;
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;	
	}
}