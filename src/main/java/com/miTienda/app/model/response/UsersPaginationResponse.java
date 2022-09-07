package com.miTienda.app.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersPaginationResponse {
    public List<UserDetailsResponse> users;
    public String prev;
    public String nxt;
}
