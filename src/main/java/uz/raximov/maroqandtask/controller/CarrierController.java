package uz.raximov.maroqandtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.raximov.maroqandtask.payload.NameItem;
import uz.raximov.maroqandtask.payload.auth.CreateCarrierDTO;
import uz.raximov.maroqandtask.payload.response.ApiResult;
import uz.raximov.maroqandtask.service.region.CarrierService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrier")
@RequiredArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;

    @PostMapping("/addCarrier")
    public ApiResult<List<NameItem>> addCarrier(@RequestBody CreateCarrierDTO dto) {
        return ApiResult.successResponse(
                carrierService.create(dto)
        );
    }

    @GetMapping("/getCarriersForRegion/{regionName}")
    public ApiResult<List<NameItem>> getCarriersForRegion(@PathVariable String regionName) {
        return ApiResult.successResponse(
                carrierService.getCarriersForRegion(regionName)
        );
    }
}
