package peyto.ide.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import peyto.ide.data.DaoData;
import peyto.ide.data.DtoData;

@AutoConfigureMockMvc
@SpringBootTest
class DaoGenerationServiceTest {

	@Autowired
	private DaoGenerationService daoGenerationService;

	@Test
	void testGenerateDao() throws Exception {
		
		DaoData daoInfo = new DaoData();
		daoInfo.setPackageName("peyto.ide.dao.api");
		daoInfo.setClassName("SampleDao");
		daoInfo.setSelectId("getItems");
		daoInfo.setReturnType("Map<String,Object>");

		byte[] raw = daoGenerationService.generateDao(daoInfo);
		System.out.println(new String(raw));
	}

	@Test
	void testGenerateDaoTest() throws Exception {
		
		DaoData daoInfo = new DaoData();
		daoInfo.setPackageName("peyto.ide.dao.api");
		daoInfo.setClassName("SampleDao");
		daoInfo.setSelectId("getItems");
		daoInfo.setReturnType("Map<String,Object>");
		
		byte[] raw = daoGenerationService.generateDaoTest(daoInfo);
		System.out.println(new String(raw));
	}

	@Test
	void testGenerateDto() throws Exception {
		DtoData dtoInfo = new DtoData();
//		dtoInfo.setPackageName("peyto.ide.dao.api");
//		dtoInfo.setClassName("SampleDao");
//		dtoInfo.setSelectId("getItems");
//		dtoInfo.setReturnType("Map<String,Object>");
		
		dtoInfo.setTableCatalog("postgres");
		dtoInfo.setTableSchema("information_schema");
		dtoInfo.setTableName("tables");
		dtoInfo.setPackageName("peyto.ide.dto");
		
		byte[] raw = daoGenerationService.generateDto(dtoInfo);
		System.out.println(new String(raw));
	}

}
