package com.carlaospa.openfeign;

import com.carlaospa.openfeign.client.PostClient;
import com.carlaospa.openfeign.dto.PostDTO;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostClientTest {

    private static String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static String RESPONSE_POST = "[{\n" +
            "        \"userId\": 1,\n" +
            "        \"id\": 1,\n" +
            "        \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
            "        \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
            "    }]";
    private PostClient postClient;

    @Test
    public void whenGetPostClientThenReturnOk(){
        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/posts"),
                RESPONSE_POST
        ));

        List<PostDTO> postDTOList = postClient.getAllPosts();

        assertFalse(postDTOList.isEmpty());
        assertThat(postDTOList.get(0).getUserId(), equalTo(1));

    }

    private void builderFeignClient(MockClient mockClient){
        postClient = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(PostClient.class, BASE_URL);
    }
}
