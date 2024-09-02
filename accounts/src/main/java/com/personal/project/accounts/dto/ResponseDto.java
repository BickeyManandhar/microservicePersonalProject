package com.personal.project.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information."
)
public class ResponseDto {

    @Schema(
            description = "Status code in the response.",
            example = "200" //providing example is not always a good idea , as it will show 200 for all the status code in the API documentation
    )
    private String statusCode;

    @Schema(
            description = "Status code in the response.",
            example = "Request processed successfully."
    )
    private String statusMessage;
}
