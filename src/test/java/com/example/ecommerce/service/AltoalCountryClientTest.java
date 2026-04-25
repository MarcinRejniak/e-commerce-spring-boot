package com.example.ecommerce.service;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.webclient.altoal.AltoalCountryClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class AltoalCountryClientTest {

    private static final String BASE_URL = "https://countries.altoal.com/api/v1/";
    private AltoalCountryClient client;
    private MockRestServiceServer server;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder().baseUrl(BASE_URL);

        server = MockRestServiceServer.bindTo(builder).build();

        client = new AltoalCountryClient(builder.build());
    }

    @Test
    void should_return_mapped_countries_when_api_responds_with_success() {

//        given
        Map<String, Object> mockResponse = Map.of(
                "countries", Map.of(
                        "poland", Map.of("name","Poland"),
                        "germany", Map.of("name", "Germany")
                        )
        );
        String json = objectMapper.writeValueAsString(mockResponse);

        server.expect(requestTo(BASE_URL + "metadata.json"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

//        when
        List<CountryDto> result = client.getCountries();

//        then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(CountryDto::getName)
                .containsExactlyInAnyOrder("Poland", "Germany");
    }

    @Test
    void should_return_mapped_states_when_api_responds_with_success() {

//        given
        Map<String, Object> mockResponse = Map.of(
                "data", Map.of(
                        "government", Map.of(
                                "administrative_divisions", Map.of(
                                        "value", Map.of(
                                                "provinces", List.of(
                                                        Map.of("string", "Lodzkie (Lodz)")
                                                        )
                                        )
                                )
                        )
                )
        );
        String json = objectMapper.writeValueAsString(mockResponse);

        server.expect(requestTo(BASE_URL + "name/poland.json"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

//        when
        List<StateDto> result = client.getStates("poland");

//        then
        assertThat(result).hasSize(1);
        assertThat(result).extracting(StateDto::getName).contains("Lodzkie (Lodz)");
    }

    @Test
    void should_return_empty_list_when_api_returns_404() {

//        given
        server.expect(requestTo(BASE_URL + "name/disneyland.json"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

//        when
        List<StateDto> result = client.getStates("disneyland");

//        then
        assertThat(result).isEmpty();
    }

    @Test
    void should_return_empty_list_when_json_is_missing_fields() {

//        given
        Map<String, Object> mockResponse = Map.of("data", Map.of());
        String json = objectMapper.writeValueAsString(mockResponse);

        server.expect(requestTo(BASE_URL + "name/poland.json"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

//        when
        List<StateDto> result = client.getStates("poland");

//        then
        assertThat(result).isEmpty();
    }
}
