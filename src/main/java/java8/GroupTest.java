package java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 分组测试类
 *
 * @author Young
 */
public class GroupTest {

    public static void main(String[] args) {
        List<Bill> source = getData();
        List<Bill> dest = new ArrayList<>();

        Map<Integer, Map<Integer, Map<Integer, List<Bill>>>> groups = source.stream().collect(
                //分组1
                Collectors.groupingBy(Bill::getBillingCycleId,
                        //分组2
                        Collectors.groupingBy(Bill::getResellerId,
                                //分组3
                                Collectors.groupingBy(Bill::getAmountType))));
        //遍历分组,聚合处理
        groups.forEach((k1, v1) ->
                v1.forEach((k2, v2) ->
                        v2.forEach((k3, v3) -> {
                            Long sum = 0L;
                            //聚合数据
                            for (Bill bill : v3) {
                                if (!Objects.isNull(bill.getAmount())) {
                                    sum += bill.getAmount();
                                }
                            }
                            dest.add(Bill.builder().billingCycleId(k1).resellerId(k2).
                                    amountType(k3).amount(sum).build());
                        })));

        dest.forEach(System.out::println);

    }

    static List<Bill> getData() {
        List<Bill> list = new ArrayList<>();

        Bill bill1 = Bill.builder().
                billingCycleId(1).resellerId(1).amountType(1).amount(1L).build();

        Bill bill2 = Bill.builder().
                billingCycleId(1).resellerId(1).amountType(1).amount(1L).build();

        Bill bill3 = Bill.builder().
                billingCycleId(1).resellerId(1).amountType(2).amount(1L).build();

        Bill bill4 = Bill.builder().
                billingCycleId(1).resellerId(2).amountType(1).amount(1L).build();

        Bill bill5 = Bill.builder().
                billingCycleId(1).resellerId(2).amountType(1).amount(1L).build();

        Bill bill6 = Bill.builder().
                billingCycleId(1).resellerId(2).amountType(2).amount(1L).build();

        Bill bill7 = Bill.builder().
                billingCycleId(2).resellerId(2).amountType(2).amount(1L).build();


        list.add(bill1);
        list.add(bill2);
        list.add(bill3);
        list.add(bill4);
        list.add(bill5);
        list.add(bill6);
        list.add(bill7);
        return list;
    }
}

/**
 * 分组元素实体
 *
 * @author Young
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Bill {
    private Integer billingCycleId;
    private Integer resellerId;
    private Integer amountType;
    private Long amount;
}
