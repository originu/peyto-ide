package peyto.ide.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import peyto.ide.dto.TableInfo;

@AutoConfigureMockMvc
@SpringBootTest
class SqlGenerationServiceTest {

	@Autowired
	private SqlGenerationService sqlGenerationService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void contextLoads() {
	}

	@Test
	void test() throws Exception {
		TableInfo tableInfo = new TableInfo();
		tableInfo.setTableCatalog("postgres");
		tableInfo.setTableSchema("information_schema");
		tableInfo.setTableName("tables");
		
		tableInfo.setNamespace("jedivin.peyto.dao.api.SampleDao");
		tableInfo.setSelectId("getItems");
		tableInfo.setSelectResultType("HashMap");
		
		byte[] raw = sqlGenerationService.generateByTableName(tableInfo);
		System.out.println(new String(raw));

	}

}
