ackage com.travel.travelplanner.mapper;

import com.travel.travelplanner.dto.HotelDto;
import com.travel.travelplanner.entity.City;
import com.travel.travelplanner.entity.Hotel;
import com.travel.travelplanner.entity.Trip;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "trip.id", target = "tripId")
    HotelDto toDto(Hotel hotel);

    @Mapping(source = "cityId", target = "city.id")
    @Mapping(source = "tripId", target = "trip.id")
    Hotel toEntity(HotelDto dto);

    List<HotelDto> toDtoList(List<Hotel> list);
}
