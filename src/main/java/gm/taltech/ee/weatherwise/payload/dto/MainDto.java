package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@Getter
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDto {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Double temp_min;
    private Double temp_max;
    private Double sea_level;
    private Double grnd_level;
    private Double temp_kf;
}
