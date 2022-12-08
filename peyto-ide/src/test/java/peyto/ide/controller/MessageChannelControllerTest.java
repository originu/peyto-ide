package peyto.ide.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import peyto.ide.data.AddMessageChannelData;
import peyto.ide.data.UpdateMessageChannelData;

@AutoConfigureMockMvc
@SpringBootTest
class MessageChannelControllerTest {

	ObjectMapper MAPPER = new ObjectMapper();
	
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
	void testAddMessageChannel() throws Exception {
		AddMessageChannelData data = new AddMessageChannelData();
		data.setMessageChannelName("frontend-backend");
		data.setMessageChannelDescription("");
		ObjectNode objectNode = MAPPER.convertValue(data, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/message-channel")
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testGetMessageChannels() throws Exception {
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/message-channel?applicationId=1"));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testUpdateMessageChannel() throws Exception {
		long messageChannelId = 1;
		UpdateMessageChannelData data = new UpdateMessageChannelData();
		data.setMessageChannelId(messageChannelId);
		data.setMessageChannelName("client-server");
		data.setMessageChannelDescription("");
		ObjectNode objectNode = MAPPER.convertValue(data, ObjectNode.class);
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.put(String.format("/api/message-channel/%s", messageChannelId))
						.content(objectNode.toString())
						.contentType(MediaType.APPLICATION_JSON));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	void testRemoveMessageChannel() throws Exception {
		long messageChannelId = 1;
		ResultActions perform = mockMvc.perform(
				MockMvcRequestBuilders.delete(String.format("/api/message-channel/%s",  messageChannelId)));
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		String contentAsString = response.getContentAsString();
		System.out.println(contentAsString);
	}

}