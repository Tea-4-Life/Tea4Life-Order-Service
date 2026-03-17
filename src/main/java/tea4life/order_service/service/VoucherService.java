package tea4life.order_service.service;

import org.springframework.stereotype.Service;
import tea4life.order_service.model.Voucher;

import java.util.List;

/**
 * @author Le Tran Gia Huy
 * @created 17/03/2026 - 10:45 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.service.impl
 */

@Service
public interface VoucherService {
    List<Voucher> findAllVouchers();

    Voucher findVouchersById(Long id);

    Voucher saveVoucher(Voucher voucher);

    void deleteVoucherById(Long id);
}
