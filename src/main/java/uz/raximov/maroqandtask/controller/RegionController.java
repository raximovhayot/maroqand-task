package uz.raximov.maroqandtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.raximov.maroqandtask.payload.region.CreateRegionDTO;
import uz.raximov.maroqandtask.payload.response.ApiResult;
import uz.raximov.maroqandtask.service.region.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/addRegion")
    public ApiResult<List<String>> addRegion(@RequestBody CreateRegionDTO dto) {
        return ApiResult.successResponse(
                regionService.create(dto)
        );
    }
}
