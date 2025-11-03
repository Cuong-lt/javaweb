package com.javaweb.batdongsan.model.request.image;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageRequest {
    String imageUrl;
    Long propertyId;
}
