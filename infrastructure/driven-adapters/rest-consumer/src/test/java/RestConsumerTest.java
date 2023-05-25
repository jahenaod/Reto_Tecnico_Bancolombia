import co.com.bancolombia.consumer.ObjectResponse;
import co.com.bancolombia.consumer.RestConsumer;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.ConsumeData;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.exception.CountryNotFoundException;
import co.com.bancolombia.model.retotecnicobancolombiaconsumedata.gateways.ConsumeDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestConsumerTest {

    private OkHttpClient client;
    private ObjectMapper mapper;
    private RestConsumer consumeDataRepository;

    @Mock
    private Environment mockEnv;

    @BeforeEach
    public void setUp() {
        client = Mockito.mock(OkHttpClient.class);
        mapper = Mockito.mock(ObjectMapper.class);
        consumeDataRepository = new RestConsumer(client);
    }

    @Test
    public void getDataCountry_ValidName_ReturnsConsumeData() throws IOException, CountryNotFoundException {
        // GIVEN
        String name = "Colombia";
        String jsonResponse = "[\n" +
                "    {\n" +
                "        \"name\": {\n" +
                "            \"common\": \"Colombia\",\n" +
                "            \"official\": \"Republic of Colombia\",\n" +
                "            \"nativeName\": {\n" +
                "                \"spa\": {\n" +
                "                    \"official\": \"República de Colombia\",\n" +
                "                    \"common\": \"Colombia\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        \"tld\": [\n" +
                "            \".co\"\n" +
                "        ],\n" +
                "        \"cca2\": \"CO\",\n" +
                "        \"ccn3\": \"170\",\n" +
                "        \"cca3\": \"COL\",\n" +
                "        \"cioc\": \"COL\",\n" +
                "        \"independent\": true,\n" +
                "        \"status\": \"officially-assigned\",\n" +
                "        \"unMember\": true,\n" +
                "        \"currencies\": {\n" +
                "            \"COP\": {\n" +
                "                \"name\": \"Colombian peso\",\n" +
                "                \"symbol\": \"$\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"idd\": {\n" +
                "            \"root\": \"+5\",\n" +
                "            \"suffixes\": [\n" +
                "                \"7\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"capital\": [\n" +
                "            \"Bogotá\"\n" +
                "        ],\n" +
                "        \"altSpellings\": [\n" +
                "            \"CO\",\n" +
                "            \"Republic of Colombia\",\n" +
                "            \"República de Colombia\"\n" +
                "        ],\n" +
                "        \"region\": \"Americas\",\n" +
                "        \"subregion\": \"South America\",\n" +
                "        \"languages\": {\n" +
                "            \"spa\": \"Spanish\"\n" +
                "        },\n" +
                "        \"translations\": {\n" +
                "            \"ara\": {\n" +
                "                \"official\": \"جمهورية كولومبيا\",\n" +
                "                \"common\": \"كولومبيا\"\n" +
                "            },\n" +
                "            \"bre\": {\n" +
                "                \"official\": \"Republik Kolombia\",\n" +
                "                \"common\": \"Kolombia\"\n" +
                "            },\n" +
                "            \"ces\": {\n" +
                "                \"official\": \"Kolumbijská republika\",\n" +
                "                \"common\": \"Kolumbie\"\n" +
                "            },\n" +
                "            \"cym\": {\n" +
                "                \"official\": \"Gweriniaeth Colombia\",\n" +
                "                \"common\": \"Colombia\"\n" +
                "            },\n" +
                "            \"deu\": {\n" +
                "                \"official\": \"Republik Kolumbien\",\n" +
                "                \"common\": \"Kolumbien\"\n" +
                "            },\n" +
                "            \"est\": {\n" +
                "                \"official\": \"Colombia Vabariik\",\n" +
                "                \"common\": \"Colombia\"\n" +
                "            },\n" +
                "            \"fin\": {\n" +
                "                \"official\": \"Kolumbian tasavalta\",\n" +
                "                \"common\": \"Kolumbia\"\n" +
                "            },\n" +
                "            \"fra\": {\n" +
                "                \"official\": \"République de Colombie\",\n" +
                "                \"common\": \"Colombie\"\n" +
                "            },\n" +
                "            \"hrv\": {\n" +
                "                \"official\": \"Republika Kolumbija\",\n" +
                "                \"common\": \"Kolumbija\"\n" +
                "            },\n" +
                "            \"hun\": {\n" +
                "                \"official\": \"Kolumbiai Köztársaság\",\n" +
                "                \"common\": \"Kolumbia\"\n" +
                "            },\n" +
                "            \"ita\": {\n" +
                "                \"official\": \"Repubblica di Colombia\",\n" +
                "                \"common\": \"Colombia\"\n" +
                "            },\n" +
                "            \"jpn\": {\n" +
                "                \"official\": \"コロンビア共和国\",\n" +
                "                \"common\": \"コロンビア\"\n" +
                "            },\n" +
                "            \"kor\": {\n" +
                "                \"official\": \"콜롬비아 공화국\",\n" +
                "                \"common\": \"콜롬비아\"\n" +
                "            },\n" +
                "            \"nld\": {\n" +
                "                \"official\": \"Republiek Colombia\",\n" +
                "                \"common\": \"Colombia\"\n" +
                "            },\n" +
                "            \"per\": {\n" +
                "                \"official\": \"جمهوری کلمبیا\",\n" +
                "                \"common\": \"کلمبیا\"\n" +
                "            },\n" +
                "            \"pol\": {\n" +
                "                \"official\": \"Republika Kolumbii\",\n" +
                "                \"common\": \"Kolumbia\"\n" +
                "            },\n" +
                "            \"por\": {\n" +
                "                \"official\": \"República da Colômbia\",\n" +
                "                \"common\": \"Colômbia\"\n" +
                "            },\n" +
                "            \"rus\": {\n" +
                "                \"official\": \"Республика Колумбия\",\n" +
                "                \"common\": \"Колумбия\"\n" +
                "            },\n" +
                "            \"slk\": {\n" +
                "                \"official\": \"Kolumbijská republika\",\n" +
                "                \"common\": \"Kolumbia\"\n" +
                "            },\n" +
                "            \"spa\": {\n" +
                "                \"official\": \"República de Colombia\",\n" +
                "                \"common\": \"Colombia\"\n" +
                "            },\n" +
                "            \"srp\": {\n" +
                "                \"official\": \"Република Колумбија\",\n" +
                "                \"common\": \"Колумбија\"\n" +
                "            },\n" +
                "            \"swe\": {\n" +
                "                \"official\": \"Republiken Colombia\",\n" +
                "                \"common\": \"Colombia\"\n" +
                "            },\n" +
                "            \"tur\": {\n" +
                "                \"official\": \"Kolombiya Cumhuriyeti\",\n" +
                "                \"common\": \"Kolombiya\"\n" +
                "            },\n" +
                "            \"urd\": {\n" +
                "                \"official\": \"جمہوریہ کولمبیا\",\n" +
                "                \"common\": \"کولمبیا\"\n" +
                "            },\n" +
                "            \"zho\": {\n" +
                "                \"official\": \"哥伦比亚共和国\",\n" +
                "                \"common\": \"哥伦比亚\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"latlng\": [\n" +
                "            4.0,\n" +
                "            -72.0\n" +
                "        ],\n" +
                "        \"landlocked\": false,\n" +
                "        \"borders\": [\n" +
                "            \"BRA\",\n" +
                "            \"ECU\",\n" +
                "            \"PAN\",\n" +
                "            \"PER\",\n" +
                "            \"VEN\"\n" +
                "        ],\n" +
                "        \"area\": 1141748.0,\n" +
                "        \"demonyms\": {\n" +
                "            \"eng\": {\n" +
                "                \"f\": \"Colombian\",\n" +
                "                \"m\": \"Colombian\"\n" +
                "            },\n" +
                "            \"fra\": {\n" +
                "                \"f\": \"Colombienne\",\n" +
                "                \"m\": \"Colombien\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"flag\": \"\uD83C\uDDE8\uD83C\uDDF4\",\n" +
                "        \"maps\": {\n" +
                "            \"googleMaps\": \"https://goo.gl/maps/RdwTG8e7gPwS62oR6\",\n" +
                "            \"openStreetMaps\": \"https://www.openstreetmap.org/relation/120027\"\n" +
                "        },\n" +
                "        \"population\": 50882884,\n" +
                "        \"gini\": {\n" +
                "            \"2019\": 51.3\n" +
                "        },\n" +
                "        \"fifa\": \"COL\",\n" +
                "        \"car\": {\n" +
                "            \"signs\": [\n" +
                "                \"CO\"\n" +
                "            ],\n" +
                "            \"side\": \"right\"\n" +
                "        },\n" +
                "        \"timezones\": [\n" +
                "            \"UTC-05:00\"\n" +
                "        ],\n" +
                "        \"continents\": [\n" +
                "            \"South America\"\n" +
                "        ],\n" +
                "        \"flags\": {\n" +
                "            \"png\": \"https://flagcdn.com/w320/co.png\",\n" +
                "            \"svg\": \"https://flagcdn.com/co.svg\",\n" +
                "            \"alt\": \"The flag of Colombia is composed of three horizontal bands of yellow, blue and red, with the yellow band twice the height of the other two bands.\"\n" +
                "        },\n" +
                "        \"coatOfArms\": {\n" +
                "            \"png\": \"https://mainfacts.com/media/images/coats_of_arms/co.png\",\n" +
                "            \"svg\": \"https://mainfacts.com/media/images/coats_of_arms/co.svg\"\n" +
                "        },\n" +
                "        \"startOfWeek\": \"monday\",\n" +
                "        \"capitalInfo\": {\n" +
                "            \"latlng\": [\n" +
                "                4.71,\n" +
                "                -74.07\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "]";
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), jsonResponse);

        RestConsumer rest = new RestConsumer(client);

        Call call = Mockito.mock(Call.class);
        Response response = new Response.Builder()
                .request(new Request.Builder().url("https://localhost:8080/api/Colombia").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build();

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);

        consumeDataRepository.setUrl("https://localhost:8080/api/");

        //THEN
        ConsumeData data = consumeDataRepository.getDataCountry(name);

        // WHEN
        assertNotNull(data);
        assertEquals(1141748.0, data.getArea());
        assertEquals(50882884, data.getPopulation());
        verify(client, times(1)).newCall(any(Request.class));
        verify(call, times(1)).execute();
    }

    @Test
    public void getDataCountry_CountryNotFound_ThrowsCountryNotFoundException() throws IOException {
        // GIVEN
        String name = "NonexistentCountry";
        Call call = Mockito.mock(Call.class);
        Response response = new Response.Builder()
                .request(new Request.Builder().url("https://example.com/NonexistentCountry").build())
                .protocol(Protocol.HTTP_1_1)
                .code(404)
                .message("Not Found")
                .build();

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);

        consumeDataRepository.setUrl("https://localhost:8080/api/");

        // THEN-WHEN
        assertThrows(CountryNotFoundException.class, () -> consumeDataRepository.getDataCountry(name));
        verify(client, times(1)).newCall(any(Request.class));
        verify(call, times(1)).execute();
    }


    @Test
    public void getDataCountry_IOError_ThrowsCountryNotFoundException() throws IOException {
        // GIVEN
        String name = "Colombia";
        Call call = Mockito.mock(Call.class);

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("Connection failed"));

        // THEN - WHEN

    }
}