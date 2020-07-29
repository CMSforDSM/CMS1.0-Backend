package com.cms.api.domain.club.service;

import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.dto.ClubMemberResponseDto;
import com.cms.api.domain.club.exception.CannotGenerateExcelException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberListServiceImpl implements MemberListService {

    private final ClubRepository clubRepository;

    @Override
    @Transactional
    public void getMemberList(HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("전공동아리 명단");

        setColumnWidth(sheet);
        int rowIdx = inputTitle(sheet);
        inputMembers(getMembers(), rowIdx, sheet, wb);

        try {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"test.xlsx\""));
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            throw new CannotGenerateExcelException();
        } finally {
            try {
                wb.close();
            } catch (Exception ignore) {};
        }
    }

    private int inputTitle(Sheet sheet) {
        int rowIdx = 0;
        Row row = sheet.createRow(rowIdx++);
        Cell cell = null;

        String[] title = {"", "동아리", "학번", "이름", "비고", "7교시", "8교시", "9교시", "10교시"};

        for(int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }

        return rowIdx;
    }

    private List<ClubMemberResponseDto> getMembers() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .flatMap(club -> club.getClubMembers().stream())
                .map(member -> ClubMemberResponseDto.builder()
                        .club_name(member.split("-")[0])
                        .student_no(member.split("-")[1])
                        .name(member.split("-")[2])
                        .leader(member.split("-")[3])
                        .build())
                .collect(Collectors.toList());
    }

    private void inputMembers(List<ClubMemberResponseDto> members, int rowIdx, Sheet sheet, XSSFWorkbook wb) {
        int memberCount = 1;
        for(ClubMemberResponseDto member : members) {
            Row row = sheet.createRow(rowIdx++);
            int cellIdx = 0;

            boolean isLeader = false;
            if(member.getName().equals(member.getLeader()))
                isLeader = true;

            Cell cell = createDefaultCell(row, cellIdx++, wb);
            cell.setCellValue(memberCount++);

            cell = createDefaultCell(row, cellIdx++, wb);
            cell.setCellValue(member.getClub_name());

            cell = createDefaultCell(row, cellIdx++, wb);
            cell.setCellValue(member.getStudent_no());

            cell = createDefaultCell(row, cellIdx++, wb);
            cell.setCellValue(member.getName());
            if(isLeader)
                cell.setCellStyle(cellStyle(wb, true));

            cell = createDefaultCell(row, cellIdx++, wb);
            cell.setCellStyle(cellStyle(wb, false));
            if(isLeader)
                cell.setCellValue("동아리장");

            createDefaultCell(row, cellIdx++, wb);
            createDefaultCell(row, cellIdx++, wb);
            createDefaultCell(row, cellIdx++, wb);
            createDefaultCell(row, cellIdx, wb);

        }
    }

    private static Cell createDefaultCell(Row row, int cellIdx, XSSFWorkbook wb) {
        Cell cell = row.createCell(cellIdx);
        cell.setCellStyle(cellStyle(wb, false));
        return cell;
    }

    private static CellStyle cellStyle(XSSFWorkbook wb, boolean isLeader) {
        CellStyle cellStyle = wb.createCellStyle();

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        if(isLeader) {
            cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        return cellStyle;
    }

    private static void setColumnWidth(Sheet sheet) {
        sheet.setColumnWidth(0, 3 * 256);
        sheet.setColumnWidth(1, 15 * 256);
    }

}
