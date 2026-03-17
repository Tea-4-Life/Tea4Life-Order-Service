package tea4life.order_service.controller.admin;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tea4life.order_service.dto.base.ApiResponse;
import tea4life.order_service.model.Voucher;
import tea4life.order_service.service.VoucherService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author Le Tran Gia Huy
 * @created 17/03/2026 - 11:03 PM
 * @project Tea4Life-Product-Service
 * @package tea4life.order_service.controller.admin
 */

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/admin/vouchers")
public class VoucherAdminController {

    final VoucherService voucherService;

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> insertVouchers(
                                                       @RequestParam(value = "discountPercentage") double discountPercentage,
                                                       @RequestParam(value = "minOrderAmount") BigDecimal minOrderAmount,
                                                       @RequestParam(value = "maxDiscountAmount") BigDecimal maxDiscountAmount,
                                                       @RequestParam(value = "description") String description,
                                                       @RequestParam(value = "imgUrl") String imgUrl
    ) {
        try {
            Voucher voucher = new Voucher();
            voucher.setDiscountPercentage(discountPercentage);
            voucher.setMinOrderAmount(minOrderAmount);
            voucher.setMaxDiscountAmount(maxDiscountAmount);
            voucher.setDescription(description);
            voucher.setImgUrl(imgUrl);
            Voucher savedVoucher = voucherService.saveVoucher(voucher);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(savedVoucher));
        } catch (DataIntegrityViolationException e) {
            // Lỗi do database (VD: vi phạm unique, thiếu trường bắt buộc...)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(null,null,"Lỗi dữ liệu: Vi phạm ràng buộc hoặc dữ liệu đã tồn tại."));
        } catch (Exception e) {
            // Lỗi hệ thống khác (VD: mất kết nối DB, lỗi runtime...)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null,null,"Lỗi server: Không thể thêm mới Voucher lúc này."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateVouchers(
            @PathVariable Long id,
            @RequestParam(value = "discountPercentage") double discountPercentage,
            @RequestParam(value = "minOrderAmount") BigDecimal minOrderAmount,
            @RequestParam(value = "maxDiscountAmount") BigDecimal maxDiscountAmount,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "imgUrl") String imgUrl
    ) {
        try {
            Voucher existingVoucher = voucherService.findVouchersById(id);
            if (existingVoucher == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, null, "Không tìm thấy Voucher với ID: " + id));
            }
            existingVoucher.setDiscountPercentage(discountPercentage);
            existingVoucher.setMinOrderAmount(minOrderAmount);
            existingVoucher.setMaxDiscountAmount(maxDiscountAmount);
            existingVoucher.setDescription(description);
            existingVoucher.setImgUrl(imgUrl);
            Voucher updatedVoucher = voucherService.saveVoucher(existingVoucher);
            return ResponseEntity.ok(new ApiResponse<>(updatedVoucher));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(null, null, "Lỗi dữ liệu: Vi phạm ràng buộc hoặc dữ liệu đã tồn tại."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, null, "Lỗi server: Không thể cập nhật Voucher lúc này."));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteVouchersById(@PathVariable Long id) {
        try {
            voucherService.deleteVoucherById(id);
            return ResponseEntity.ok(new ApiResponse<>("Xóa Voucher thành công với ID: " + id));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(null,null,"Lỗi dữ liệu: Không thể xóa Voucher do có liên kết với đơn hàng hoặc dữ liệu khác."));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null,null,"Không tìm thấy Voucher với ID: " + id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null,null,"Lỗi server: Không thể xóa Voucher lúc này."));
        }
    }
}
