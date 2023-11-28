package tr.edu.medipol.H5210072.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tr.edu.medipol.H5210072.dto.Cevap5Dto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Swagger url : http://localhost:8080/swagger-ui/index.html

@Tag(name = "Homework Controller")
@RestController
public class HomeworkController {

    @GetMapping("/")
    public String getStudentInformations() {
        return "H5210072 Kübra Aykol";
    }

    @GetMapping("/cevap1")
    public List<String> cevap1(@RequestParam("sentence") String sentence) {

        String[] words = sentence.split(" ");

        List<String> sortedWords = Arrays.stream(words).sorted().toList();

        return sortedWords;
    }

    @GetMapping("/cevap2")
    public Integer cevap2(@RequestParam("op") String op,
                          @RequestParam("sayi1") Integer sayi1,
                          @RequestParam("sayi2") Integer sayi2) {

        switch (op) {
            case "+":
                return sayi1 + sayi2;
            case "-":
                return sayi1 - sayi2;
            case "*":
                return sayi1 * sayi2;
            case "/":
                return sayi1 / sayi2;
            default:
                return -1;
        }

    }

    @GetMapping("/cevap3")
    public String cevap3(@RequestParam(name = "format", defaultValue = "dd-MM-yyyy HH:mm", required = false) String format) {

        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTime.format(DateTimeFormatter.ofPattern(format));


    }


    @PostMapping("/cevap4")
    public Boolean cevap4(@RequestParam(name = "ip") String ip) {

        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";
        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        Pattern p = Pattern.compile(regex);


        if (ip == null || ip.isEmpty()) {
            return false;
        }

        Matcher m = p.matcher(ip);

        return m.matches();

    }

    @PostMapping("/cevap5")
    public String cevap5(@RequestBody Cevap5Dto dto) {

        try {
            File directoryPath = new File(dto.getKlasorAdresi());
            File files[] = directoryPath.listFiles();

            File targetFile = new File(dto.getSonucDosyasiAdi());

            if (targetFile.createNewFile()) {
                for (File file : files) {
                    if (dto.getSize() < file.length() && file.isFile()) {
                        Files.write(targetFile.toPath(), (file.getAbsolutePath() + " - " + file.length() + "\n").getBytes(), StandardOpenOption.APPEND);
                    }
                }

                return targetFile.getAbsolutePath();
            } else {
                return null;
            }

        } catch (Exception exception) {
            return null;
        }

    }


}