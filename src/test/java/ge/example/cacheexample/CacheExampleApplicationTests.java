package ge.example.cacheexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CacheExampleApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	@Qualifier("cacheServiceImpl")
	private CacheService cacheService;

	@Autowired
	@Qualifier("cacheServiceImpl2")
	private CacheService cacheService2;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testFirstCacheManagerGet() throws Exception{
		int value = 2;
		cacheService.putInteger(value);

		MvcResult result = mockMvc.perform(get("/api/get?value="+value).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("success "+value);

		result = mockMvc.perform(get("/api/get2?value="+value).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("Not found");

	}

	@Test
	public void testFirstCacheManagerPut() throws Exception{
		int value = 2;
		cacheService.putInteger(value);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/put?value="+value).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("success");
	}


	@Test
	public void testSecondCacheManagerGet() throws Exception{
		int value = 4;
		cacheService2.putInteger(value);

		MvcResult result = mockMvc.perform(get("/api/get2?value="+value).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("success "+value);

		result = mockMvc.perform(get("/api/get?value="+value).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("Not found");

	}


	@Test
	public void testSecondCacheManagerPut() throws Exception{
		int value = 2;
		cacheService2.putInteger(value);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/put2?value="+value).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("success");
	}




}
