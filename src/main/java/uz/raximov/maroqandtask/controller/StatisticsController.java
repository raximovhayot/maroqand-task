package uz.raximov.maroqandtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.raximov.maroqandtask.payload.response.ApiResult;
import uz.raximov.maroqandtask.payload.statistics.NTPerProduct;
import uz.raximov.maroqandtask.payload.statistics.ScorePerCarrier;
import uz.raximov.maroqandtask.service.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/deliveryRegionsPerNT")
    public ApiResult deliveryRegionsPerNT() {
        return null;
    }

    @GetMapping("/scorePerCarrier")
    public ApiResult<List<ScorePerCarrier>> scorePerCarrier(@RequestParam int minimumScore) {
        return ApiResult.successResponse(statisticsService.scorePerCarrier(minimumScore));
    }

    @GetMapping("/nTPerProduct")
    public ApiResult<List<NTPerProduct>> nTPerProduct() {
        return ApiResult.successResponse(statisticsService.nTPerProduct());
    }
}
