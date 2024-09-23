package com.airbnb.util;

import com.airbnb.dto.BookingDto;
import com.airbnb.entity.Booking;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.awt.desktop.AppReopenedEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PDFService {
    private EmailService emailService;

    public void generatePDF(BookingDto bookingDto){

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C://bnb_bookings//"+bookingDto.getId()+"_booking_confirmation.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addRows(table,bookingDto);

            document.add(table);
            document.close();

            //Another Method to call Email
            emailService.sendEmailWithAttachment(
                    bookingDto.getEmail(),
                    "Booking Confirmation. Your booking id"+bookingDto.getId(),
                    "test",
                    new File("C://bnb_bookings//"+bookingDto.getId()+"_booking_confirmation.pdf")
                    );

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //AddTableHeader
    private void addTableHeader(PdfPTable table) {
        Stream.of("Guest Name", "Hotel Name", "City")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    //AddRows
    private void addRows(PdfPTable table, BookingDto bookingDto) {
        table.addCell(bookingDto.getGuestName());
        table.addCell(bookingDto.getProperty().getName());
        table.addCell(bookingDto.getProperty().getCity().getName());
    }

}
