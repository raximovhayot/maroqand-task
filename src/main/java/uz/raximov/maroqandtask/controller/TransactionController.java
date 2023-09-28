package uz.raximov.maroqandtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.raximov.maroqandtask.payload.response.ApiResult;
import uz.raximov.maroqandtask.payload.transaction.CreateOfferDTO;
import uz.raximov.maroqandtask.payload.transaction.CreateRequestDTO;
import uz.raximov.maroqandtask.payload.transaction.CreateTransactionDTO;
import uz.raximov.maroqandtask.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/addRequest")
    public ApiResult<Void> addRequest(@RequestBody CreateRequestDTO dto) {
        transactionService.addRequest(dto);
        return ApiResult.successResponse();
    }

    @PostMapping("/addOffer")
    public ApiResult<Void> addOffer(@RequestBody CreateOfferDTO dto) {
        transactionService.addOffer(dto);
        return ApiResult.successResponse();
    }

    @PostMapping("/addTransaction")
    public ApiResult<Void> addTransaction(@RequestBody CreateTransactionDTO dto) {
        transactionService.addTransaction(dto);
        return ApiResult.successResponse();
    }

    @PutMapping("/evaluateTransaction/{transactionId}")
    public ApiResult<Boolean> evaluateTransaction(@PathVariable String transactionId, @RequestParam int score) {
        return ApiResult.successResponse(
                transactionService.evaluateTransaction(score, transactionId)
        );
    }
}