package com.inditex.ecomerce;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcomerceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class EcomerceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:test-json-results/*")
    Resource[] expectedResponses;

    @Test
    void priceRateCorrect() throws Exception {
        List<MultiValueMap<String, String>> testCasesQueryParams = getTestCasesQueryParams();
        Iterator<MultiValueMap<String, String>> iterator = testCasesQueryParams.iterator();
        for (Resource response : expectedResponses) {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                            .get("/prices/{productId}", 35455L)
                            .queryParams(iterator.next())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
            JSONObject jsonResult = new JSONObject(result.getResponse().getContentAsString());
            JSONObject jsonExpected = new JSONObject(new String(Files.readAllBytes(response.getFile().toPath())));
            Assertions.assertEquals(jsonExpected.toString(), jsonResult.toString());
        }
    }

    private List<MultiValueMap<String, String>> getTestCasesQueryParams() {
        List<MultiValueMap<String, String>> testCasesQueryParams = new ArrayList<>();

        MultiValueMap<String, String> q1 = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> q2 = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> q3 = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> q4 = new LinkedMultiValueMap<>();
        MultiValueMap<String, String> q5 = new LinkedMultiValueMap<>();
        q1.add("date", "2020-06-14-10.00.00");
        q1.add("brandId", "1");
        testCasesQueryParams.add(q1);

        q2.add("date", "2020-06-14-16.00.00");
        q2.add("brandId", "1");
        testCasesQueryParams.add(q2);

        q3.add("date", "2020-06-14-21.00.00");
        q3.add("brandId", "1");
        testCasesQueryParams.add(q3);

        q4.add("date", "2020-06-15-10.00.00");
        q4.add("brandId", "1");
        testCasesQueryParams.add(q4);

        q5.add("date", "2020-06-16-21.00.00");
        q5.add("brandId", "1");
        testCasesQueryParams.add(q5);

        return testCasesQueryParams;
    }

}
