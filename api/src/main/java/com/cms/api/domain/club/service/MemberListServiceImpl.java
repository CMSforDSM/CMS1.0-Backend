package com.cms.api.domain.club.service;

import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberListServiceImpl implements MemberListService {

    private final ClubRepository clubRepository;

    @Override
    @Transactional
    public void getMemberList(HttpServletResponse response) {
        List<Club> clubs = clubRepository.findAll();

        XSSFWorkbook wb = new XSSFWorkbook();

        inputTitle(wb);

        try {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
            wb.write(response.getOutputStream());
        } catch (Exception e) {

        } finally {
            try {
                wb.close();
            } catch (Exception ignore) {};
        }
    }

    private void inputTitle(XSSFWorkbook wb) {
        Sheet sheet = wb.createSheet("전공동아리 명단");

        int rowIdx = 0;
        Row row = sheet.createRow(rowIdx++);
        Cell cell = null;
        String[] title = {"동아리", "학번", "이름", "출석여부"};
        int[] titleLen = {2, 1, 2, 3};
        for(int i = 0, j=0; j< title.length; i+=titleLen[j], j++) {
            cell = row.createCell(i);
            cell.setCellValue(title[j]);
        }
    }
}
