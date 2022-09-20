package com.miTienda.app.model.response;


import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationResponse {
    List<?> entities;
    String prevPageURI;
    String nextPageURI;
}