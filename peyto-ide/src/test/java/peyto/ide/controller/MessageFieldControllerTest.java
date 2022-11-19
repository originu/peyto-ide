package peyto.ide.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import peyto.ide.data.AddMessageFieldData;

@AutoConfigureMockMvc
@SpringBootTest
class MessageFieldControllerTest {

	@Autowired
	private MockMvc mockMvc;

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
	void testAddMessageField() throws Exception {
		ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
		long messageId = 1;

		AddMessageFieldData dto1 = new AddMessageFieldData();
		dto1.setMessageId(messageId);
		dto1.setMessageFieldOrder(1);
		dto1.setMessageFieldName("name");
		dto1.setMessageFieldDescription("this is a name");
		dto1.setMessageFieldDataType("String");
		dto1.setMessageFieldLength(255);

		AddMessageFieldData dto2 = new AddMessageFieldData();
		dto2.setMessageId(messageId);
		dto2.setMessageFieldOrder(2);
		dto2.setMessageFieldName("email");
		dto2.setMessageFieldDescription("this is an email");
		dto2.setMessageFieldDataType("String");
		dto2.setMessageFieldLength(255);

		AddMessageFieldData dto3 = new AddMessageFieldData();
		dto3.setMessageId(messageId);
		dto3.setMessageFieldOrder(3);
		dto3.setMessageFieldName("nick");
		dto3.setMessageFieldDescription("this is a nick");
		dto3.setMessageFieldDataType("int");
		dto3.setMessageFieldLength(8);

		ObjectMapper MAPPER = new ObjectMapper();
		arrayNode.add(MAPPER.convertValue(dto1, ObjectNode.class));
		arrayNode.add(MAPPER.convertValue(dto2, ObjectNode.class));
		arrayNode.add(MAPPER.convertValue(dto3, ObjectNode.class));

		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.post(String.format("/api/message/%s/messagefield", messageId))
						.content(arrayNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testGetItemsByMessageId() throws Exception {
		long messageId = 1;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get(String.format("/api/message/%s/messagefield", messageId)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testRemoveByMessageId() throws Exception {
		long messageId = 1;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.delete(String.format("/api/message/%s/messagefield", messageId)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

}