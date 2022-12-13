package org.joonzis.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// ContextConfiguration : 스프링 컨테이너를 가져오는 역할. 의존성을 주입하고 Bean을 가져올수 있다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
	
	// data
	// 원래라면 다음과 같은 객체 생성이 필요하지만 지금은 컨테이너에서 생성을 해준다.
	// private DataSource dataSource = new DataSource();
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	
	//테스트용 메소드를 만드려면 @Test 어노테이션 필요
	@Test
	public void testConnection() {
		try (Connection con = dataSource.getConnection()){
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Setter(onMethod_ = @Autowired )
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			log.info(session);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	//@Setter(onMethod_ = @Autowired)
	//private SqlSessionFactory sqlSessionFactory;
	
	
	
}
