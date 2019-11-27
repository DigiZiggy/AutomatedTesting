package gm.taltech.ee.weatherwise.payload.dto;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDto {
    private Integer type;
    private Integer id;
    private String country;
    private long sunrise;
    private long sunset;
    private String pod;
}
