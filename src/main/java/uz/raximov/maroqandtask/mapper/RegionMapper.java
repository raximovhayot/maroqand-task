package uz.raximov.maroqandtask.mapper;

import org.mapstruct.Mapper;
import uz.raximov.maroqandtask.domain.region.Region;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    NameItem toNameItem(Region region);

    List<NameItem> toNameItemCollection(List<Region> regions);
}
