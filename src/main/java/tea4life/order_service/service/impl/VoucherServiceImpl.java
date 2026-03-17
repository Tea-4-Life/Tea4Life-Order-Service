package tea4life.order_service.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tea4life.order_service.model.Voucher;
import tea4life.order_service.repository.VoucherRepository;

import java.util.List;

/**
 * @author Le Tran Gia Huy
 * @created 17/03/2026 - 10:45 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.service.impl
 */

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoucherServiceImpl {
    final VoucherRepository voucherRepository;

    public List<Voucher> findAllVouchers() {
        return voucherRepository.findAll();
    }

    public Voucher findVouchersById(Long id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy Voucher với ID: " + id));
        return voucher;
    }

    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    public void deleteVoucherById(Long id) {
        if (!voucherRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy Voucher với ID: " + id);
        }
        voucherRepository.deleteById(id);
    }


}
