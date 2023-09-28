package uz.raximov.maroqandtask.payload.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRegionsPerNT {
    private Integer transactionNumber;
    private List<NameItem> regions;
}
