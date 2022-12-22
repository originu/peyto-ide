package peyto.ide.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.node.ObjectNode;

import peyto.ide.core.model.ManifestModel;
import peyto.ide.core.util.JsonUtil;

class YamlTest {

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
	void test() {
		try {
			Map<String, Object> propMap = new Yaml().load(new FileReader("./test-resources/entity_base.yaml"));
			ManifestModel convertValue = JsonUtil.MAPPER.convertValue(propMap, ManifestModel.class);
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Test
	void test2() {
		Yaml yaml = new Yaml();
		try {
			ManifestModel loadAs = yaml.loadAs(new FileReader("./test-resources/entity_base.yaml"), ManifestModel.class);
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
