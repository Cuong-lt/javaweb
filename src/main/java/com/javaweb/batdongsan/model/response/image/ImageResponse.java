package com.javaweb.batdongsan.model.response.image;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageResponse {
    Long id;
    String imageUrl;
    Long propertyId;
}
