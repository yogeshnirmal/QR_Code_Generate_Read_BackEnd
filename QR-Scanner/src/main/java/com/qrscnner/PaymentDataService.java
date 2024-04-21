package com.qrscnner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PaymentDataService {

    public ResponseEntity<byte[]> generateQRCode(PaymentData paymentData) {
        try {
           String paymentDataJson = "upi://pay?pa=" + paymentData.getVpa() +
                    "&pn=" + paymentData.getRecipient() +
                    "&am=" + paymentData.getAmount() +
                    "&cu=" + paymentData.getCurrency() +
                    "&tn=" + paymentData.getReference();

            ByteArrayOutputStream byteArrayOutputStream= QRCode.from(paymentDataJson).to(ImageType.PNG).stream();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
        } catch (Exception ex) { // Catch generic exception
            System.out.println("Exception while Creating QR code: " + ex);
            throw new RuntimeException("Failed to generate QR code", ex); // Return 500 Internal Server Error
        }
    }

    public ResponseEntity<String> qrCodeRead(MultipartFile file) {
        try{
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(img);
            HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return ResponseEntity.ok(result.toString());
        }catch (Exception ex){
            System.out.println("Exception While Reading QR Code"+ex);
            return ResponseEntity.badRequest().body("Exception While Reading QR Code");
        }
    }
}
