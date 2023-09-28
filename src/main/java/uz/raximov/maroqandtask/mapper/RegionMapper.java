package uz.raximov.maroqandtask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.raximov.maroqandtask.domain.region.Region;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    NameItem toNameItem(Region region);

    List<NameItem> toNameItemCollection(List<Region> regions);
}
