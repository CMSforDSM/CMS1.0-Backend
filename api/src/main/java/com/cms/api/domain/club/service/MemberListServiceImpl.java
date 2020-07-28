package com.cms.api.domain.club.service;

import com.cms.api.domain.club.dao.ClubRepository;
import com.cms.api.domain.club.domain.Club;
import com.cms.api.domain.club.dto.ClubMemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
        int[] columnLen = {2, 1, 1, 1, 1};

        int rowIdx = inputTitle(wb, sheet, columnLen);
        inputMembers(getMembers(), rowIdx, sheet, columnLen);

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

    private int inputTitle(XSSFWorkbook wb, Sheet sheet, int[] titleLen) {
        int rowIdx = 0;
        Row row = sheet.createRow(rowIdx++);
        Cell cell = null;

        String[] title = {"동아리", "학번", "이름", "출석여부", "비고"};

        for(int i = 0, j=0; j< title.length; i+=titleLen[j], j++) {
            cell = row.createCell(i);
            cell.setCellValue(title[j]);
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
                        .build())
                .collect(Collectors.toList());
    }

    private void inputMembers(List<ClubMemberResponseDto> members, int rowIdx, Sheet sheet, int[] elementLen) {
        for(ClubMemberResponseDto member : members) {
            Row row = sheet.createRow(rowIdx++);
            int cellIdx = 0;
            int elementCount = 0;

            Cell cell = row.createCell(cellIdx);
            cell.setCellValue(member.getClub_name());
            cellIdx += elementLen[elementCount];
            elementCount++;

            cell = row.createCell(cellIdx);
            cell.setCellValue(member.getStudent_no());
            cellIdx += elementLen[elementCount];

            cell = row.createCell(cellIdx);
            cell.setCellValue(member.getName());
        }
    }
}
