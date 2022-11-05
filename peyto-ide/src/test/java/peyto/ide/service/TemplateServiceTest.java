package peyto.ide.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@AutoConfigureMockMvc
@SpringBootTest
class TemplateServiceTest {

	@Autowired
	private FreemarkerTemplateService freemarkerTemplateService;

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
//		<mapper namespace="${namespace}">
//    	<select id="${selectId}" resultType="${selectResultType}">
//		select * from ${tableName}

		ByteArrayOutputStream	bos = new ByteArrayOutputStream();
		/* Create a data-model */
		Map root = new HashMap();
		root.put("namespace", "jedivin.peyto.dao.api.SampleDao");
		root.put("selectId", "getOne");
		root.put("selectResultType", "HashMap");
		root.put("tableName", "sample_table");
		Writer out = new OutputStreamWriter(bos);
		freemarkerTemplateService.generate("mybatis/basic_mapper.ftlh", root, out);
		System.out.println( ">> " + new String(bos.toByteArray()) );
	}

}