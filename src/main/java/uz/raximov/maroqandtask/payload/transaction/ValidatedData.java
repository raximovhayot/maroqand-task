package uz.raximov.maroqandtask.payload.transaction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.domain.transaction.Offer;
import uz.raximov.maroqandtask.domain.transaction.Request;

import java.util.Set;

@Getter
@Setter
@Builder
public class ValidatedData {
    private Offer offer;
    private Request request;
    private Carrier carrier;
    private Set<Long> regionIds;
}
