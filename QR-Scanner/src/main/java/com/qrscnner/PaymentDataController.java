package com.qrscnner;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PaymentDataController {

    @Autowired
    PaymentDataService paymentDataService;


    @PostMapping(value = "/generateQR", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestBody PaymentData paymentData) {
        return paymentDataService.generateQRCode(paymentData);
    }

    @PostMapping(value = "/readOR")
    public  ResponseEntity<String>qrCodeRead(@RequestBody MultipartFile file){
        return paymentDataService.qrCodeRead(file);
    }
}
