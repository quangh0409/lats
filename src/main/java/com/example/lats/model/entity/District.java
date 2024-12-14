package com.example.lats.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "DISTRICT")
public class District extends BaseClass<District>{
    @Id
    Long districtId;
    String districtName;
    Integer population;
    Double area;
    String coordinates;
    @JsonProperty("coordinates_")
    transient Coordinates coordinates_;


    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
        // Chuyển đổi từ String sang Coordinates
        this.coordinates_ = parseCoordinates(coordinates);
    }

    // Getter cho Coordinates_
    public Coordinates getCoordinates_() {
        if (coordinates_ == null && coordinates != null) {
            // Lazy loading nếu chưa chuyển đổi
            this.coordinates_ = parseCoordinates(coordinates);
        }
        return coordinates_;
    }

    // Hàm hỗ trợ chuyển từ JSON String sang đối tượng Coordinates
    private Coordinates parseCoordinates(String coordinatesJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(coordinatesJson, Coordinates.class);
        } catch (Exception e) {
            return null; // Hoặc throw RuntimeException nếu cần
        }
    }
}
